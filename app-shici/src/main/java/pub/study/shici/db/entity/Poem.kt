package pub.study.shici.db.entity

import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by itzhu on 2018/3/27.
 * desc
 */
@DatabaseTable(tableName = "poem")
data class Poem(@DatabaseField(columnName = "id", dataType = DataType.INTEGER) val id: Int,
                @DatabaseField val title: String,
                @DatabaseField val n_id: Int,
                @DatabaseField val z_id: Int,
                @DatabaseField val content: String,
                @DatabaseField val zhu: String
) {
    constructor():this(-1,"",-1,-1,"","")
}