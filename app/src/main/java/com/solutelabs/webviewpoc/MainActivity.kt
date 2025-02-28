package com.solutelabs.webviewpoc

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {

    lateinit var myWebView: WebView
    lateinit var webView: CustomWebView
    lateinit var baseContainer: RelativeLayout
    lateinit var ivNewsImage: ImageView
    //lateinit var baseTitleContainer: CardView

    //lateinit var consumptionNestedScrollView: NestedScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // setWebView()
        renderWebview()
    }

    private fun renderWebview() {
        //consumptionNestedScrollView = findViewById(R.id.consumptionNestedScrollView)
        webView = findViewById(R.id.consumptionWebView)
        ivNewsImage = findViewById(R.id.ivNewsImage)
        //baseTitleContainer = findViewById(R.id.baseTitleContainer)
      //  baseContainer = findViewById(R.id.baseContainer)

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


        //webView.isNestedScrollingEnabled = false


        webView.webViewClient = WebViewClient()

        webView.setOnScrollChangeListener { v, scrollX, scrollY, oldScrollX, oldScrollY ->
            ivNewsImage.scrollTo(scrollX, scrollY)
           // baseTitleContainer.scrollTo(scrollX,scrollY)
        }
        // Set the scroll listener



        webView.webViewClient = object : android.webkit.WebViewClient() {
            override fun onPageFinished(view: android.webkit.WebView?, url: String?) {
                super.onPageFinished(view, url)

                // Inject CSS to add top margin to the body
                val css = "body { margin-top: 250px !important; }" // Adjust the margin value as needed
                val js = """
                    var style = document.createElement('style');
                    style.type = 'text/css';
                    style.innerHTML = '$css';
                    document.head.appendChild(style);
                """.trimIndent()

                view?.evaluateJavascript(js, null)
            }
        }


        // Load the URL
        //val url = "https://www.deccanherald.com/india/india-politics-live-latest-bjp-narendra-modi-congress-rahul-gandhi-amit-shah-nda-maha-yuti-delhi-assembly-elections-2025-punjab-aap-arvind-kejriwal-atishi-manipur-biren-singh-punjab-france-us-macron-trump-news-updates-3399540?app=true"
        //With Twitter
        //val url = """https://deccanherald-web.qtstage.io/india/andhra-pradesh/text-story-with-twitter-embed-scascs-880?app=true"""
        //Without Twitter
        //val url = """https://deccanherald-web.qtstage.io/india/andhra-pradesh/text-story-without-twitter-embed-scsacs-879?app=true"""
        // Live URL
        val url="""https://www.deccanherald.com/india/indian-political-updates-live-latest-news-congress-bjp-aap-shiv-sena-ubt-tmc-rahul-gandhi-narendra-modi-jairam-ramesh-bihar-punjab-telangana-shashi-tharoor-andhra-pradesh-legislative-council-election-m-k-stalin-3423781?app=true"""
        webView.loadUrl(url)


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
        myWebView = findViewById(R.id.consumptionWebView)

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