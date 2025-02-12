package com.solutelabs.webviewpoc

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.webkit.WebView

class CustomWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : WebView(context, attrs, defStyleAttr) {

    private var startX = 0f
    private var startY = 0f
    private var isBeingDragged = false

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
                isBeingDragged = false
                parent.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = Math.abs(event.x - startX)
                val dy = Math.abs(event.y - startY)
                if (!isBeingDragged) {
                    if (dx > dy) { // Horizontal scroll detected
                        isBeingDragged = true
                        parent.requestDisallowInterceptTouchEvent(true)
                    } else if (dy > dx) { // Vertical scroll detected
                        isBeingDragged = true
                        parent.requestDisallowInterceptTouchEvent(false)
                    }
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                parent.requestDisallowInterceptTouchEvent(false)
                isBeingDragged = false
            }
        }
        return super.onTouchEvent(event)
    }
}