package epost.android.mitake.com.fbloginkotlinsample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.facebook.*
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager
    lateinit var profileTracker: ProfileTracker
    lateinit var mAuth: FirebaseAuth
    lateinit var accToken: AccessToken
    //    var datebase: FirebaseDatabase? = null
//    var ref: DatabaseReference? = null
    val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        login_button.setOnClickListener { callFBManager() }
    }


    override fun onStart() {
        super.onStart()
        val firebaseUser = mAuth.currentUser

        if (firebaseUser != null) {
            firebaseUser?.let {
                Log.d(TAG, it.displayName)
                Log.d(TAG, it.uid)
            }
        } else {
            Log.d(TAG, "user null")
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
                Log.d(TAG, "onCancel")
            }

            override fun onError(error: FacebookException?) {
                Log.d(TAG, error.toString())

            }

        })
    }

    private fun getInfo(token: AccessToken) {
        var credential = FacebookAuthProvider.getCredential(token.token)

        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this, object : OnCompleteListener<AuthResult> {
                override fun onComplete(p0: Task<AuthResult>) {
                    if (p0.isSuccessful) {
                        val firebaseUser = mAuth.currentUser
                        Log.d(TAG, "登入成功")

                        firebaseUser?.let {
                            Log.d(TAG, it.displayName)
                            Log.d(TAG, it.uid)
                        }
                    } else {
                        Log.d(TAG, "登入失敗")
                    }
                }

            })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //callbackManager.onActivityResult對應上方FacebookCallback
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }
}
