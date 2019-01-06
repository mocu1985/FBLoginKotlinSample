package epost.android.mitake.com.item

import android.content.Intent
import android.view.View
import epost.android.mitake.com.fbloginkotlinsample.BR
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeObject
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.traderecord.ui.trusttradedetail.TrustTradeDetailActivity
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentActivity
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.TrustTradeListViewModel

//信任交易清單item
class TrustTradeItem : BaseItem {

    var act: ParentActivity
    var order: TrustTradeObject
    var orderId: String

    constructor(act: ParentActivity, orderId: String, order: TrustTradeObject, callback: TrustTradeListViewModel) {
        this.act = act
        this.orderId = orderId
        this.order = order
        onClickListener = View.OnClickListener {
            GlobalProperties.trsutObj = order
            act.startActivity(Intent(act, TrustTradeDetailActivity::class.java))
//            act.startActivityCallback(Intent(act, TrustTradeDetailActivity::class.java),
//                GlobalProperties.TRADE_ORDER_DELETE,
//                callback)
        }
    }

    override fun getLayout(): Int {
        return R.layout.trust_trade_item
    }

    override fun getVariableId(): Int {
        return BR.item
    }

    fun checkState(): String {
        return when (order.trustInfo.orderState) {
            "0" ->  "等待對方確認"
            "1" ->  "處理中"
            "2" ->  "已確認"
            "3" ->  "已完成"
            else -> {
                "裁決中"
            }
        }
    }
}