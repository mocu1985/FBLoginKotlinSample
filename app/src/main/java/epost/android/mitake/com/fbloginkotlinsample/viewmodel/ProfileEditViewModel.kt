package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.arch.lifecycle.ViewModel
import android.content.Context
import android.content.Intent
import android.databinding.ObservableField
import android.view.View
import android.widget.AdapterView
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.databinding.FragmentProfileEditBinding
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.trusttrade.TrustTradeActivity
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.userprofile.myruling.MyRulingActivity

class ProfileEditViewModel : ViewModel() {

    lateinit var cxt: Context;
    lateinit var binding: FragmentProfileEditBinding
    var account = GlobalProperties.account
    var isEdit: ObservableField<Boolean> = ObservableField(false)


    fun doUpdate(binding: FragmentProfileEditBinding) {
//        GlobalProperties.account.uid = binding.edtUserid.text.toString()
//        GlobalProperties.account.userInfo.name = binding.edtName.text.toString()
//        GlobalProperties.account.userInfo.birthday = binding.edtBir.text.toString()
//        GlobalProperties.account.userInfo.address = binding.edtAddress.text.toString()
//
//        var updateRef = MainTabActivity.rootRef.collection(GlobalProperties.ACCOUNT_ROOT)
//            .document(GlobalProperties.currect_id)
//
//
//        var hashMap = HashMap<String, Any>()
//        hashMap.put("uid", GlobalProperties.account.uid!!)
//        hashMap.put("userInfo.name", GlobalProperties.account.userInfo.name!!)
//        hashMap.put("userInfo.birthday", GlobalProperties.account.userInfo.birthday!!)
//        hashMap.put("userInfo.address", GlobalProperties.account.userInfo.address!!)
//
//        updateRef.update(hashMap)
//            .addOnSuccessListener {
//                Snackbar.make(binding.contentView, "更新成功2", Snackbar.LENGTH_LONG).show()
//            }
//            .addOnFailureListener {
//                Snackbar.make(binding.contentView, "更新失敗2", Snackbar.LENGTH_LONG).show()
//            }
    }

    //Firebase FB登出
//    fun logout() {
//        FirebaseAuth.getInstance().signOut()
//    }

    fun setBtnText() {
        isEdit.set(!isEdit.get()!!)
    }


    fun floatAction() {
        this.cxt.startActivity(Intent(cxt, TrustTradeActivity::class.java))
    }

    fun addItemClick() {
        binding.itemList.onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                when (position){
                    0 -> return
                    1 -> cxt.startActivity(Intent(cxt, MyRulingActivity::class.java))
                }
            }

        }
    }

}