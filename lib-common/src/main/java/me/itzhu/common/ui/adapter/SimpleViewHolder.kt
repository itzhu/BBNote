package me.itzhu.common.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import me.itzhu.common.util.S

/**
 * Created by itzhu on 2017/12/13.
 * desc
 */
class SimpleViewHolder : RecyclerView.ViewHolder {
    var views: MutableList<View>
    var rootView: View

    constructor(rootView: View, ids: Array<Int>) : super(rootView) {
        this.rootView = rootView
        var count = ids.size
        views = arrayListOf()
        for (i in 0..(count - 1)) {
            views.add(i, S.S(rootView, ids[i]))
        }
    }
}