package epost.android.mitake.com.fbloginkotlinsample.framework

import android.support.v4.app.Fragment

abstract class ParentFragment : Fragment() {

    override fun onResume() {
        super.onResume()
        initData()
    }

    //延遲TabLayout的fragment初始化
    abstract fun initData()

}