package epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.trade

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import epost.android.mitake.com.fbloginkotlinsample.fragment.setting.ui.main.trade.ui.trusttrade.TrustTradeFragment

class TrustTradeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.trust_trade_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, TrustTradeFragment.newInstance())
                .commitNow()
        }
    }

}
