package pub.study.shici.db

import com.j256.ormlite.dao.GenericRawResults
import com.j256.ormlite.dao.RawRowMapper
import com.j256.ormlite.field.DataType
import me.itzhu.common.util.L
import pub.study.shici.db.entity.Poem
import pub.study.shici.db.entity.PoemData

/**
 * Created by itzhu on 2018/3/27.
 * desc
 */
object ShiciHelper {
    val TAG = javaClass.simpleName

    /**
     * 查询诗词
     * @param id poem id
     * 对应sql:select poem.*,nian.nian,zuo.zuo from poem,nian,zuo
     * where poem.n_id = nian.n_id and poem.z_id = zuo.z_id and poem.id=1
     * */
    fun getPoemDataFromId(id: Int): PoemData? {
        try {
            var poemDao = DBManager.getInstance().getDao(Poem::class.java)
            var sql = "select poem.id,poem.title,poem.n_id,poem.z_id,poem.content,poem.zhu,nian.nian,zuo.zuo from poem,nian,zuo where poem.n_id = nian.n_id and poem.z_id = zuo.z_id and poem.id=${id}"

            L.e(TAG, "sql->${(poemDao == null)}" + sql )
            var results: GenericRawResults<PoemData>? = poemDao?.queryRaw(
                    sql,
                    RawRowMapper<PoemData> { columnNames, resultColumns ->
                        PoemData(Poem(resultColumns[0].toInt(), resultColumns[1], resultColumns[2].toInt(),
                                resultColumns[3].toInt(), resultColumns[4], resultColumns[5]),
                                resultColumns[6], resultColumns[7])
                    }
            )
            return results?.firstResult
        } catch (e: Exception) {
            e.printStackTrace()
            L.e(TAG, "error", e)
        }
        return null
    }


}