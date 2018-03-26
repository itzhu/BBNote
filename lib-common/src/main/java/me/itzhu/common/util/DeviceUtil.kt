package me.itzhu.common.util

import android.annotation.SuppressLint
import android.content.Context
import android.telephony.TelephonyManager
import android.text.TextUtils
import com.blankj.utilcode.util.DeviceUtils
import java.util.*
import me.itzhu.common.R
import java.io.*
import org.json.JSONException
import org.json.JSONObject
import java.net.*
import java.text.SimpleDateFormat


/**
 * Created by itzhu on 2018/1/18.
 * desc 设备基本信息
 */
object DeviceUtil {

    val SP_KEY = "sp_uuid"


    //获取deviceID
    @SuppressLint("MissingPermission")
    fun getDeviceID(context: Context): String? {
        try {
            return (context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager).deviceId
        } catch (e: Exception) {
        }
        return null
    }

    fun getDeviceUUID(context: Context): String {
        var installid = Installation.id(context)
        if (!TextUtils.isEmpty(installid)) {
            return "IN-" + (if (installid!!.length > 12) installid!!.substring(0, 12) else installid!!)
        } else {
            return getSpUid(context)
        }
    }

    private fun getSpUid(context: Context): String {
        var uid: String = SpUtil.get(context, SP_KEY, "")
        if (TextUtils.isEmpty(uid)) {
            var info = DeviceUtils.getAndroidID() + DeviceUtils.getModel() + DeviceUtils.getSDKVersion()
            var infomd5 = Md5.encrypt(info)
            if (!TextUtils.isEmpty(infomd5)) {
                uid = "S5I" + infomd5 + "-" + System.currentTimeMillis()
            } else {
                uid = "SI" + UUID.randomUUID()
            }
            if (uid.length > 16) uid = uid.substring(0, 16)
            SpUtil.put(context, SP_KEY, uid)
        }
        return uid
    }

    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    fun getVersion(context: Context): String {
        try {
            val manager = context.getPackageManager()
            val info = manager.getPackageInfo(context.getPackageName(), 0)
            val version = info.versionName
            return version
        } catch (e: Exception) {
            e.printStackTrace()
            return context.getString(R.string.can_not_find_version_name)
        }
    }

//    /**
//     * 获取手机信息
//     */
//    fun getPhoneInfo(context: Activity) {
//        val metric = DisplayMetrics()
//        context.getWindowManager().getDefaultDisplay().getMetrics(metric)
//        val pkgM = context.getPackageManager()
//
//        val sb = StringBuilder()
//
//        sb.append("CPU型号 : " + getCpuName() + "\n")
//        sb.append("CPU核心数 : " + getNumberOfCPUCores() + "\n")
//        sb.append("CPU频率 : " + getMinCpuFreq() + "-" + getMaxCpuFreq() + " MHZ " + "\n")
//        sb.append("屏幕尺寸 : " + metric.heightPixels + " * " + metric.widthPixels + "\n")
//        sb.append("运行内存 : " + getTotalMemory() + "\n")
//        var totalSDMemory = (getSDCardMemory()[0] / (1024.0 * 1024.0 * 1024.0)).toString()
//        totalSDMemory = totalSDMemory.substring(0, totalSDMemory.indexOf(".") + 3)
//        var availableMemory = (getSDCardMemory()[1] / (1024.0 * 1024.0 * 1024.0)).toString()
//        availableMemory = availableMemory.substring(0, totalSDMemory.indexOf(".") + 3)
//        sb.append("机身存储 : " + totalSDMemory + "G" + " ; 可用 : " + availableMemory + "G" + "\n")
//        val isSupportAccelerate = pkgM.hasSystemFeature("android.hardware.sensor.accelerometer")
//        sb.append("加速度传感器 : " + isSupport(isSupportAccelerate) + "\n")
//
//        val isSupportMagnetic = pkgM.hasSystemFeature("android.hardware.sensor.compass")
//        sb.append("电子罗盘 : " + isSupport(isSupportMagnetic) + "\n")
//
//        val isSupportGyroscope = pkgM.hasSystemFeature("android.hardware.sensor.gyroscope")
//        sb.append("陀螺仪传感器 : " + isSupport(isSupportGyroscope) + "\n")
//
//        val isSupportMicrophone = pkgM.hasSystemFeature("android.hardware.microphone")
//        sb.append("麦克风 : " + isSupport(isSupportMicrophone) + " \n")
//        sb.append("屏幕密度 : " + metric.density + "\n")
//
//    }

