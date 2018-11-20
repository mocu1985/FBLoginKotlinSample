package epost.android.mitake.com.fbloginkotlinsample

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginResult
import com.google.firebase.database.*
import com.orhanobut.logger.Logger
import epost.android.mitake.com.fbloginkotlinsample.data.Info
import epost.android.mitake.com.kotlinsample.Account
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var callbackManager: CallbackManager
    lateinit var profileTracker: ProfileTracker
    lateinit var accToken: AccessToken
    var datebase: FirebaseDatabase? = null
    var ref: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FacebookSdk.sdkInitialize(getApplicationContext())
        AppEventsLogger.activateApp(this)

        callbackManager = CallbackManager.Factory.create()

        datebase = FirebaseDatabase.getInstance()


        try {
            accToken = AccessToken.getCurrentAccessToken()

            if (accToken != null && !accToken.isExpired) {
                Log.d("****", "is Login")
                getInfo(accToken)
            } else {
                callFBManager()
            }
        } catch (e: java.lang.Exception) {
            callFBManager()
        }


        login_button.setOnClickListener { callFBManager() }
    }


    private fun getInfo(token: AccessToken) {
        var request = GraphRequest.newMeRequest(
            token,
            object : GraphRequest.GraphJSONObjectCallback {
                override fun onCompleted(`object`: JSONObject?, response: GraphResponse?) {
                    Logger.json(`object`.toString())


                    Account.account = Account(
                        `object`!!.get("id").toString(),
                        `object`!!.get("name").toString(),
                        `object`!!.get("birthday").toString()
                    )

//                    doUpdate()
                    checkAccount()
                }

            })

        var bundle = Bundle()
        bundle.putString("fields", "id,name,birthday")
        request.parameters = bundle
        request.executeAsync()
    }

    private fun checkAccount() {
        try {
            var reference = "accounts/" + Account.id.get() + "/userInfo"
            ref = datebase?.getReference(reference)
            ref?.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    Log.d("****", p0.toString())
                    doUpdate()
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0 != null) {
                        Logger.d(p0.toString())
                        Account.account.userInfo = p0.getValue(Info::class.java)!!
                        var intent = Intent(this@MainActivity, MainTabActivity::class.java)
                        startActivity(intent)
                    } else {
                        doUpdate()
                    }
                }
            })
        } catch (e: Exception) {
            Log.d("*****", e.toString())
            doUpdate()
        }

    }

    private fun doUpdate() {
        ref = datebase?.getReference("accounts")

        var map = HashMap<String, Account>()
        map.put(Account.id.get()!!, Account.account)

        ref?.updateChildren(
            map as Map<String, Any>,
            object : DatabaseReference.CompletionListener {
                override fun onComplete(p0: DatabaseError?, p1: DatabaseReference) {
                    if (p0 == null) {
                        var intent = Intent(this@MainActivity, MainTabActivity::class.java)
                        startActivity(intent)
                    }
                }
            })
    }

    private fun callFBManager() {
        var permissionList = mutableListOf("email", "user_birthday")
        login_button.setReadPermissions(permissionList)

        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {

            override fun onSuccess(result: LoginResult?) {
                getInfo(result!!.accessToken)
            }

            override fun onCancel() {
                Log.d("****", "onCancel")
            }

            override fun onError(error: FacebookException?) {
                Log.d("****", error.toString())

            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        //callbackManager.onActivityResult對應上方FacebookCallback
        callbackManager?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
