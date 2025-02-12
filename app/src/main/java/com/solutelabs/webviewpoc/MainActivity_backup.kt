//package com.solutelabs.webviewpoc
//
//import android.os.Bundle
//import android.view.View
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
//
//
//class MainActivity_backup : AppCompatActivity() {
//
//    val spinnerValues = arrayOf(10, 15, 20, 25, 30, 35, 40)
//    lateinit var myWebView: WebView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        setspinner()
//        setWebView()
//        setThemeSwitch()
//    }
//
//    private fun setWebView() {
//        myWebView = findViewById(R.id.my_web_view)
//
//        myWebView.getSettings().setJavaScriptEnabled(true)
//        myWebView.getSettings().setLoadWithOverviewMode(true)
//        myWebView.getSettings().setUseWideViewPort(true)
//
//        myWebView.getSettings().setUserAgentString(getString(R.string.useragentstring))
//
//        myWebView.settings.defaultFontSize = spinnerValues[0]
////        myWebView.setWebViewClient(object : WebViewClient() {
////            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
////                //progDailog.show()
////                view.loadUrl(url)
////                return true
////            }
////
////            override fun onPageFinished(view: WebView, url: String) {
////                //progDailog.dismiss()
////            }
////        })
//
////        var link = """https://www.ndtvprofit.com/widget/markets"""
////        var link = "https://deccanherald-web.qtstage.io/india/ram-mandir-immersive-415?app=true"
//        var link = "https://deccanherald-web.qtstage.io/india/andhra-pradesh/all-elements-budget-2024-armed-with-rbis-bonanza-government-likely-planning-a-feel-good-budget-sacyyauwvc-366?app=true"
////        var link = "https://prajavani-training-web.quintype.io/sports-page/sports-extra/test-sponsored-story-25th-march-2024-1350843"
//        myWebView.loadUrl(link)
//
//    }
//
//    private fun setThemeSwitch() {
//        val themeSwitch = findViewById<Switch>(R.id.theme_switch)
//        themeSwitch.setOnCheckedChangeListener { _: CompoundButton, isChecked: Boolean ->
//            if (isChecked) {
//                enableDarkMode()
//            } else {
//                disableDarkMode()
//            }
//        }
//    }
//
//    private fun enableDarkMode() {
//        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
//            WebSettingsCompat.setForceDark(myWebView.settings, WebSettingsCompat.FORCE_DARK_ON)
//        }
//    }
//
//    private fun disableDarkMode() {
//        if (WebViewFeature.isFeatureSupported(WebViewFeature.FORCE_DARK)) {
//            WebSettingsCompat.setForceDark(myWebView.settings, WebSettingsCompat.FORCE_DARK_OFF)
//        }
//    }
//
//    private fun setspinner() {
//
//        val spinner = findViewById<Spinner>(R.id.spinner)
//
//        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerValues)
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//
//        spinner.adapter = adapter
//
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
//                val selectedValue = spinnerValues[position]
//                myWebView.settings.defaultFontSize = selectedValue
////                Toast.makeText(this@MainActivity, "Selected Font Size -> $selectedValue", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>) {
//            }
//        }
//    }
//
//}