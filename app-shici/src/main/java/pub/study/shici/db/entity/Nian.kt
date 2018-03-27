package pub.study.shici.db.entity

import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by itzhu on 2018/3/27.
 * desc
 */
@DatabaseTable(tableName = "nian")
data class Nian(@DatabaseField(columnName = "n_id", dataType = DataType.INTEGER) val n_id: Int,
                @DatabaseField
                val nian: String) {
    constructor() : this(-1, "")
}
