package epost.android.mitake.com.ezinfo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import epost.android.mitake.com.fbloginkotlinsample.R
import kotlinx.android.synthetic.main.activity_ez_info.*

class EzInfo : AppCompatActivity() {

    val url = "https://newservices.mitake.com.tw:8443/easyInfo/www/#/home/:appCode"
//    val url = "https://tw.yahoo.com/?p=us"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ez_info)


        initView()
    }

    private fun initView() {
        wb_view.settings.javaScriptEnabled = true
        wb_view.settings.domStorageEnabled = true

//        wb_view!!.webViewClient = object : WebViewClient() {
//            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
//                super.onReceivedSslError(view, handler, error)
//                Log.e("onReceivedSslError", error.toString())
//            }
//
//            override fun onPageFinished(view: WebView?, url: String?) {
//                super.onPageFinished(view, url)
//                Log.e("onPageFinished", url)
//            }
//
//
//        }

        wb_view.loadUrl(url)
    }

}
