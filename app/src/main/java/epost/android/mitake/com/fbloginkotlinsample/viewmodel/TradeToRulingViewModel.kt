package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.data.RulingInfo
import epost.android.mitake.com.fbloginkotlinsample.data.RulingObject
import epost.android.mitake.com.fbloginkotlinsample.databinding.TradeToRulingFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.traderecord.ui.trusttradedetail.TrustTradeDetailActivity
import epost.android.mitake.com.fbloginkotlinsample.util.JDialog
import epost.android.mitake.com.fbloginkotlinsample.util.TimeUtils
import epost.android.mitake.com.kotlinsample.Account

class TradeToRulingViewModel : ViewModel() {

    lateinit var binding: TradeToRulingFragmentBinding
    lateinit var act: TrustTradeDetailActivity
    var account: Account = GlobalProperties.account
    var rulingContent = ObservableField<String>()
    var order = GlobalProperties.trsutObj


    fun postRuling() {
        //TODO Functions 修改狀態變成申訴單
        var rulingInfo = RulingInfo()
        rulingInfo.title = order.trustInfo.tradeTitle
        rulingInfo.detail = rulingContent.get().toString()
        rulingInfo.endTime = TimeUtils.getLongTime(72)
        rulingInfo.systemTime = Timestamp.now()
        rulingInfo.orderId = order.orderId
        var rulingObject = RulingObject(TimeUtils.getTimeTemp(), rulingInfo)

        var ref = FirebaseFirestore.getInstance().collection(GlobalProperties.RULING_ROOT)
            .document(rulingObject.rulingId)
            .set(rulingInfo)
            .addOnSuccessListener {
                changeRuling()
            }
    }

    fun changeRuling() {
        FirebaseFirestore.getInstance().collection(GlobalProperties.TRUST_TRADE_ROOT)
            .document(GlobalProperties.currect_id)
            .collection(GlobalProperties.TRADING)
            .document(order.orderId)
            .update("orderState", "4")
            .addOnSuccessListener {
                JDialog.showMessage(act, act.getString(R.string.alt_hint), "已完成申訴") { dialog, which ->
                    act.onBackPressed()
                }
            }
    }
}
