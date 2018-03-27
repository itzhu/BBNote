package pub.study.shici

import android.app.Application
import me.itzhu.common.AppCommon

/**
 * Created by itzhu on 2018/3/27.
 * desc
 */
class App :Application(){
    override fun onCreate() {
        super.onCreate()
        AppCommon.init(this)
    }
}