package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.ruling

import android.os.Bundle
import epost.android.mitake.com.fbloginkotlinsample.R
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.ruling.ui.trusttradedetail.TrustTradeDetailFragment
import epost.android.mitake.com.fbloginkotlinsample.framework.TitleBarParentActivity
import kotlinx.android.synthetic.main.activity_share_titlebar_content.*

class TrustTradeDetailActivity : TitleBarParentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrustTradeDetailFragment.newInstance())
                .commitNow()
        }
    }

    override fun initTitleBar() {
        title_bar.centerTextView.text = getString(R.string.order_detail)
    }

}