    /**
     * 获取ip地址
     * @return
     */
    fun getHostIP(): String? {
        var hostIp: String? = null
        try {
            val nis = NetworkInterface.getNetworkInterfaces()
            var ia: InetAddress? = null
            while (nis.hasMoreElements()) {
                val ni = nis.nextElement() as NetworkInterface
                val ias = ni.getInetAddresses()
                while (ias.hasMoreElements()) {
                    ia = ias.nextElement()
                    if (ia is Inet6Address) {
                        continue// skip ipv6
                    }
                    val ip = ia!!.getHostAddress()
                    if ("127.0.0.1" != ip) {
                        hostIp = ia!!.getHostAddress()
                        break
                    }
                }
            }
        } catch (e: SocketException) {
            e.printStackTrace()
        }
        return hostIp
    }

    /**
     * 获取外网IP地址
     * @return
     */
    fun GetNetIp(): String? {
        var infoUrl: URL? = null
        var inStream: InputStream? = null
        var line: String? = ""
        try {
            infoUrl = URL("http://pv.sohu.com/cityjson?ie=utf-8")
            val connection = infoUrl!!.openConnection()
            val httpConnection = connection as HttpURLConnection
            val responseCode = httpConnection.getResponseCode()
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inStream = httpConnection.getInputStream()
                val reader = BufferedReader(InputStreamReader(inStream, "utf-8"))
                val strber = StringBuilder()
                while (true) {
                    //当有内容时读取一行数据，否则退出循环
                    line = reader.readLine() ?: break
                    strber.append(line + "\n")
                }
                inStream!!.close()
                // 从反馈的结果中提取出IP地址
                val start = strber.indexOf("{")
                val end = strber.indexOf("}")
                val json = strber.substring(start, end + 1)
                if (json != null) {
                    try {
                        val jsonObject = JSONObject(json)
                        line = jsonObject.optString("cip")
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                return line
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return line
    }


    private var CPU_FILTER = object : FileFilter {
        override fun accept(pathname: File): Boolean {
            val path = pathname.getName()
            //regex is slow, so checking char by char.
            if (path.startsWith("cpu")) {
                for (i in 3 until path.length) {
                    if (path.get(i) < '0' || path.get(i) > '9') {
                        return false
                    }
                }
                return true
            }
            return false
        }
    }


    private fun isSupport(pValue: Boolean): String {
        return if (pValue) "支持" else "不支持"
    }


    fun getMaxCpuFreq(): String {
        var result = ""
        val cmd: ProcessBuilder
        try {
            val args = arrayOf("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq")
            cmd = ProcessBuilder(*args)
            val process = cmd.start()
            val `in` = process.inputStream
            val re = ByteArray(24)
            while (`in`.read(re) !== -1) {
                result = result + String(re)
            }
            `in`.close()
        } catch (ex: IOException) {
            ex.printStackTrace()
            result = "N/A"
        }

        val maxFreq = Integer.parseInt(result.trim { it <= ' ' }) / 1024
        return maxFreq.toString()
    }


    fun getCpuName(): String? {
        try {
            val fr = FileReader("/proc/cpuinfo")
            val br = BufferedReader(fr)
            val text = br.readLine()
            val array = text.split(":\\s+".toRegex(), 2).toTypedArray()
            for (i in array.indices) {
            }
            return array[1]
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

}