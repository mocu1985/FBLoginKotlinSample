package epost.android.mitake.com.fbloginkotlinsample.framework

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.wuhenzhizao.titlebar.widget.CommonTitleBar
import epost.android.mitake.com.fbloginkotlinsample.R
import kotlinx.android.synthetic.main.activity_trust_trade.*

abstract class TitleBarParentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_titlebar_content)
    }

    override fun onStart() {
        super.onStart()
        initTitleBar()
        initTitleBarClick()
    }

    //Title Bar事件監聽
    private fun initTitleBarClick() {
        title_bar.setListener(object : CommonTitleBar.OnTitleBarListener {
            override fun onClicked(v: View?, action: Int, extra: String?) {
                when (action) {
                    CommonTitleBar.ACTION_LEFT_BUTTON -> onBackPressed()
                }
            }

        })
    }

    //Title Bar顯示初始化
    abstract fun initTitleBar()

}