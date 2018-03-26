package me.itzhu.common.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter

/**
 * Created by itzhu on 2017/12/13.
 * desc
 */
abstract class SimpleBaseAdapter<T, E : SimpleViewHolder>(var context: Context) : BaseAdapter() {
    var data: T? = null
    var layoutInflater: LayoutInflater

    init {
        layoutInflater = LayoutInflater.from(context)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View
        var holder: E
        if (convertView == null) {
            holder = newView(position, parent)
            view = holder.rootView
            view.setTag(holder)
        } else {
            view = convertView
            holder = convertView.getTag() as E
        }
        bindView(view, position, holder)
        return view
    }

    override fun getItem(position: Int): Any = position.toLong()

    override fun getItemId(position: Int): Long = position.toLong()

    abstract fun newView(position: Int, parent: ViewGroup?): E

    abstract fun bindView(itemView: View, position: Int, holder: E)

}