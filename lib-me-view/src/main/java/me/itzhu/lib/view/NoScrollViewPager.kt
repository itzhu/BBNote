package me.itzhu.common.view


import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by itzhu on 2017/9/14.
 * desc
 */

class NoScrollViewPager : ViewPager {

    private var isScrollEnable = true

    constructor(context: Context) : super(context) {}

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {}

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return this.isScrollEnable && super.onTouchEvent(event)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return this.isScrollEnable && super.onInterceptTouchEvent(event)
    }

    /**
     * 是否允许左右滑动
     *
     * @param scroll
     */
    fun setScrollEnable(scroll: Boolean) {
        this.isScrollEnable = scroll
    }
}
