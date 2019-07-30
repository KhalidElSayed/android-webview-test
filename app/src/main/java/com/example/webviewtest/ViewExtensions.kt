package com.example.webviewtest

import android.app.Activity
import android.graphics.Rect
import android.util.Log
import android.view.View

fun View.onKeyboardOpenListener(opened: () -> Unit, closed: () -> Unit) {
    viewTreeObserver.addOnGlobalLayoutListener {
        if (isKeyboardShown()) {
            opened.invoke()
        } else {
            closed.invoke()
        }
    }
}

fun Activity.onKeyboardOpenListener(opened: () -> Unit, closed: () -> Unit = {}) {
    val decorView = window.decorView
    decorView.viewTreeObserver.addOnGlobalLayoutListener {
        if (decorView.isKeyboardShown()) {
            opened.invoke()
        } else {
            closed.invoke()
        }
    }
}

fun View.isKeyboardShown(): Boolean {
    /* 128dp = 32dp * 4, minimum button height 32dp and generic 4 rows soft keyboard */
    val SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD = 128;

    val rect = Rect()
    rootView.getWindowVisibleDisplayFrame(rect)
    val dm = rootView.getResources().getDisplayMetrics()
    /* heightDiff = rootView height - status bar height (r.top) - visible frame height (r.bottom - r.top) */
    val heightDiff = rootView.getBottom() - rect.bottom
    /* Threshold size: dp to pixels, multiply with display density */
    val isKeyboardShown = heightDiff > SOFT_KEYBOARD_HEIGHT_DP_THRESHOLD * dm.density

    Log.d("ViewExtensions", "isKeyboardShown? $isKeyboardShown, heightDiff: $heightDiff, " +
            "density: ${dm.density}, root view height: ${rootView.getHeight()}, rect:$rect")

    return isKeyboardShown
}

fun Activity.readFromAsset(fileName: String): String {
    return assets.open(fileName).bufferedReader().use{
        return@use it.readText()
    }
}