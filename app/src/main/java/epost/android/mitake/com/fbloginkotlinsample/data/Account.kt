package epost.android.mitake.com.kotlinsample

import epost.android.mitake.com.fbloginkotlinsample.data.Info

public class Account {
    var userInfo: Info

    constructor(id: String, name: String = "", birthday: String = "") {
        Account.id = id
        this.userInfo = Info(name, birthday)
    }

    companion object {
        lateinit var account: Account
        lateinit var id: String
    }

}