package me.itzhu.common.ui.helper

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.WindowManager
import me.itzhu.common.util.L
import me.itzhu.common.util.StringDataUtil
import me.itzhu.common.R

/**
 * Created by itzhu on 2018/3/19.
 * desc
 */
class DialogHelper {

    companion object {

        val TAG = javaClass.simpleName

        val THEME_ANIM = "anim"
        val THEME_NOPADDING = "nopadding"
        val THEME_BACK_DISMISS = "backdismiss"
        val THEME_OUTSIDE_DISMISS = "outsidedismiss"
        val THEME_WIDTH = "width"
        val THEME_HEIGHT = "height"
        val THEME_ALIGN = "align"

        /**
         * @param theme
         *     &anim=in-left&padding=0-0-0-0&backdismiss=true
         *     <br>&nopadding true 即不会出现白边，占满全屏
         *     <br>&anim - 进入动画效果  in-left、in-top、in-right、in-bottom
         *     <br>&backdismiss - 按返回键是否消失 true-消失  false-不消失 默认消失
         *     <br>&outsidedismiss - 按空白处是否消失 true-消失  false-不消失 默认消失
         *     <br>&width  auto-自适应大小  full-占满全屏
         *     <br>&height  auto-自适应大小  full-占满全屏
         *     <br>&align  left  top  right bottom
         * */
        fun createDialog(context: Context, themeData: String): Dialog {
            L.e(TAG, themeData)

            var dialog = Dialog(context, R.style.style_dialog_common)
            var themes: MutableMap<String, String> = StringDataUtil.dealData(themeData)
            if (themes.size == 0) return dialog
            var window = dialog.window

            //设置动画
            var anim = Config.getValue(Config.anims, themes.get(THEME_ANIM))
            if (-1 != anim) window.attributes.windowAnimations = anim

            //设置gravity
            var gravity = Config.getValue(Config.gravitys, themes.get(THEME_ALIGN))
            if (-1 != gravity) window.setGravity(gravity)

            //设置边距
            if ("true".equals(themes.get(THEME_NOPADDING))) window.decorView.setPadding(0, 0, 0, 0)

            var width = if ("full".equals(themes.get(THEME_WIDTH))) WindowManager.LayoutParams.MATCH_PARENT else WindowManager.LayoutParams.WRAP_CONTENT
            var height = if ("full".equals(themes.get(THEME_HEIGHT))) WindowManager.LayoutParams.MATCH_PARENT else WindowManager.LayoutParams.WRAP_CONTENT

            window.setBackgroundDrawableResource(android.R.color.transparent)//dialog窗口背景透明

            var params = window.attributes
            params.width = width
            params.height = height
            window.attributes = params

            dialog.setCanceledOnTouchOutside("true".equals(themes.get(THEME_OUTSIDE_DISMISS)))//默认点击外部消失
            dialog.setCancelable("true".equals(themes.get(THEME_BACK_DISMISS)))//默认点击返回按钮消失
            return dialog
        }
    }

    /**
     * 配置信息
     * */
    protected object Config {
        val anims = mutableMapOf<String, Int>()

        val gravitys = mutableMapOf<String, Int>()

        init {
            anims.put("in-left", R.style.Dialog_anim_in_left)
            anims.put("in-right", R.style.Dialog_anim_in_right)
            anims.put("in-top", R.style.Dialog_anim_in_top)
            anims.put("in-bottom", R.style.Dialog_anim_in_bottom)

            gravitys.put("left", Gravity.LEFT)
            gravitys.put("top", Gravity.TOP)
            gravitys.put("right", Gravity.RIGHT)
            gravitys.put("bottom", Gravity.BOTTOM)
            gravitys.put("center", Gravity.CENTER)
        }

        fun getValue(map: MutableMap<String, Int>, key: String?): Int {
            if (map.containsKey(key)) {
                var value = map.get(key)
                if (value != null) return value
            }
            return -1
        }
    }
}

