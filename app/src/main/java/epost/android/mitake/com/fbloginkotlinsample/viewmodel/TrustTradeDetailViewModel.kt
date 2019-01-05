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
}
