package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.arch.lifecycle.ViewModel
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.data.RulingInfo
import epost.android.mitake.com.fbloginkotlinsample.data.RulingObject
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeObject
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.traderecord.ui.trusttradedetail.TradeToRulingFragment
import epost.android.mitake.com.fbloginkotlinsample.framework.TitleBarParentActivity
import epost.android.mitake.com.fbloginkotlinsample.util.JDialog
import epost.android.mitake.com.fbloginkotlinsample.util.TimeUtils
import kotlinx.android.synthetic.main.activity_share_titlebar_content.*
import mma.security.component.diagnostics.Debuk

class TrustTradeDetailViewModel : ViewModel() {

    lateinit var order: TrustTradeObject
    lateinit var act: TitleBarParentActivity

    /**
     * 取消交易
     */
    fun cancelOrder() {
        Debuk.WriteLine("****", TimeUtils.getTimeString())
        JDialog.showDialog(
            act, act.getString(R.string.alt_hint), "確定刪除？"
        ) { dialog, which -> doDelete() }
    }

    fun doDelete() {
        //TODO Functions 歸還分數
        var ref = FirebaseFirestore.getInstance()
            .collection(GlobalProperties.TRUST_TRADE_ROOT)
            .document(GlobalProperties.currect_id)
            .collection(GlobalProperties.TRADING)
            .document(order.orderId)
            .delete()
            .addOnSuccessListener {
                //                act.setResult(Activity.RESULT_OK)
//                act.finish()

                GlobalProperties.doRefresh = true
                act.onBackPressed()
            }
            .addOnFailureListener {
                JDialog.showMessage(act, act.getString(R.string.alt_hint), "刪除失敗")
            }

    }

    fun confirmClick() {
        //TODO Functions 確認訂單修改狀態
        JDialog.showDialog(
            act, act.getString(R.string.alt_hint), "是否確認完成交易？"
        ) { dialog, which ->
            order.trustInfo.orderState = if (order.trustInfo.orderState == "1") "2" else "3"

            //TODO 結果頁 or 直接返回
            act.onBackPressed()
        }
    }

    fun rulingClick() {
        //TODO Functions 修改狀態變成申訴單
        var rulingInfo = RulingInfo()
        rulingInfo.title = order.trustInfo.tradeTitle
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


    fun createRuling() {
        act.title_bar.centerTextView.setText("申訴建立")
        act.title_bar.rightTextView.setText("發布")
        act.title_bar.rightTextView.setOnClickListener {
            rulingClick()
        }
        act.supportFragmentManager.beginTransaction()
            .replace(R.id.container, TradeToRulingFragment.newInstance())
            .commitNow()
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

    fun checkStateCancelUI(): Boolean {
        return when (order.trustInfo.orderState) {
            "0" -> true
            else -> false
        }
    }

    fun checkConfirmBtnUI(): Boolean {
        return when (order.trustInfo.orderState) {
            "2" -> false
            "3" -> false
            else -> true
        }
    }

    fun checkRulingBtnUI(): Boolean {
        return when (order.trustInfo.orderState) {
            "3" -> false
            else -> true
        }
    }

    fun checkState(): String {
        return when (order.trustInfo.orderState) {
            "0" -> "等待對方確認"
            "1" -> "處理中"
            "2" -> "已確認"
            "3" -> "已完成"
            else -> {
                "裁決中"
            }
        }
    }
}
