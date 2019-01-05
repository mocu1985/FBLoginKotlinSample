package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.adapter.SectionsPagerAdapter
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.traderecord.TradeRecordMainFragment
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.userprofile.ProfileEditFragment
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentActivity
import kotlinx.android.synthetic.main.activity_main_tab.*

class MainTabActivity : ParentActivity() {

    /**
     * The [android.support.v4.view.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * [android.support.v4.app.FragmentStatePagerAdapter].
     */
    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)


        mSectionsPagerAdapter!!.addFragment(ProfileEditFragment())
        mSectionsPagerAdapter!!.addFragment(TradeRecordMainFragment())
        mSectionsPagerAdapter!!.addFragment()

        // Set up the ViewPager with the sections adapter.
        view_pager.adapter = mSectionsPagerAdapter

        view_pager.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabs) {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mSectionsPagerAdapter!!.getFragmentList().get(position).initData()
            }
        })

        mSectionsPagerAdapter!!.getFragmentList().get(0).initData()


        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager))
    }

    override fun onBackPressed() {
        ActivityCompat.finishAffinity(this)
    }
}
