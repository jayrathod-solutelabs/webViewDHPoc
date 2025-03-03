package com.solutelabs.webviewpoc

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {

    lateinit var myWebView: WebView
    lateinit var webView: CustomWebView
    lateinit var baseContainer: RelativeLayout
    lateinit var linearBottom: LinearLayout
    lateinit var ivNewsImage: RelativeLayout
    lateinit var relatedStories: RecyclerView
    lateinit var textViewRelated: TextView
    lateinit var nestedScrollView: NestedScrollView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       // setWebView()
        renderWebview()
    }

    private fun renderWebview() {
        webView = findViewById(R.id.consumptionWebView)
        linearBottom = findViewById(R.id.linearBottom)
        textViewRelated = findViewById(R.id.textViewRelated)
        ivNewsImage = findViewById(R.id.scrollingRelativeLayout)
        relatedStories = findViewById(R.id.recyclerView_viewType)
        nestedScrollView = findViewById(R.id.nestedScrollView)

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


            val threshold = 20
            val isAtBottom = webView.contentHeight * webView.scale <= webView.scrollY + webView.height + threshold
            //Log.e("Tag","Bottom --- ${isAtBottom} -- ${(webView.contentHeight * webView.scale)} -- ${ webView.scrollY + webView.height + threshold}")

            if (isAtBottom) {
                Log.e(TAG, "renderWebview: Scroll End")
                // Scroll the ImageView when WebView finishes scrolling
                //linearBottom.scrollTo(0,textViewRelated.top)
                //nestedScrollView.smoothScrollTo(0, textViewRelated.top)
                nestedScrollView.post {
                    nestedScrollView.scrollTo(0,scrollY)
                }


            }
        }
        // Set the scroll listener



        webView.webViewClient = object : android.webkit.WebViewClient() {
            override fun onPageFinished(view: android.webkit.WebView?, url: String?) {
                super.onPageFinished(view, url)

                ivNewsImage.post {
                    val fullHeightPx = ivNewsImage.measuredHeight // Height in pixels
                    val fullHeightDp = pxToDp(fullHeightPx) // Convert to DP
                    injectCSS(view, fullHeightDp)
                }
            }
        }

        val url="""https://www.deccanherald.com/india/indian-political-updates-live-latest-news-congress-bjp-aap-shiv-sena-ubt-tmc-rahul-gandhi-narendra-modi-jairam-ramesh-bihar-punjab-telangana-shashi-tharoor-andhra-pradesh-legislative-council-election-m-k-stalin-3423781?app=true"""
        // Normal story
        //val url="""https://deccanherald-web.qtstage.io/india/andhra-pradesh/text-story-with-twitter-embed-scascs-880?app=true"""
        webView.loadUrl(url)

        //webView.visibility=View.GONE

        relatedStories.setLayoutManager(LinearLayoutManager(this,RecyclerView.VERTICAL,false))

        relatedStories.bindData(
            data = listOf("1111111111", "2222222222", "3333333333", "4444444444", "5555555555", "3333333333", "4444444444", "5555555555", "3333333333", "4444444444", "5555555555", "3333333333", "4444444444", "5555555555", "3333333333", "4444444444", "5555555555", "3333333333", "4444444444", "5555555555"),
            layoutRes = R.layout.related_stories_item,
            bindFunc = { view, item ->
                val textView = view.findViewById<TextView>(R.id.textView)
                textView.text = item
            }
        )
    }

    private fun pxToDp(px: Int): Int {
        val density = resources.displayMetrics.density
        return (px / density).toInt() // Convert to DP
    }


    private fun injectCSS(view: WebView?, height: Int) {
        val css = "body { margin-top: ${height}px; !important; }" // Use dynamic height
        val js = """
        var style = document.createElement('style');
        style.type = 'text/css';
        style.innerHTML = '$css';
        document.head.appendChild(style);
    """.trimIndent()

        view?.evaluateJavascript(js, null)
    }




    fun <T> RecyclerView.bindData(
        data: List<T>,
        layoutRes: Int,
        bindFunc: (View, T) -> Unit,
        clickListener: ((T) -> Unit)? = null
    ): RecyclerView.Adapter<*>? {
        adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val view = LayoutInflater.from(parent.context).inflate(layoutRes, parent, false)
                return ViewHolder(view)
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val item = data[position]
                bindFunc(holder.itemView, item)
                clickListener?.let { listener ->
                    holder.itemView.setOnClickListener { listener(item) }
                }
            }

            override fun getItemCount() = data.size
            inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
        }
        return adapter
    }

}