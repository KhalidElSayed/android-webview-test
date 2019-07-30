package com.example.webviewtest

import android.content.Context
import android.webkit.JavascriptInterface
import android.widget.Toast
import java.text.FieldPosition


class JsInterface(val context: Context) {

    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
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