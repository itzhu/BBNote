package pub.study.shici.db

import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.AndroidConnectionSource
import com.j256.ormlite.dao.Dao
import com.j256.ormlite.dao.DaoManager

/**
 * Created by itzhu on 2018/3/26.
 * desc 读取外部数据库
 */
class DBManager {

    private var mConnectionSource: AndroidConnectionSource? = null

    companion object {
        @Volatile private var INSTANCE: DBManager? = null

        val dbPath = "file:///android_asset/shici.db"//数据库路径

        /**
         * 初始化数据库
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
        }
        return null
    }

}