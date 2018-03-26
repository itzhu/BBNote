package pub.study.shici

import android.os.Bundle

import me.itzhu.common.ui.activity.CommonUIActivity

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : CommonUIActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

}
