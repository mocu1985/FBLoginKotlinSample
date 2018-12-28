package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.function.trusttrade

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.wuhenzhizao.titlebar.widget.CommonTitleBar
import epost.android.mitake.com.fbloginkotlinsample.R
import kotlinx.android.synthetic.main.activity_trust_trade.*

class TrustTradeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trust_trade)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrustTradeFragment.newInstance())
                .commitNow()
        }
    }


    override fun onStart() {
        super.onStart()

        title_bar.setListener(object : CommonTitleBar.OnTitleBarListener{
            override fun onClicked(v: View?, action: Int, extra: String?) {
                when (action) {
                    CommonTitleBar.ACTION_LEFT_BUTTON -> onBackPressed()
                }
            }

        })
    }

}
