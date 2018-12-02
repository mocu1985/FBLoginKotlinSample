package epost.android.mitake.com.fbloginkotlinsample.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.framework.ParentFragment
import kotlinx.android.synthetic.main.fragment_main_tab.view.*

class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val fragmentList = ArrayList<ParentFragment>()

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return fragmentList.get(position)
    }

    override fun getCount(): Int {
        // Show 3 total pages.
        return fragmentList.size
    }

    fun addFragment(fragment: ParentFragment? = PlaceholderFragment.newInstance(1)) {
        fragmentList.add(fragment!!)
    }

    fun getFragmentList(): ArrayList<ParentFragment> {
        return fragmentList
    }
}

/**
 * A placeholder fragment containing a simple view.
 */
class PlaceholderFragment : ParentFragment() {
    override fun initData() {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_main_tab, container, false)
        rootView.section_label.text =
                getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        fun newInstance(sectionNumber: Int): PlaceholderFragment {
            val fragment = PlaceholderFragment()
            val args = Bundle()
            args.putInt(ARG_SECTION_NUMBER, sectionNumber)
            fragment.arguments = args
            return fragment
        }
    }
}