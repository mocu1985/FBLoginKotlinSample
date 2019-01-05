package epost.android.mitake.com.item

import android.content.Context
import android.content.Intent
import android.view.View
import epost.android.mitake.com.fbloginkotlinsample.BR
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeObject
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.traderecord.ui.trusttradedetail.TrustTradeDetailActivity

//信任交易清單item
class TrustTradeItem : BaseItem {

    var cxt: Context
    var order: TrustTradeObject
    var orderId: String


    constructor(cxt: Context, orderId: String, order: TrustTradeObject) {
        this.cxt = cxt
        this.orderId = orderId
        this.order = order
        onClickListener = View.OnClickListener {
            GlobalProperties.trsutObj = order
            cxt.startActivity(Intent(cxt, TrustTradeDetailActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.trust_trade_item
    }

    override fun getVariableId(): Int {
        return BR.item
    }

}