//package com.solutelabs.webviewpoc
//
//import android.graphics.Bitmap
//import android.os.Bundle
//import android.util.Log
//import android.view.View
//import android.webkit.WebChromeClient
//import android.webkit.WebResourceRequest
//import android.webkit.WebResourceResponse
//import android.webkit.WebSettings
//import android.webkit.WebView
//import android.webkit.WebViewClient
//import android.widget.AdapterView
//import android.widget.ArrayAdapter
//import android.widget.CompoundButton
//import android.widget.Spinner
//import android.widget.Switch
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import androidx.webkit.WebSettingsCompat
//import androidx.webkit.WebViewFeature
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//
//
//class MainActivity : AppCompatActivity() {
//
//    val spinnerValues = arrayOf(10, 15, 20, 25, 30, 35, 40,80,100,120,200,400,500,700,1000)
//    lateinit var myWebView: WebView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
////        setspinner()
//        setWebView()
////        setThemeSwitch()
//    }
//
//    private fun setWebView() {
//        myWebView = findViewById(R.id.consumptionWebView)
//
//        myWebView.getSettings().setJavaScriptEnabled(true)
//        myWebView.getSettings().setLoadWithOverviewMode(true)
//        myWebView.getSettings().setUseWideViewPort(true)
//
//
////        myWebView.getSettings().setUserAgentString(getString(R.string.useragentstring))
//        myWebView.settings.mediaPlaybackRequiresUserGesture = false
//
//
////        myWebView.settings.defaultFontSize = spinnerValues[0]
//
//
//        myWebView.webViewClient = object : WebViewClient() {
//            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
//                view.loadUrl(url)
//                return true
//            }
//
//            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
//                super.onPageStarted(view, url, favicon)
//                logWithTimestamp("onPageStarted")
//
//            }
//
//            override fun shouldInterceptRequest(
//                view: WebView?,
//                request: WebResourceRequest?
//            ): WebResourceResponse? {
//                Log.i("hello","shouldInterceptRequest")
//                return super.shouldInterceptRequest(view, request)
//
//            }
//
//            override fun onPageFinished(view: WebView, url: String) {
//                logWithTimestamp("onPageFinished")
//
////                val selectedValue = spinnerValues[5]
////                myWebView.settings.defaultFontSize = selectedValue
////                injectFontSizeScript(selectedValue)
////                Toast.makeText(this@MainActivity, "Page Loaded", Toast.LENGTH_SHORT).show()
//            }
//        }
//
//        myWebView.webChromeClient = object : WebChromeClient() {
//            override fun onProgressChanged(view: WebView?, newProgress: Int) {
//                super.onProgressChanged(view, newProgress)
//                Log.i("hello","newProgress: $newProgress")
//                if (newProgress > 10) {
//                    val selectedValue = spinnerValues[1]
////                    myWebView.settings.defaultFontSize = selectedValue
////                    injectFontSizeScript(selectedValue)
//                }
//            }
//        }
//
////        var link = """https://www.deccanherald.com/india/siemens-healthineers-gets-nod-to-manufacture-mpox-detection-rt-pcr-kit-in-india-3166935?app=true&font-size=16"""
//
//
////        var link = """https://www.deccanherald.com/india/gujarat/watch-crocodile-seen-on-roof-of-house-as-rains-inundate-gujarat-3168573?app=true"""
////        var link = """https://www.deccanherald.com/india/west-bengal/rg-kar-doctor-rape-murder-case-latest-news-live-updates-supreme-court-hearing-sandip-ghosh-cbi-ed-sanjoy-roy-tmc-bjp-mamata-banerjee-west-bengal-kolkata-3182123?app=true"""
//        var link = """https://www.deccanherald.com/india/india-politics-live-latest-bjp-narendra-modi-congress-rahul-gandhi-amit-shah-nda-maha-yuti-delhi-assembly-elections-2025-punjab-aap-arvind-kejriwal-atishi-manipur-biren-singh-punjab-france-us-macron-trump-news-updates-3399540?app=true#7"""
//
//
//
////        var link = """https://deccanherald-web.qtstage.io/india/karnataka/bengaluru/test-video-story-empty-space-issue-jask-474?app=true"""
////        var link = """https://deccanherald-web.qtstage.io/india/karnataka/bengaluru/test-podcast-story-podcast-player-testing-cvsccvwd-490?app=true"""
////        var link = """https://www.ndtvprofit.com/stock/914349/hindustan-copper-ltd"""
////        var link = """https://www.ndtvprofit.com/widget/markets"""
////        var link = """https://www.deccanherald.com/india/maharashtra/aimim-offers-to-join-hands-with-mva-to-defeat-bjp-in-maharashtra-assembly-polls-3156674?app=true"""
////        var link = """https://www.deccanherald.com/india/karnataka/ashoka-for-prez-rule-says-it-s-free-rein-for-cong-workers-3156388?app=true"""
////        var link = "https://deccanherald-web.qtstage.io/india/ram-mandir-immersive-415?app=true"
////        var link = "https://deccanherald-web.qtstage.io/india/andhra-pradesh/all-elements-budget-2024-armed-with-rbis-bonanza-government-likely-planning-a-feel-good-budget-sacyyauwvc-366?app=true"
////        var link = "https://prajavani-training-web.quintype.io/sports-page/sports-extra/test-sponsored-story-25th-march-2024-1350843"
//        myWebView.loadUrl(link)
//        logWithTimestamp("Load The Link")
//        Toast.makeText(this@MainActivity, "Loading Done", Toast.LENGTH_SHORT).show()
//
//    }
////
////    private fun setThemeSwitch() {
////        val themeSwitch = findViewById<Switch>(R.id.theme_switch)
////        themeSwitch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
////            if (isChecked) {
////                enableDarkMode()
////            } else {
////                disableDarkMode()
////            }
////        }
////    }
//
////    private fun enableDarkMode() {
////        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
////            WebSettingsCompat.setForceDark(myWebView.settings, WebSettingsCompat.FORCE_DARK_ON)
////        }
////    }
////
////    private fun disableDarkMode() {
////        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
////            WebSettingsCompat.setForceDark(myWebView.settings, WebSettingsCompat.FORCE_DARK_OFF)
////        }
////    }
//
////    private fun setspinner() {
////
////        val spinner = findViewById<Spinner>(R.id.spinner)
////
////        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerValues)
////        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
////
////        spinner.adapter = adapter
////
////        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
////            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
////                val selectedValue = spinnerValues[position]
//////                injectFontSizeScript(selectedValue)
////
////                myWebView.settings.defaultFontSize = selectedValue
//////                Toast.makeText(this@MainActivity, "Selected Font Size -> $selectedValue", Toast.LENGTH_SHORT).show()
////            }
////
////            override fun onNothingSelected(parent: AdapterView<*>) {
////            }
////        }
////
////
////    }
//
////    private fun injectFontSizeScript(fontSize: Int) {
////        val jsCode = """
////            document.querySelectorAll('body *').forEach(function(node) {
////                if (node.style) {
////                    node.style.fontSize = '${fontSize}px';
////                }
////            });
////            document.documentElement.scrollHeight;
////        """
////        myWebView.evaluateJavascript(jsCode, null)
////    }
//
//    fun logWithTimestamp(message: String) {
//        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
//        val currentTime = dateFormat.format(Date())
//        Log.d("logWithTimestamp", "[$currentTime] $message")
//    }
//
//
//}