package epost.android.mitake.com.item

import com.baurine.multitypeadapter.MultiTypeAdapter
import epost.android.mitake.com.fbloginkotlinsample.BR
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeObject

class TrustTradeItem : MultiTypeAdapter.IItem {

//    var order: TrustTradeInfo
    var order: TrustTradeObject
    var orderId: String

//    constructor(orderId: String, order: TrustTradeInfo) {
//        this.orderId = orderId
//        this.order = order
//    }


    constructor(orderId: String, order: TrustTradeObject) {
        this.orderId = orderId
        this.order = order
    }

    override fun getLayout(): Int {
        return R.layout.trust_trade_item
    }

    override fun getVariableId(): Int {
        return BR.item
    }
}