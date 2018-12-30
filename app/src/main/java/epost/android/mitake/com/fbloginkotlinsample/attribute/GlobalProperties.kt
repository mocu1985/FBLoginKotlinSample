package epost.android.mitake.com.fbloginkotlinsample.attribute

import epost.android.mitake.com.kotlinsample.Account

class GlobalProperties {
    companion object {
        lateinit var currect_id: String //當前用戶指向id

        lateinit var account: Account

        val ACCOUNT_ROOT = "users"
        val TRUST_TRADE_ROOT = "trust_trade"
        val ACCOUNT_INFO = "userInfo"
    }
}