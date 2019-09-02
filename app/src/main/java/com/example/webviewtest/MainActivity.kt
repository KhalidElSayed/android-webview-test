package com.example.webviewtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main_simple.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_simple)

        val html = readFromAsset("content/pages/preview_sample.html")
        val htmlTemplate = "<html><head></head><body><img src=\"file:///android_asset/content/svg/q1_c.svg\"><p>Hello Webview.</p></body></html>"

        webView.addJavascriptInterface(JsInterface(this) , "jsInterface")
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        webView.loadUrl("file:///android_asset/content/pages/preview_sample.html")
//        webView.loadDataWithBaseURL("file:///android_asset/content/", html, "text/html", "UTF-8", null)

//        webView.loadDataWithBaseURL(null, htmlTemplate, "text/html", "utf-8",null);

//        val webviewPosition = webView.getLocationInWindow()

        /*onKeyboardOpenListener(opened = {
            Observable.fromCallable {
                val webviewPosition = linerLayout.getLocationOnScreen().y
                val offest = 8

                webView.evaluateJavascript("getElementPositionById()") { value ->
                    scrollView.scrollTo(0, webviewPosition + value!!.toInt() + offest)
                    Toast.makeText(this@MainActivity, "input position is: $value", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity, "webView position is: $webviewPosition", Toast.LENGTH_SHORT).show()
                }
            }.blockingFirst()
        })*/
    }
}
