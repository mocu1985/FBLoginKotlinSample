package epost.android.mitake.com.fbloginkotlinsample

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import epost.android.mitake.com.fbloginkotlinsample.adapter.SectionsPagerAdapter
import epost.android.mitake.com.fbloginkotlinsample.fragment.ProfileEditFragment
import epost.android.mitake.com.fbloginkotlinsample.fragment.ProfileListFragment
import kotlinx.android.synthetic.main.activity_main_tab.*

class MainTabActivity : AppCompatActivity() {

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
        mSectionsPagerAdapter!!.addFragment(ProfileListFragment())
        mSectionsPagerAdapter!!.addFragment()

        // Set up the ViewPager with the sections adapter.
        view_pager.adapter = mSectionsPagerAdapter


//        view_pager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        view_pager.addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabs) {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mSectionsPagerAdapter!!.getFragmentList().get(position).initData()
            }
        })

        mSectionsPagerAdapter!!.getFragmentList().get(0).initData()

//        view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
//            override fun onPageScrollStateChanged(state: Int) {
//            }
//
//            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
//            }
//
//            override fun onPageSelected(position: Int) {
//                mSectionsPagerAdapter!!.getFragmentList().get(position).initData()
//            }
//
//        })

        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(view_pager))
    }
}
