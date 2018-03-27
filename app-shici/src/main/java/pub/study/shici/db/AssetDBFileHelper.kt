package pub.study.shici.db

import android.content.Context
import android.media.MediaDrm
import android.support.annotation.UiThread
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ZipUtils
import me.itzhu.common.interfaces.OnEventListener
import me.itzhu.common.util.AssetsUtil
import me.itzhu.common.util.L
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import pub.study.shici.AppConfig
import java.io.File

/**
 * Created by itzhu on 2018/3/27.
 * desc 把assets里面shici.zip文件放到外部存储卡上
 */
object AssetDBFileHelper {
    val TAG = javaClass.simpleName

    private var shiciZipFile: File? = null
    private var shiciDBFile: File? = null
    private var eventListener: OnEventListener<Boolean>? = null

    fun checkDB() = File(AppConfig.getShiciDBFile()).exists()

    fun setOnEventListener(eventListener: OnEventListener<Boolean>?): AssetDBFileHelper {
        this.eventListener = eventListener
        return this
    }

    /**
     * 复制文件到sd卡
     * */
    fun copyDBToSD(context: Context) {
        doAsync {
            try {
                var path = File(AppConfig.getDBPath())
                if (!path.exists()) FileUtils.createOrExistsDir(path)
                val zipfile = File(path.absolutePath + File.separator + "shici.zip")
                zipfile.deleteOnExit()//删除zip文件
                L.e("","path->${zipfile.absolutePath}")
                AssetsUtil.copyAssetsToSD(context, AppConfig.getAssetShiciZipName(), zipfile.absolutePath)
                ZipUtils.unzipFile(zipfile, zipfile.parentFile)

            } catch (e: Exception) {
                FileUtils.deleteFile(AppConfig.getShiciDBFile())
               L.e(TAG,"error", e)
            }

            uiThread {
                eventListener?.onEvent(checkDB())
            }
        }
    }
}