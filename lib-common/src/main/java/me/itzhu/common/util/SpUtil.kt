package me.itzhu.common.util

import android.content.Context

/**
 * Created by itzhu on 2017/12/15.
 * desc
 */
object SpUtil {

    private val TAG = "SpUtils"

    /**
     * 保存在手机里面的文件名
     */
    val FILE_NAME = "app_sp_data"

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     */
    fun put(context: Context, key: String, value: Any) {

        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()

        if (value is String) {
            editor.putString(key, value)
        } else if (value is Int) {
            editor.putInt(key, value)
        } else if (value is Boolean) {
            editor.putBoolean(key, value)
        } else if (value is Float) {
            editor.putFloat(key, value)
        } else if (value is Long) {
            editor.putLong(key, value)
        } else {
            editor.putString(key, value.toString())
        }
        SharedPreferencesCompat.apply(editor)
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultValue
     * @return
     */
    fun <T> get(context: Context, key: String, defaultValue: T): T {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        if (defaultValue is String) {
            return sp.getString(key, defaultValue) as T
        } else if (defaultValue is Int) {
            return sp.getInt(key, defaultValue) as T
        } else if (defaultValue is Boolean) {
            return sp.getBoolean(key, defaultValue) as T
        } else if (defaultValue is Float) {
            return sp.getFloat(key, defaultValue) as T
        } else if (defaultValue is Long) {
            return sp.getLong(key, defaultValue) as T
        }

        return defaultValue
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    fun remove(context: Context, key: String) {
        val sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.remove(key)
        SharedPreferencesCompat.apply(editor)
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    fun clear(context: Context) {
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.clear()
        SharedPreferencesCompat.apply(editor)
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param context
     * @param key
     * @return
     */
    fun contains(context: Context, key: String): Boolean {
        val sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE)
        return sp.contains(key)
    }

    /**
     * 返回所有的键值对
     *
     * @param context
     * @return
     */
    fun getAll(context: Context): Map<String, *> {
        val sp = context.getSharedPreferences(FILE_NAME,
                Context.MODE_PRIVATE)
        return sp.all
    }

}
