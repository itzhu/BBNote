package me.itzhu.common.util

import android.content.SharedPreferences
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method

/**
 * Created by itzhu on 2017/12/25.
 * desc
 */
object SharedPreferencesCompat {
    private val sApplyMethod = findApplyMethod()

    /**
     * 反射查找apply的方法
     *
     * @return
     */
    private fun findApplyMethod(): Method? {
        try {
            val clz = SharedPreferences.Editor::class.java
            return clz.getMethod("apply")
        } catch (e: NoSuchMethodException) {
        }

        return null
    }

    /**
     * 如果找到则使用apply执行，否则使用commit
     *
     * @param editor
     */
    fun apply(editor: SharedPreferences.Editor) {
        try {
            if (sApplyMethod != null) {
                sApplyMethod.invoke(editor)
                return
            }
        } catch (e: IllegalArgumentException) {
        } catch (e: IllegalAccessException) {
        } catch (e: InvocationTargetException) {
        }

        editor.commit()
    }
}