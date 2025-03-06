package com.solutelabs.webviewpoc

import android.os.Bundle
import android.util.Log
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable


import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


class MainActivity : AppCompatActivity() {

    lateinit var myWebView: WebView
    lateinit var webView: WebView
    lateinit var baseContainer: RelativeLayout
    lateinit var consumption_nested_layout: LinearLayout
    //lateinit var consumptionNestedScrollView: NestedScrollView
var isLoad=true;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContent {
            MyComposeUI()
        }
       // setWebView()
        //renderWebview()
    }




    

    @Composable
    fun MyComposeUI() {
        MaterialTheme {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Welcome to Jetpack Compose!")
                Button(onClick = { /* TODO */ }) {
                    Text("Click Me")
                }
            }
        }
    }
    
    

    private fun renderWebview() {
        //consumptionNestedScrollView = findViewById(R.id.consumptionNestedScrollView)
        //webView = findViewById(R.id.consumptionWebView)
        consumption_nested_layout = findViewById(R.id.consumption_nested_layout)


        webView= WebView(this)
        val layoutParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        webView.setLayoutParams(layoutParams);

        webView.settings.apply {
          /*  javaScriptEnabled = true
            domStorageEnabled = true*/

            mixedContentMode = WebSettings.LOAD_DEFAULT
            allowFileAccess = true
            javaScriptEnabled = true // Enable JavaScript
            domStorageEnabled = true // Improve performance
            useWideViewPort = true // Enable better layout rendering
            loadWithOverviewMode = true // Fit content properly
            setSupportZoom(false) // Disable zooming
            builtInZoomControls = false // Disable pinch-to-zoom
            displayZoomControls = false // Hide zoom controls
        }


        webView.isNestedScrollingEnabled = false


        // Handle redirects within the WebView
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                return false // Ensures all URLs open inside the WebView
            }


            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                Log.e("URL","Loading URL --- ${view?.url}")

              /*  val twitterScript =
                    "<script async src=\"https://platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>"
                view?.loadUrl(
                    "javascript:(function() { " +
                            "var parent = document.getElementsByTagName('head').item(0); " +
                            "var script = document.createElement('script'); " +
                            "script.type = 'text/javascript'; " +
                            "script.innerHTML = '" + twitterScript + "'; " +
                            "parent.appendChild(script)" +
                            "})()"
                )
                view!!.postDelayed({
                    val params = view!!.layoutParams
                    params.height = ViewGroup.LayoutParams.MATCH_PARENT
                    view!!.layoutParams = params
                }, 500)
*/


            }
        }

        // Load the URL
        //val url = "https://www.deccanherald.com/india/india-politics-live-latest-bjp-narendra-modi-congress-rahul-gandhi-amit-shah-nda-maha-yuti-delhi-assembly-elections-2025-punjab-aap-arvind-kejriwal-atishi-manipur-biren-singh-punjab-france-us-macron-trump-news-updates-3399540?app=true"
        //With Twitter
        val url = """https://deccanherald-web.qtstage.io/india/andhra-pradesh/text-story-with-twitter-embed-scascs-880?app=true"""
        //Without Twitter
        //val url = """https://deccanherald-web.qtstage.io/india/andhra-pradesh/text-story-without-twitter-embed-scsacs-879?app=true"""
    runOnUiThread {
       /* webView.loadUrl(url)

        consumption_nested_layout.addView(webView)*/
    }
       // webView.loadUrl("file:///android_asset/myweb.html");



    }
    private fun adjustWebViewHeight(webView: WebView) {
        webView.postDelayed({
            webView.evaluateJavascript("(function() { return document.body.scrollHeight; })();") { height ->
                height?.toFloatOrNull()?.toInt()?.let { newHeight ->
                    webView.layoutParams = webView.layoutParams.apply {
                        this.height = newHeight
                    }
                }
            }
        }, 1000) // Delay to ensure Twitter embeds load
    }

    private fun setWebView() {
       // myWebView = findViewById(R.id.consumptionWebView)

        myWebView.getSettings().setJavaScriptEnabled(true)
        myWebView.getSettings().setLoadWithOverviewMode(true)
        myWebView.getSettings().setUseWideViewPort(true)
        myWebView.settings.domStorageEnabled = true
        myWebView.settings.cacheMode = WebSettings.LOAD_DEFAULT

        myWebView.settings.mediaPlaybackRequiresUserGesture = false
        myWebView.settings.blockNetworkImage = false
        myWebView.settings.loadsImagesAutomatically = true


    /*    myWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                Log.e("Error","URL --- :: $url")
                Toast.makeText(this@MainActivity, url, Toast.LENGTH_SHORT).show()
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
        }*/



        myWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                Log.i("hello","newProgress: $newProgress")
            }
        }
        var link = """https://www.deccanherald.com/india/india-politics-live-latest-bjp-narendra-modi-congress-rahul-gandhi-amit-shah-nda-maha-yuti-delhi-assembly-elections-2025-punjab-aap-arvind-kejriwal-atishi-manipur-biren-singh-punjab-france-us-macron-trump-news-updates-3399540?app=true"""

        myWebView.loadUrl(link)
        Log.e("Error","URL --- Link:: $link")
        logWithTimestamp("Load The Link")
       // Toast.makeText(this@MainActivity, "Loading Done", Toast.LENGTH_SHORT).show()

    }



    fun logWithTimestamp(message: String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentTime = dateFormat.format(Date())
        Log.d("logWithTimestamp", "[$currentTime] $message")
    }


}