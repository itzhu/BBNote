package pub.study.shici

import android.app.ProgressDialog
import android.os.Bundle
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.ToastUtils
import kotlinx.android.synthetic.main.activity_login.*
import me.itzhu.common.interfaces.OnEventListener

import me.itzhu.common.ui.activity.CommonUIActivity
import me.itzhu.common.util.L
import pub.study.shici.db.AssetDBFileHelper
import pub.study.shici.db.ShiciHelper
import java.io.File
import kotlin.concurrent.thread

/**
 * A login screen that offers login via email/password.
 */
class MainActivity : CommonUIActivity() {

    private var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        check()
    }

    fun check() {
        //检查shici.db是否存在
        if (!AssetDBFileHelper.checkDB()) {
            progressDialog = ProgressDialog(this)
            progressDialog?.show()

            AssetDBFileHelper.setOnEventListener(object : OnEventListener<Boolean> {
                override fun onEvent(success: Boolean?) {
                    progressDialog?.dismiss()
                    if (success != null && success) {
                        L.e(TAG, "db文件复制成功${AppConfig.getShiciDBFile()}")
                        ToastUtils.showShort("db文件复制成功${AppConfig.getShiciDBFile()}")

                        test()
                    } else {
                        L.e(TAG, "db文件复制失败${AppConfig.getShiciDBFile()}")
                        ToastUtils.showShort("db文件复制失败${AppConfig.getShiciDBFile()}")
                    }
                }
            }).copyDBToSD(this.applicationContext)
        }else{
            test()
        }
    }

    fun test() {
        tv_content.setText(ShiciHelper.getPoemDataFromId(1)?.toString())
        L.e(TAG, "data->" + ShiciHelper.getPoemDataFromId(1)?.toString())
    }

}
