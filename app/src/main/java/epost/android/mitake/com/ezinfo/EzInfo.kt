package epost.android.mitake.com.ezinfo

import android.net.http.SslError
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
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
        wb_view!!.webViewClient = object : WebViewClient() {
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                super.onReceivedSslError(view, handler, error)
                Log.e("*******", error.toString())
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }

        wb_view.loadUrl(url)
    }

}
