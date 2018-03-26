package pub.study.shici.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
import com.j256.ormlite.support.ConnectionSource
import java.io.File
import java.io.InputStream

/**
 * Created by itzhu on 2018/3/26.
 * desc 创建数据库
 */
class DatabaseHelper : OrmLiteSqliteOpenHelper {

    constructor(context: Context?, databaseName: String?, factory: SQLiteDatabase.CursorFactory?, databaseVersion: Int) : super(context, databaseName, factory, databaseVersion)
    constructor(context: Context?, databaseName: String?, factory: SQLiteDatabase.CursorFactory?, databaseVersion: Int, configFileId: Int) : super(context, databaseName, factory, databaseVersion, configFileId)
    constructor(context: Context?, databaseName: String?, factory: SQLiteDatabase.CursorFactory?, databaseVersion: Int, configFile: File?) : super(context, databaseName, factory, databaseVersion, configFile)
    constructor(context: Context?, databaseName: String?, factory: SQLiteDatabase.CursorFactory?, databaseVersion: Int, stream: InputStream?) : super(context, databaseName, factory, databaseVersion, stream)


    companion object {
        @Volatile private var INSTANCE: DatabaseHelper? = null
        fun getInstance(context: Context) {

        }
    }


    override fun onCreate(database: SQLiteDatabase?, connectionSource: ConnectionSource?) {
//        try {
//            TableUtils.createTable(connectionSource, Nian::class.java)
//            TableUtils.createTable(connectionSource, Poem::class.java)
//            TableUtils.createTable(connectionSource, Zuo::class.java)
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }
    }

    override fun onUpgrade(database: SQLiteDatabase?, connectionSource: ConnectionSource?,
                           oldVersion: Int, newVersion: Int) {
    }
}