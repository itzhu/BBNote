package me.itzhu.common.util

import com.google.gson.Gson
import com.google.gson.JsonSyntaxException

/**
 * Created by itzhu on 2018/1/6.
 * desc Gson解析
 * */
object GsonUtil {

    val gson = Gson()


    /**
     * 将Json数据解析为bean对象
     *
     * @param jsonStr
     * @param cl
     * @return
     */
    fun <T> jsonToBean(jsonStr: String, clazz: Class<T>): T? {
        var obj: T? = null
        try {
            obj = gson.fromJson(jsonStr, clazz)
        } catch (e: JsonSyntaxException) {
            obj = null
        }
        return obj
    }

    /**
     * 将Json数据解析为bean对象
     *
     * @param jsonStr
     * @param type
     * @return
     */
    fun <T> jsonToBean(jsonStr: String, type: java.lang.reflect.Type): T? {
        var obj: T? = null
        try {
            obj = gson.fromJson<T>(jsonStr, type)
        } catch (e: Exception) {
            obj = null
        }
        return obj
    }

    fun beantoJson(obj: Any, type: java.lang.reflect.Type): String? {
        var objStr: String? = null
        try {
            objStr = gson.toJson(obj, type)
        } catch (e: Exception) {
            objStr = null
        }
        return objStr
    }

    fun beantoJson(obj: Any, clazz: Class<Any>): String? {
        var objStr: String? = null
        try {
            objStr = gson.toJson(obj, clazz)
        } catch (e: Exception) {
            objStr = null
        }
        return objStr
    }

}