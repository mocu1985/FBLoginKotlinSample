package epost.android.mitake.com.fbloginkotlinsample.viewmodel

import android.arch.lifecycle.ViewModel
import android.util.Log
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.trusttrade.TrustTradeFragment

class TrustTradeViewModel : ViewModel() {

    lateinit var viewData: TrustTradeFragment.ViewData

    fun confirmNext() {
        Log.d("*******", viewData.uuid)
    }
}
