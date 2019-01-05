package epost.android.mitake.com.fbloginkotlinsample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.MainTabActivity
import epost.android.mitake.com.fbloginkotlinsample.framework.FragmentParentActivity
import epost.android.mitake.com.kotlinsample.Account
import kotlinx.android.synthetic.main.activity_main.*
import mma.security.component.diagnostics.Debuk

class MainActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager
    lateinit var mAuth: FirebaseAuth
    val rootRef = FirebaseFirestore.getInstance()

    //    var datebase: FirebaseDatabase? = null
//    var ref: DatabaseReference? = null
    val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (BuildConfig.isShowLog) Debuk.OpenWriteDebukMessage()

        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        login_button.setOnClickListener { callFBManager() }
    }


    override fun onStart() {
        super.onStart()
        try {
            updateLoginProcess()
        } catch (e: Exception) {
//            捕捉異常，讓使用者手動登出再登入
        }
    }

    private fun callFBManager() {
        var permissionList = mutableListOf("email", "public_profile")
        login_button.setReadPermissions(permissionList)

        callbackManager = CallbackManager.Factory.create()
        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

            override fun onSuccess(result: LoginResult?) {
                getInfo(result!!.accessToken)
            }

            override fun onCancel() {
                Debuk.WriteLine(TAG, "onCancel")
            }

            override fun onError(error: FacebookException?) {
                Debuk.WriteLine(TAG, error.toString())

            }

        })
    }

    private fun getInfo(token: AccessToken) {
        var credential = FacebookAuthProvider.getCredential(token.token)

        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) { p0 ->
                if (p0.isSuccessful) {
                    updateLoginProcess()
                } else {
                    Debuk.WriteLine(TAG, "FB 登入失敗")
                }
            }

    }


    fun updateLoginProcess() {
        val firebaseUser = mAuth.currentUser
        GlobalProperties.currect_id = firebaseUser?.uid!!
        val uidRef =
            rootRef.collection(GlobalProperties.ACCOUNT_ROOT).document(GlobalProperties.currect_id)

        if (firebaseUser != null) {
            uidRef.get().addOnCompleteListener { p0 ->
                if (p0.isSuccessful) {
                    var document = p0.result!!
                    if (document.exists()) {
                        Debuk.WriteLine(TAG, document.data.toString())
                        GlobalProperties.account = document.toObject(Account::class.java)!!
                        var intent = Intent(this@MainActivity, MainTabActivity::class.java)
                        startActivity(intent)
                    } else {
                        addFirstAccount(GlobalProperties.currect_id, firebaseUser, uidRef)
                    }
                }
            }

        } else {
            Debuk.WriteLine(TAG, "user null")
            if (firebaseUser == null) {
                addFirstAccount(GlobalProperties.currect_id, firebaseUser, uidRef)
            }
        }
    }


    fun addFirstAccount(uid: String, firebaseUser: FirebaseUser, uidRef: DocumentReference) {
        GlobalProperties.account = Account(firebaseUser.displayName!!)
        startActivity(Intent(this@MainActivity, FragmentParentActivity::class.java))

//        uidRef.set(GlobalProperties.account)
//            .addOnSuccessListener {
//                Debuk.WriteLine(TAG, "註冊成功")
//                var intent = Intent(this@MainActivity, MainTabActivity::class.java)
//                startActivity(intent)
//            }
//            .addOnFailureListener(object : OnFailureListener {
//                override fun onFailure(p0: Exception) {
//                    Snackbar.make(content_view, "註冊失敗", Snackbar.LENGTH_LONG)
//                        .setAction("重新註冊") {
//                            addFirstAccount(uid, firebaseUser, uidRef)
//                        }.show()
//                }
//
//            })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //callbackManager.onActivityResult對應上方FacebookCallback
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}
