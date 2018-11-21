package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import android.util.Log
import com.google.firebase.database.DataSnapshot
import epost.android.mitake.com.fbloginkotlinsample.data.Info
import epost.android.mitake.com.fbloginkotlinsample.databinding.FragmentProfileEditBinding
import epost.android.mitake.com.kotlinsample.Account

class ProfileEditViewModel : ViewModel() {
    lateinit var info: Info
    var isEdit: ObservableField<Boolean> = ObservableField(false)

    fun updateInfo(p0: DataSnapshot, binding: FragmentProfileEditBinding) {
        Log.d("****", p0.getValue().toString())
        Account.account.userInfo = p0.getValue(Info::class.java)!!

        info.apply {
            name = Account.account.userInfo.name
            birthday = Account.account.userInfo.birthday
            address = Account.account.userInfo.address
            sex = Account.account.userInfo.sex
        }
        Log.d("****", Account.account.userInfo.name)
        Log.d("****", info.name)
    }

    fun setBtnText() {
        isEdit.set(!isEdit.get()!!)
    }

}