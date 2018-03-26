package me.itzhu.common.util

import android.app.Activity
import android.support.annotation.LayoutRes
import android.support.annotation.Nullable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by itzhu on 2017/12/6.
 * desc
 */
object S {
    /**
     * 简化代码1  findViewById
     *
     * @param activity
     * @param resId
     * @param <T>
     * @return view.findViewById()
    </T> */
    fun <T : View> S(activity: Activity, resId: Int): T {
        return activity.findViewById<View>(resId) as T
    }

    /**
     * 简化代码1  findViewById
     *
     * @param view
     * @param resId
     * @param <T>
     * @return view.findViewById()
    </T> */
    fun <T : View> S(view: View, resId: Int): T {
        return view.findViewById<View>(resId) as T
    }

    fun <T : View> S(inflater: LayoutInflater, @LayoutRes resource: Int, @Nullable root: ViewGroup? = null, attachToRoot: Boolean = false): T {
        return inflater.inflate(resource, root, attachToRoot) as T
    }

    /*-----------------------onclick事件--------------------------------------*/

    fun _c(onClickListener: View.OnClickListener, view: View) {
        view.setOnClickListener(onClickListener)
    }

    fun _c(onClickListener: View.OnClickListener, vararg views: View) {
        for (view in views) {
            view.setOnClickListener(onClickListener)
        }
    }

    fun _c(onClickListener: View.OnClickListener, parent: View, childId: Int) {
        parent.findViewById<View>(childId).setOnClickListener(onClickListener)
    }

    fun _c(onClickListener: View.OnClickListener, parent: Activity, childId: Int) {
        parent.findViewById<View>(childId).setOnClickListener(onClickListener)
    }

    fun _c(onClickListener: View.OnClickListener, parent: Activity, vararg childId: Int) {
        for (id in childId) {
            parent.findViewById<View>(id).setOnClickListener(onClickListener)
        }
    }

    fun _c(onClickListener: View.OnClickListener, parent: View, vararg childId: Int) {
        for (id in childId) {
            parent.findViewById<View>(id).setOnClickListener(onClickListener)
        }
    }

    /*---------------------------------onlongclick事件-----------------------------------*/
    fun _lc(longClickListener: View.OnLongClickListener, view: View) {
        view.setOnLongClickListener(longClickListener)
    }

    fun _lc(longClickListener: View.OnLongClickListener, vararg views: View) {
        for (view in views) {
            view.setOnLongClickListener(longClickListener)
        }
    }

    fun _lc(longClickListener: View.OnLongClickListener, parent: View, childId: Int) {
        parent.findViewById<View>(childId).setOnLongClickListener(longClickListener)
    }

    fun _lc(longClickListener: View.OnLongClickListener, parent: Activity, childId: Int) {
        parent.findViewById<View>(childId).setOnLongClickListener(longClickListener)
    }

    fun _lc(longClickListener: View.OnLongClickListener, parent: View, vararg childId: Int) {
        for (id in childId) {
            parent.findViewById<View>(id).setOnLongClickListener(longClickListener)
        }
    }

    fun _lc(longClickListener: View.OnLongClickListener, parent: Activity, vararg childId: Int) {
        for (id in childId) {
            parent.findViewById<View>(id).setOnLongClickListener(longClickListener)
        }
    }

    /*----------------thread----------------------------*/
//    fun doAsync(f:()->Unit){
//        Thread({f()}).start()
//    }

}