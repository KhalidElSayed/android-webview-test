package com.example.webviewtest

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, WebActivity::class.java)

        btnCurrent.setOnClickListener {
            intent.putExtra(PAGE_URL, CURRENT_PAGE_URL)
            startActivity(intent)
        }

        btnNewImg.setOnClickListener {
            intent.putExtra(PAGE_URL, NEW_PAGE_URL)
            startActivity(intent)
        }

        btnNewObject.setOnClickListener {
            intent.putExtra(PAGE_URL, NEW_PAGE_OBJECT_URL)
            startActivity(intent)
        }

        btnNewPng.setOnClickListener {
            intent.putExtra(PAGE_URL, NEW_PAGE_TINY_PNG_URL)
            startActivity(intent)
        }

        btnNewSvg.setOnClickListener {
            intent.putExtra(PAGE_URL, NEW_PAGE_EXTERNAL_SVG_URL)
            startActivity(intent)
        }
    }

    companion object {
        private const val PAGE_URL = "page_url"
        private const val CURRENT_PAGE_URL = "https://beta-nagwa-media.s3.amazonaws.com/gabr/11_q_assessment.html"
        private const val NEW_PAGE_URL = "https://beta-nagwa-media.s3.amazonaws.com/gabr/new+design.htm"
        private const val NEW_PAGE_OBJECT_URL = "file:///android_asset/content/pages/new_object.html"
        private const val NEW_PAGE_PNG_URL = "https://beta-nagwa-media.s3.amazonaws.com/gabr/new+design+with+png.htm"
        private const val NEW_PAGE_TINY_PNG_URL = "https://beta-nagwa-media.s3.amazonaws.com/gabr/new+design+with+tinypng.htm"
        private const val NEW_PAGE_EXTERNAL_SVG_URL = "https://www.nagwa.com/en/worksheets/593162506870/"
    }
}
