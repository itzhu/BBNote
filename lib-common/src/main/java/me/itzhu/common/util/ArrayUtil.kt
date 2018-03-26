package me.itzhu.common.util

/**
 * Created by itzhu on 2017/12/6.
 * desc
 */
object ArrayUtil {

    /**
     * 数组转字符串
     * @param strings array数组
     * @param split 分隔符
     * */
    fun ArrayToString(strings: Array<String>, split: String = ","): String {
        val builder = StringBuilder()
        for (i in strings.indices) {
            builder.append(strings[i])
            builder.append(split)
        }
        builder.substring(0, builder.length - 1)
        return builder.toString()
    }
}