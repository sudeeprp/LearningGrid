package com.thinklearn.tide.learninggrid

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.mybrowser)
        webView.settings.javaScriptEnabled = true
        webView.settings.allowFileAccessFromFileURLs = true
        webView.addJavascriptInterface(ActivityInterface(this), "Android")
        webView.webViewClient = WebViewClient()
        webView.loadUrl("file:///android_asset/French-Grade1.html")
    }

    override fun onBackPressed() {
        val webView = findViewById<WebView>(R.id.mybrowser)
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}

class ActivityInterface {
    lateinit var con: MainActivity

    constructor(con: MainActivity) {
        this.con = con
    }
    @JavascriptInterface
    fun activityResult(datapoint: String) {
        Toast.makeText(con, datapoint, Toast.LENGTH_SHORT).show()
        val webView = con.findViewById<WebView>(R.id.mybrowser)
        webView.post { webView.goBack() }
    }
}
