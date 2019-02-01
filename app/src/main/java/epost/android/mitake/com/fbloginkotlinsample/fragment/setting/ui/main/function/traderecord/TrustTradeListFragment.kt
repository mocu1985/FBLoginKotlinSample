package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.traderecord

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.databinding.TrustTradeListFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentActivity
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentFragment
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.TrustTradeListViewModel

//交易紀錄-進行中
class TrustTradeListFragment : ParentFragment(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var viewModel: TrustTradeListViewModel
    private lateinit var binding: TrustTradeListFragmentBinding
    private var isDone = false  //是否已完成清單


    companion object {
        fun newIntace(isDone:Boolean): TrustTradeListFragment {
            var fragment = TrustTradeListFragment()
            var bundle = Bundle()
            bundle.putBoolean("isDone", isDone)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onResume() {
        super.onResume()
        initData()
    }

    override fun initData() {
        viewModel?.loadAccountsData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.trust_trade_list_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(TrustTradeListViewModel::class.java)
        viewModel.binding = binding
        viewModel.isDone = arguments!!.getBoolean("isDone")
        viewModel.act = (activity as ParentActivity?)!!

        viewModel.initView()

        return binding.root
    }

    override fun onRefresh() {
        binding.swpLayout.isRefreshing = true
        viewModel.multiAdapter.clearItems()
        viewModel.loadAccountsData()

    }

}
