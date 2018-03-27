package me.itzhu.common.interfaces

/**
 * Created by itzhu on 2018/1/4.
 * desc
 */
interface OnEventListener<T> {
    fun onEvent(data: T?)
}