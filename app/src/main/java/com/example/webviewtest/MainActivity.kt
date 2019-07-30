package com.example.webviewtest

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val html = readFromAsset("content/pages/index.html")

        webView.addJavascriptInterface(JsInterface(this) , "jsInterface")
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()
        //webView.loadUrl("file:///android_asset/content/pages/index.html")
        webView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)

        onKeyboardOpenListener(opened = {
            Observable.fromCallable {
                webView.evaluateJavascript("getElementPositionById()", object : ValueCallback<String> {
                    override fun onReceiveValue(value: String?) {
                        Toast.makeText(this@MainActivity, "now position is: $value", Toast.LENGTH_SHORT).show()
                        scrollView.scrollTo(0, value!!.toInt())
                    }
                })
            }.blockingFirst()
        })
    }
}
