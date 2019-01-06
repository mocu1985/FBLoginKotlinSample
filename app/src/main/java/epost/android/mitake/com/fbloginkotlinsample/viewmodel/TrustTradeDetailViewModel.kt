package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.app.Activity
import android.arch.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeObject
import epost.android.mitake.com.fbloginkotlinsample.util.JDialog
import epost.android.mitake.com.fbloginkotlinsample.util.TimeUtils
import mma.security.component.diagnostics.Debuk

class TrustTradeDetailViewModel : ViewModel() {

    lateinit var order: TrustTradeObject
    lateinit var act: Activity

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
