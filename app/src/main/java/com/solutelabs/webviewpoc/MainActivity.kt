package com.solutelabs.webviewpoc

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {

    lateinit var myWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setWebView()
    }

    private fun setWebView() {
        myWebView = findViewById(R.id.consumptionWebView)

        myWebView.getSettings().setJavaScriptEnabled(true)
        myWebView.getSettings().setLoadWithOverviewMode(true)
        myWebView.getSettings().setUseWideViewPort(true)
        myWebView.settings.domStorageEnabled = true
        myWebView.settings.cacheMode = WebSettings.LOAD_DEFAULT

        myWebView.settings.mediaPlaybackRequiresUserGesture = false
        myWebView.settings.blockNetworkImage = false
        myWebView.settings.loadsImagesAutomatically = true


        object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                view.loadUrl(url)
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                logWithTimestamp("onPageStarted")

            }

            override fun onReceivedHttpError(
                view: WebView?,
                request: WebResourceRequest?,
                errorResponse: WebResourceResponse
            ) {
                super.onReceivedHttpError(view, request, errorResponse)
                Log.i("hello", "onReceivedHttpError: $errorResponse")
            }

            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError
            ) {
                super.onReceivedSslError(view, handler, error)
                Log.i("hello", "onReceivedSslError: $error")
            }

            override fun onReceivedError(
                view: WebView?,
                errorCode: Int,
                description: String,
                failingUrl: String?
            ) {
                super.onReceivedError(view, errorCode, description, failingUrl)
                Log.i("hello", "onReceivedError: $description")
            }

            override fun shouldInterceptRequest(
                view: WebView?,
                request: WebResourceRequest?
            ): WebResourceResponse? {
                Log.i("hello","shouldInterceptRequest")
                return super.shouldInterceptRequest(view, request)

            }

            override fun onPageFinished(view: WebView, url: String) {
                logWithTimestamp("Permissions Policy Applied")
                super.onPageFinished(view, url)
            }
        }.also { myWebView.webViewClient = it }

        myWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                Log.i("hello","newProgress: $newProgress")
            }
        }
        var link = """https://www.deccanherald.com/india/india-politics-live-latest-bjp-narendra-modi-congress-rahul-gandhi-amit-shah-nda-maha-yuti-delhi-assembly-elections-2025-punjab-aap-arvind-kejriwal-atishi-manipur-biren-singh-punjab-france-us-macron-trump-news-updates-3399540?app=true#7"""

        myWebView.loadUrl(link)
        logWithTimestamp("Load The Link")
        Toast.makeText(this@MainActivity, "Loading Done", Toast.LENGTH_SHORT).show()

    }



    fun logWithTimestamp(message: String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentTime = dateFormat.format(Date())
        Log.d("logWithTimestamp", "[$currentTime] $message")
    }


}