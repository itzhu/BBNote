package me.itzhu.common.ui.helper

import android.app.Activity
import android.content.Intent
import android.text.TextUtils
import me.itzhu.common.R

/**
 * Created by itzhu on 2018/1/9.
 * desc 界面跳转动画
 * 因为某些手机或其他因素导致在主题xml里面配置的anim不能显示时，使用这个类 。
 * @attention : 使用这个里面的方法时，注意不要重复执行overridePendingTransition这个方法
 * */
object ActivityHelper {
    val TAG = javaClass.simpleName

    val DATA_COMMON_ANIM = "DATA_ACTIVITY_FINISH_ANIM"

    val ANIM_IN_LEFT = "in-left"
    val ANIM_IN_TOP = "in-top"
    val ANIM_IN_RIGHT = "in-right"
    val ANIM_IN_BOTTOM = "in-bottom"

    val ANIM_OUT_LEFT = "out-left"
    val ANIM_OUT_TOP = "out-top"
    val ANIM_OUT_RIGHT = "out-right"
    val ANIM_OUT_BOTTOM = "out-bottom"

    val ANIM_HOLD = "hold"

    val anims: MutableMap<String, Int> = mutableMapOf()

    init {
        anims.put(ANIM_IN_LEFT, R.anim.slide_in_left)
        anims.put(ANIM_IN_TOP, R.anim.slide_in_top)
        anims.put(ANIM_IN_RIGHT, R.anim.slide_in_right)
        anims.put(ANIM_IN_BOTTOM, R.anim.slide_in_bottom)

        anims.put(ANIM_OUT_LEFT, R.anim.slide_out_left)
        anims.put(ANIM_OUT_TOP, R.anim.slide_out_top)
        anims.put(ANIM_OUT_RIGHT, R.anim.slide_out_right)
        anims.put(ANIM_OUT_BOTTOM, R.anim.slide_out_bottom)

        anims.put(ANIM_HOLD, R.anim.hold)
    }


    /**
     * itzhu 2018/3/21  14:18
     * @param intent
     * @param enterAnimKey - 进入的activity开始动画{@see: me.itzhu.common.ui.helper.ActivityHelper}
     * @param finishAnimKey - 进入的activity结束动画
     * @param finish - 是否结束当前ativity
     * @desc:
     */
    fun startActivityWithAnim(activity: Activity, intent: Intent, enterAnimKey: String?, finishAnimKey: String?, finish: Boolean = false) {
        if (!TextUtils.isEmpty(finishAnimKey)) {
            intent.putExtra(ActivityHelper.DATA_COMMON_ANIM, finishAnimKey)
        }
        activity.startActivity(intent)
        if (finish) activity.finish()
        if (!TextUtils.isEmpty(enterAnimKey)) {
            var enterAnim = ActivityHelper.anims.get(enterAnimKey) ?: -1
            if (enterAnim != -1) activity.overridePendingTransition(R.anim.hold, enterAnim)
        }
    }

    /**
     * @author:itzhu 2018/3/21  14:05
     * @params:
     * @desc: activity退出时的动画效果
     * 在activity的finish()方法里面执行
     */
    fun finishAnimation(activity: Activity, intent: Intent) {
        var animKey = intent.getStringExtra(ActivityHelper.DATA_COMMON_ANIM)
        var anim = ActivityHelper.anims.get(animKey) ?: -1
        if (anim != -1) {
            activity.overridePendingTransition(R.anim.hold, anim)
        }
    }

}