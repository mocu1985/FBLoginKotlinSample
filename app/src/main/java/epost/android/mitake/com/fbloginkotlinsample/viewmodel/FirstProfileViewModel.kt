package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.support.design.widget.Snackbar
import com.google.android.gms.tasks.OnFailureListener
import com.google.firebase.firestore.FirebaseFirestore
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.databinding.FirstProfileFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.framework.FragmentParentActivity
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.MainTabActivity
import epost.android.mitake.com.kotlinsample.Account
import kotlinx.android.synthetic.main.fragment_parent_activity.*

class FirstProfileViewModel : ViewModel() {

    var account: Account = GlobalProperties.account
    lateinit var activity: FragmentParentActivity

    fun createAccount(binding: FirstProfileFragmentBinding) {


        account.userInfo.uuid = account.uid!!.substring(1, account.uid!!.length).toInt().minus(98989898).toString()


        val uidRef =
            FirebaseFirestore.getInstance().collection(GlobalProperties.ACCOUNT_ROOT)
                .document(GlobalProperties.currect_id)

        uidRef.set(GlobalProperties.account)
            .addOnSuccessListener {
                var intent = Intent(activity, MainTabActivity::class.java)
                activity.startActivity(intent)
            }
            .addOnFailureListener(object : OnFailureListener {
                override fun onFailure(p0: Exception) {
                    Snackbar.make(activity.container, "註冊失敗", Snackbar.LENGTH_LONG)
                        .setAction("重新註冊") {
                            createAccount(binding)
                        }.show()
                }

            })
    }


    fun checkColumn(binding: FirstProfileFragmentBinding) {
        //TODO 欄位檢核
    }
}
