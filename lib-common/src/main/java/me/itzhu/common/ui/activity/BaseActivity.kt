package me.itzhu.common.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log

/**
 * Created by itzhu on 2017/12/25.
 * desc
 */
open class BaseActivity : AppCompatActivity() {
    var TAG = javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("activity  onCreate->", javaClass.simpleName)
    }

    protected fun getActivity() = this
}