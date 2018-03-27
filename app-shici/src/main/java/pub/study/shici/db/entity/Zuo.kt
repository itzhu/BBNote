package pub.study.shici.db.entity

import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

/**
 * Created by itzhu on 2018/3/27.
 * desc
 */
@DatabaseTable(tableName = "zuo")
data class Zuo(@DatabaseField(columnName = "z_id", dataType = DataType.INTEGER) val z_id: Int,
               @DatabaseField val zuo: String,
               @DatabaseField val n_id: Int

) {
    constructor() : this(-1, "", -1)
}