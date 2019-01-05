package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.traderecord.ui.trusttradedetail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.attribute.GlobalProperties
import epost.android.mitake.com.fbloginkotlinsample.databinding.TrustTradeDetailFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.TrustTradeDetailViewModel

//交易紀錄-明細
class TrustTradeDetailFragment : Fragment() {

    companion object {
        fun newInstance() = TrustTradeDetailFragment()
    }

    private lateinit var viewModel: TrustTradeDetailViewModel
    lateinit var binding: TrustTradeDetailFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.trust_trade_detail_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(TrustTradeDetailViewModel::class.java)
        viewModel.order = GlobalProperties.trsutObj
        binding.model = viewModel

        return binding.root
    }

}
