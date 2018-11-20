package epost.android.mitake.com.kotlinsample

import android.databinding.BaseObservable
import android.databinding.ObservableField
import epost.android.mitake.com.fbloginkotlinsample.data.Info

public class Account : BaseObservable {
    var userInfo: Info

    constructor(id: String, name: String = "", birthday: String = "") {
        Account.id = ObservableField(id)
        this.userInfo = Info(name, birthday)
    }

    companion object {
        lateinit var account: Account
        lateinit var id: ObservableField<String>
    }

}