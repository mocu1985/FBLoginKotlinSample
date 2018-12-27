package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.trade.ui.trusttrade

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R

class TrustTradeFragment : Fragment() {

    companion object {
        fun newInstance() = TrustTradeFragment()
    }

    private lateinit var viewModel: TrustTradeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.trust_trade_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(TrustTradeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
