package com.edu.hrbeu.jygankclient.view.fragment

import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.hugeterry.coordinatortablayout.CoordinatorTabLayout
import com.edu.hrbeu.jygankclient.R
import com.edu.hrbeu.jygankclient.view.adapter.PagerAdapter
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_source.*

/**
 * JYGod丶 创建于 17/12/11 下午1:49
 */
class SourceFragment : LifecycleFragment() {

    private val mTitles = listOf( "Android", "IOS", "前端", "休息视频")
    private val mTypes = listOf( "Android", "iOS", "前端", "休息视频")
    private val mImageArray = intArrayOf(R.drawable.pic_android,R.drawable.pic_ios
            , R.drawable.pic_js, R.drawable.pic_bili)

    private val mFragments = mutableListOf<LifecycleFragment>()

    companion object {
        val TAG = "SourceFragment"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_source, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        for (title in mTitles) {
            val fragment = AndroidFragment()
            val bundle = Bundle()
            bundle.putString(AndroidFragment.TITLE, mTypes[mTitles.indexOf(title)])
            fragment.arguments = bundle
            mFragments.add(fragment)
        }
//        viewPager.offscreenPageLimit = 5
        viewPager.adapter = PagerAdapter(childFragmentManager, mFragments, mTitles)
        coorTablayout.setTranslucentStatusBar(activity)
                .setTitle("干货资源")
                .setBackEnable(false)
                .setImageArray(mImageArray)
                .setupWithViewPager(viewPager)

    }

    override fun onDestroy() {
        super.onDestroy()
        mFragments.removeAll(mFragments)
    }


}