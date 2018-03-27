package me.itzhu.common.helper

import android.app.Activity
import java.lang.ref.WeakReference

/**
 * Created by itzhu on 2018/3/27.
 * desc
 */
class BaseActivityHelper<T : Activity> {

    private var mWeakActivity: WeakReference<T>? = null

    fun bindActivity(activity: T) {
        mWeakActivity = WeakReference(activity)
    }

    fun getActivity(): T? {
        return mWeakActivity?.get()
    }

}