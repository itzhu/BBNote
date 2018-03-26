package pub.study.shici.db.entity

import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by itzhu on 2018/3/26.
 * desc
 */
@DatabaseTable(tableName = "nian")
data class Nian(@DatabaseField(columnName = "n_id", dataType = DataType.INTEGER) val n_id: Int,
                @DatabaseField
                val nian: String) {

}

@DatabaseTable(tableName = "poem")
data class Poem(@DatabaseField(columnName = "n_id", dataType = DataType.INTEGER) val id: Int,
                @DatabaseField val title: String,
                @DatabaseField val n_id: Int,
                @DatabaseField val z_id: Int,
                @DatabaseField val content: String,
                @DatabaseField val zhu: String
) {}

@DatabaseTable(tableName = "zuo")
data class Zuo(@DatabaseField(columnName = "n_id", dataType = DataType.INTEGER) val z_id: Int,
               @DatabaseField val zuo: String,
               @DatabaseField val n_id: Int
) {}


data class PoemData(val Poem: Poem,
                    val nian: String,
                    val zuo: String) {
}