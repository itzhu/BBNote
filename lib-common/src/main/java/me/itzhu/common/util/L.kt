package me.itzhu.common.util

import android.util.Log

/**
 * Created by itzhu on 2017/12/5.
 * desc
 */
object L{
    val LOG_ON = true

    /**
     * @param TAG
     * @param message
     */
    fun e(TAG: String, message: String) {
        if (LOG_ON) Log.e(TAG, message)
    }

    /**
     * @param TAG
     * @param message
     * @param tr
     */
    fun e(TAG: String, message: String, tr: Throwable) {
        if (LOG_ON) Log.e(TAG, message, tr)
    }
}