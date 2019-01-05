package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import epost.android.mitake.com.fbloginkotlinsample.data.TrustTradeObject
import epost.android.mitake.com.fbloginkotlinsample.util.TimeUtils
import mma.security.component.diagnostics.Debuk

class TrustTradeDetailViewModel : ViewModel() {

    lateinit var order: TrustTradeObject

    /**
     * 取消交易
     */
    fun cancelOrder() {
        Debuk.WriteLine("****", TimeUtils.getTimeString())
    }
}
