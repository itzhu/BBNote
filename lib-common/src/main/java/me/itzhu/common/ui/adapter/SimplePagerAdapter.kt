package me.itzhu.common.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by itzhu on 2017/8/16.
 * desc
 */
class SimplePagerAdapter(fm: FragmentManager, private val infos: List<Fragment>, val titles: List<String>? = null) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return infos[position]
    }

    override fun getCount(): Int {
        return infos.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles?.get(position) ?: ""
    }
}
