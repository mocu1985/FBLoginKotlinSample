package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.app.Activity
import android.arch.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeInfo
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeObject
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.trusttrade.TrustTradeFragment
import epost.android.mitake.com.fbloginkotlinsample.util.JDialog
import epost.android.mitake.com.fbloginkotlinsample.util.TimeUtils

class TrustTradeViewModel : ViewModel() {

    lateinit var viewData: TrustTradeFragment.ViewData
    lateinit var cxt: Activity


    //建立交易單
    fun confirmNext() {
        var trustObj = TrustTradeObject()

        trustObj.orderId = System.currentTimeMillis().toString()
        trustObj.trustInfo = TrustTradeInfo()

        trustObj.trustInfo.apply {
            otherId = viewData.uuid
            mainScore = viewData.score
            tradeNote = viewData.note
            systemTime = TimeUtils.getNowTime()
        }

        val uidRef =
            FirebaseFirestore.getInstance().collection(GlobalProperties.TRUST_TRADE_ROOT)
                .document(trustObj.orderId)

        uidRef.set(trustObj.trustInfo)
            .addOnSuccessListener {
                updateTrustScore()
            }
            .addOnFailureListener {
                JDialog.showDialog(cxt, cxt.getString(R.string.alt_hint), cxt.getString(R.string.alt_trust_trade_invite))
            }


    }

    //更新帳戶分數
    fun updateTrustScore() {
        GlobalProperties.account.userInfo.apply {
            trustScore = (trustScore!!.toFloat() - viewData.score.toFloat()).toString()
        }
        var path = "${GlobalProperties.ACCOUNT_ROOT}/${GlobalProperties.currect_id}"
        val modifyRef = FirebaseFirestore.getInstance().document(path)

        modifyRef.update("userInfo.trustScore", GlobalProperties.account.userInfo.trustScore)
            .addOnSuccessListener {
                JDialog.showDialog(
                    cxt, cxt.getString(R.string.alt_hint), cxt.getString(R.string.alt_trust_trade_invite)
                ) { _, _ -> cxt.onBackPressed() }
            }
    }
}
