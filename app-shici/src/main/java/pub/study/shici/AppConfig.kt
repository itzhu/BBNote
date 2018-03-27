package pub.study.shici

import me.itzhu.common.AppCommon

/**
 * Created by itzhu on 2018/3/27.
 * desc
 */
object AppConfig {

    /**数据库文件夹路径*/
    private val mDataBasePath = AppCommon.appContext.getDatabasePath("db")
    /**数据库文件夹路径*/
    private val mDBPath = "${mDataBasePath}/bbnote/db"
    /**shici.db文件夹路径*/
    private val shiciDB = "${mDataBasePath}/bbnote/db/shici.db"
    /**assets文件夹里面的shici.zip*/
    private val shiciZipName = "shici.zip"

    fun getDataBasePath() = mDataBasePath
    fun getDBPath() = mDBPath
    fun getShiciDBFile() = shiciDB
    fun getAssetShiciZipName() = shiciZipName

}