package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.userprofile.myruling

import android.os.Bundle
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.userprofile.myruling.ui.myruling.MyRulingFragment
import epost.android.mitake.com.fbloginkotlinsample.framework.TitleBarParentActivity
import kotlinx.android.synthetic.main.activity_share_titlebar_content.*

class MyRulingActivity : TitleBarParentActivity() {
    override fun initTitleBar() {
        title_bar.centerTextView.text = "我的申訴"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MyRulingFragment.newInstance())
                .commitNow()
        }
    }

}
