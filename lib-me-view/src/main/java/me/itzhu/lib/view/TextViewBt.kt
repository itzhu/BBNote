package me.itzhu.common.view

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TextView

/**
 * Created by itzhu on 2018/1/3.
 * desc
 */
class TextViewBt : TextView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                alpha = 0.6f
            }

            MotionEvent.ACTION_CANCEL,
            MotionEvent.ACTION_UP -> {
                alpha = 1.0f
            }
        }
        return super.onTouchEvent(event)
    }
}