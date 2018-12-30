package epost.android.mitake.com.item

import android.util.Log
import android.view.View
import epost.android.mitake.com.fbloginkotlinsample.BR
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeObject

//信任交易清單item
class TrustTradeItem : BaseItem {

    var order: TrustTradeObject
    var orderId: String


    constructor(orderId: String, order: TrustTradeObject) {
        this.orderId = orderId
        this.order = order
        onClickListener = View.OnClickListener {
            Log.d("*****", order.trustInfo.tradeTitle)
        }
    }

    override fun getLayout(): Int {
        return R.layout.trust_trade_item
    }

    override fun getVariableId(): Int {
        return BR.item
    }

}