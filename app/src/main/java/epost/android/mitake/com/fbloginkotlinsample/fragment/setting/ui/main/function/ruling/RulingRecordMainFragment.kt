package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.ruling

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.adapter.SectionsPagerAdapter
import epost.android.mitake.com.fbloginkotlinsample.databinding.RulingRecordMainFragmentBinding
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentFragment
import epost.android.mitake.com.fbloginkotlinsample.viewmodel.RulingRecordMainViewModel
import kotlinx.android.synthetic.main.activity_main_tab.*

class RulingRecordMainFragment : ParentFragment() {
    override fun initData() {
    }

    private lateinit var binding: RulingRecordMainFragmentBinding
    private lateinit var viewModel: RulingRecordMainViewModel
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.ruling_record_main_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(RulingRecordMainViewModel::class.java)

        mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)
        mSectionsPagerAdapter!!.addFragment(RulingNewListFrament.newInstance())
        mSectionsPagerAdapter!!.addFragment()
        binding.viewPager.adapter = mSectionsPagerAdapter

        binding.viewPager.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabs) {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mSectionsPagerAdapter!!.getFragmentList().get(position).initData()
            }
        })


        binding.tabs.setupWithViewPager(binding.viewPager)
        //解決setupWithViewPager會removeAllTabs，造成tab空白
        binding.tabs.getTabAt(0)!!.text = "最新"
        binding.tabs.getTabAt(1)!!.text = "追蹤中"

        binding.tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(binding.viewPager))


        return binding.root
    }


}
