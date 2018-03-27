package pub.study.shici.db

import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.AndroidConnectionSource
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager
import me.itzhu.common.util.L
import pub.study.shici.AppConfig

/**
 * Created by itzhu on 2018/3/26.
 * desc 读取外部数据库
 */
class DBManager {

    val TAG = javaClass.simpleName

    private var mConnectionSource: AndroidConnectionSource? = null

    companion object {
        @Volatile private var INSTANCE: DBManager? = null

        private val dbPath = AppConfig.getShiciDBFile()//数据库路径

        /**
         * 初始化数据库
         * @param dbPath db文件路径
         * */
        private fun initializeInstance(dbPath: String): DBManager {
            if (INSTANCE == null) {
                INSTANCE = DBManager()
                var db: SQLiteDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE)
                INSTANCE?.mConnectionSource = AndroidConnectionSource(db)
            }
            return INSTANCE!!
        }

        fun getInstance(): DBManager = INSTANCE ?: synchronized(this) {
            INSTANCE ?: initializeInstance(dbPath)
        }
    }

    fun getConnectionSource(): AndroidConnectionSource? = mConnectionSource

    fun <D : Dao<T, *>, T> getDao(clazz: Class<T>): D? {
        try {
            if (mConnectionSource != null) {
                return DaoManager.createDao(mConnectionSource, clazz)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            L.e(TAG, "error->", e)
        }
        return null
    }

    fun close() {
        mConnectionSource?.isCancelQueriesEnabled = true
        mConnectionSource?.close()
        INSTANCE = null
    }
}