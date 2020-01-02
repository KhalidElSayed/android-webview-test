package com.example.webviewtest

import android.content.Context
import android.util.Log
import android.webkit.JavascriptInterface
import android.widget.Toast
import java.text.FieldPosition
import kotlin.math.log


class JsInterface(val context: Context) {

    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun showLog(text: String) {
        Log.e("TAG: jsInterface", text)
    }

    @JavascriptInterface
    fun getAndroidVersion(): Int {
        return android.os.Build.VERSION.SDK_INT
    }

    @JavascriptInterface
    fun getInputPosition(position: String) {
        Toast.makeText(context, "position is: $position", Toast.LENGTH_SHORT).show()
    }
}