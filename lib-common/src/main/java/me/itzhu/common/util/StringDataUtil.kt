package me.itzhu.common.util

/**
 * Created by itzhu on 2018/3/19.
 * desc
 */
object StringDataUtil {

    val TAG = javaClass.simpleName

    /**
     * @param dataStr 类似于以下数据&key1=value1&key2=value2
     * */
    fun dealData(dataStr: String): MutableMap<String, String> {
        var datas: MutableMap<String, String> = mutableMapOf()
        try {
            var dataStrs = dataStr.split("&")
            for (str in dataStrs) {
                if (str.contains("=")) {
                    var m2 = str.split("=")
                    datas.put(m2[0], m2[1])
                }
            }
        } catch (e: Exception) {
            /**数据解析错误*/
            L.e(TAG, "数据解析错误")
            e.printStackTrace()
        }
        return datas
    }
}