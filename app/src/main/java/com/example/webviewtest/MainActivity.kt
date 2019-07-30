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

//        val webviewPosition = webView.getLocationInWindow()

        onKeyboardOpenListener(opened = {
            Observable.fromCallable {
                val screenPos = IntArray(2)
                linerLayout.getLocationOnScreen(screenPos)
                val webviewPosition = screenPos[1] // [1] for Y position of the bottom
                val offest = 8

                webView.evaluateJavascript("getElementPositionById()") { value ->
                    scrollView.scrollTo(0, webviewPosition + value!!.toInt() + offest)
                    Toast.makeText(this@MainActivity, "input position is: $value", Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity, "webView position is: $webviewPosition", Toast.LENGTH_SHORT).show()
                }
            }.blockingFirst()
        })
    }
}
