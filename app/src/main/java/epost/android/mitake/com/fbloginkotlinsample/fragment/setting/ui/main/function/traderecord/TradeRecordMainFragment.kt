package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.traderecord

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.adapter.SectionsPagerAdapter
import epost.android.mitake.com.fbloginkotlinsample.databinding.TradeRecordMainFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentFragment
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.TradeRecordMainViewModel
import kotlinx.android.synthetic.main.activity_main_tab.*

//交易紀錄主畫面
class TradeRecordMainFragment : ParentFragment() {
    override fun initData() {

    }

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private lateinit var binding:TradeRecordMainFragmentBinding
    private lateinit var viewModel: TradeRecordMainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.trade_record_main_fragment, container, false)

        viewModel = ViewModelProviders.of(this).get(TradeRecordMainViewModel::class.java)

        mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)
        mSectionsPagerAdapter!!.addFragment(TrustTradeListFragment.newIntace(false))
        mSectionsPagerAdapter!!.addFragment(TrustTradeListFragment.newIntace(true))
        binding.viewPager.adapter = mSectionsPagerAdapter

        binding.viewPager.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabs) {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mSectionsPagerAdapter!!.getFragmentList().get(position).initData()
            }
        })


        binding.tabs.setupWithViewPager(binding.viewPager)
        //解決setupWithViewPager會removeAllTabs，造成tab空白
        binding.tabs.getTabAt(0)!!.text = "進行中"
        binding.tabs.getTabAt(1)!!.text = "已完成"

        binding.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(binding.viewPager))

        return binding.root
    }

}
