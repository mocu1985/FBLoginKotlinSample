package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.traderecord.ui.trusttradedetail

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.databinding.TradeToRulingFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.TradeToRulingViewModel
import kotlinx.android.synthetic.main.activity_share_titlebar_content.*

class TradeToRulingFragment : Fragment() {

    companion object {
        fun newInstance() = TradeToRulingFragment()
    }

    private lateinit var binding: TradeToRulingFragmentBinding
    private lateinit var viewModel: TradeToRulingViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.trade_to_ruling_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(TradeToRulingViewModel::class.java)

        viewModel.binding = binding
        viewModel.act = activity as TrustTradeDetailActivity


        viewModel.act.title_bar.rightTextView.setOnClickListener {
            viewModel.postRuling()
        }

        binding.model = viewModel

        return binding.root
    }


}
