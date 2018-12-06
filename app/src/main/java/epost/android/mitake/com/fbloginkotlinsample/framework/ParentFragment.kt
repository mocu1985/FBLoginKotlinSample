package epost.android.mitake.com.fbloginkotlinsample.framework

import android.support.v4.app.Fragment

abstract class ParentFragment : Fragment() {

    //延遲TabLayout的fragment初始化
    abstract fun initData()

}