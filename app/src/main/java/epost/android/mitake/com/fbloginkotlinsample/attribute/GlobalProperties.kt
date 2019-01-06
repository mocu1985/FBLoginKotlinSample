package epost.android.mitake.com.fbloginkotlinsample.attribute

import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeObject
import epost.android.mitake.com.kotlinsample.Account

class GlobalProperties {
    companion object {
        lateinit var currect_id: String //當前用戶指向id

        lateinit var account: Account

        lateinit var trsutObj: TrustTradeObject

        var doRefresh = false   //是否需要刷新交易清單

        val ACCOUNT_ROOT = "users"
        val TRUST_TRADE_ROOT = "trust_trade"
        val RULING_ROOT = "ruling_root"
        val ACCOUNT_INFO = "userInfo"
        val TRADING = "trading"
        val TRADE_DONE = "trade_done"
        val RULING = "ruling"



        val TRADE_ORDER_DELETE = 1
    }
}