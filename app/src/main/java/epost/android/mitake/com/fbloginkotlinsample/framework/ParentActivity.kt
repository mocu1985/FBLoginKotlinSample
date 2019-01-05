package epost.android.mitake.com.fbloginkotlinsample.framework

import android.content.Intent
import android.support.v7.app.AppCompatActivity

open class ParentActivity: AppCompatActivity() {


    //自定義Activity callback
    lateinit var activityCallBack: ActivityCallBack

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        activityCallBack.onActivityResult(requestCode, resultCode, data)
    }

    fun startActivityCallback(intent: Intent, requestCode: Int, activityCallBack: ActivityCallBack) {
        startActivityForResult(intent, requestCode)
        this.activityCallBack = activityCallBack
    }

    interface ActivityCallBack{
        fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    }
}