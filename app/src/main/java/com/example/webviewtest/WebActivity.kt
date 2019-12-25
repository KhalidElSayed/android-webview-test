package com.example.webviewtest

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.linerLayout
import kotlinx.android.synthetic.main.activity_web.*
import org.jsoup.Jsoup
import java.util.*


class WebActivity : AppCompatActivity() {

    lateinit var startDate: Date
    lateinit var finishDate: Date

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)


        val pageUrl: String = intent.extras.getString(PAGE_URL)

        // We can use the Jsoup parser here to manipulate the html file before load it by the webview.
//        val html = Jsoup.connect(pageUrl).get().html()
//        val html = readFromAsset("content/pages/index.html")

        webView.addJavascriptInterface(JsInterface(this) , "jsInterface")
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                startDate = Date()
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                finishDate = Date()

                val loadTime = (finishDate.time - startDate.time) / 1000 // convert it to seconds

                AlertDialog.Builder(this@WebActivity)
                    .setTitle("Page load time")
                    .setMessage("Your page has been loaded in $loadTime seconds.")
                    .setPositiveButton("Ok", null)
                    .setCancelable(false)
                    .create()
                    .show()
            }
        }
        webView.loadUrl(pageUrl)
//        webView.loadUrl("file:///android_asset/content/pages/index.html")
//        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)


//        val webviewPosition = webView.getLocationInWindow()

        /*onKeyboardOpenListener(opened = {
            Observable.fromCallable {
                val webviewPosition = linerLayout.getLocationOnScreen().y
                val offest = 8

                webView.evaluateJavascript("getElementPositionById()") { value ->
                    scrollView.scrollTo(0, webviewPosition + value!!.toInt() + offest)
                    Toast.makeText(this@WebActivity, "input position is: $value", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@WebActivity, "webView position is: $webviewPosition", Toast.LENGTH_SHORT).show()
                }
            }.blockingFirst()
        })*/
    }

    companion object {
        private const val PAGE_URL = "page_url"
    }
}
