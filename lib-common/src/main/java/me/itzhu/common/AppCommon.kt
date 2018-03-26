package me.itzhu.common

import android.app.Application
import android.content.Context
import android.text.TextUtils
import com.blankj.utilcode.util.Utils
import kotlin.properties.Delegates
import com.blankj.utilcode.util.DeviceUtils
import com.yanzhenjie.nohttp.InitializationConfig
import com.yanzhenjie.nohttp.Logger
import com.yanzhenjie.nohttp.NoHttp
import com.yanzhenjie.nohttp.URLConnectionNetworkExecutor
import com.yanzhenjie.nohttp.cache.DBCacheStore
import com.yanzhenjie.nohttp.cookie.DBCookieStore
import me.itzhu.common.util.Installation
import me.itzhu.common.appcash.CrashHandler


/**
 * Created by itzhu on 2017/12/25.
 * desc
 */
object AppCommon {

    var appContext: Context by Delegates.notNull()
    var installtionID: String? = null
    var deviceID: String by Delegates.notNull()

    fun init(context: Application) {
        appContext = context.applicationContext

        /**androidutil配置*/
        Utils.init(context)

        /**http配置*/
        initHttp(context)

        /**app 安装id*/
        installtionID = Installation.id(appContext)
        deviceID = DeviceUtils.getAndroidID() + DeviceUtils.getSDKVersion() + DeviceUtils.getManufacturer() + DeviceUtils.getModel()

        /**app crash监听*/
        val catchHandler = CrashHandler.getInstance()
        catchHandler.init(context)
    }

    /**
     * @param isInstallation
     * 获取设备标识ID
     * true-用户卸载重装就会不同
     * */
    fun getDeviceId(isInstallation: Boolean): String {
        if (isInstallation) return deviceID + installtionID
        return deviceID
    }

    fun initHttp(context: Application) {
        val config = InitializationConfig.newBuilder(context)
                .connectionTimeout(10 * 1000)
                .readTimeout(10 * 1000)
                .cacheStore(DBCacheStore(context).setEnable(true))
                .cookieStore(DBCookieStore(context).setEnable(true))
                .networkExecutor(URLConnectionNetworkExecutor())
                .retry(1)
                .build()
        NoHttp.initialize(config)

        Logger.setDebug(true)// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag("JMSDK - SIMPLE")// 打印Log的tag。
    }

}
