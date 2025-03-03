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
    lateinit var ivNewsImage: RelativeLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // setWebView()
        renderWebview()
    }

    private fun renderWebview() {
        webView = findViewById(R.id.consumptionWebView)
        ivNewsImage = findViewById(R.id.scrollingRelativeLayout)

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

                Log.i("hello","ivNewsImage.measuredHeight: ${ivNewsImage.measuredHeight}")
                Log.i("hello","view.height: ${view?.height}")

                ivNewsImage.post {
                    val fullHeightPx = ivNewsImage.measuredHeight // Height in pixels
                    val fullHeightDp = pxToDp(fullHeightPx) // Convert to DP
                    injectCSS(view, fullHeightDp)
                }
            }
        }

        val url="""https://www.deccanherald.com/india/indian-political-updates-live-latest-news-congress-bjp-aap-shiv-sena-ubt-tmc-rahul-gandhi-narendra-modi-jairam-ramesh-bihar-punjab-telangana-shashi-tharoor-andhra-pradesh-legislative-council-election-m-k-stalin-3423781?app=true"""
        webView.loadUrl(url)


    }

    private fun pxToDp(px: Int): Int {
        val density = resources.displayMetrics.density
        return (px / density).toInt() // Convert to DP
    }


    private fun injectCSS(view: WebView?, height: Int) {
        val css = "body { margin-top: ${height}px !important; }" // Use dynamic height
        val js = """
        var style = document.createElement('style');
        style.type = 'text/css';
        style.innerHTML = '$css';
        document.head.appendChild(style);
    """.trimIndent()

        view?.evaluateJavascript(js, null)
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
    }



    fun logWithTimestamp(message: String) {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val currentTime = dateFormat.format(Date())
        Log.d("logWithTimestamp", "[$currentTime] $message")
    }


}