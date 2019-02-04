package epost.android.mitake.com.fbloginkotlinsample.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class RulingDetailAdapter : FragmentPagerAdapter {

    lateinit var fragmentList: List<Fragment>

    constructor(fm: FragmentManager?, fragmentList: List<Fragment>) : super(fm) {
        this.fragmentList = fragmentList
    }


    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        return fragmentList.size
    }
}