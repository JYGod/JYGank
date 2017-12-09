package com.edu.hrbeu.jygankclient.view.activity

import android.arch.lifecycle.LifecycleRegistry
import android.arch.lifecycle.LifecycleRegistryOwner
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.edu.hrbeu.jygankclient.R
import com.edu.hrbeu.jygankclient.view.fragment.AndroidFragment
import kotlinx.android.synthetic.main.activity_main.*
import me.majiajie.pagerbottomtabstrip.item.BaseTabItem
import me.majiajie.pagerbottomtabstrip.item.NormalItemView
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener

/**
 * JYGod丶 创建于 17/12/8 下午7:23
 */
class MainActivity : AppCompatActivity(), LifecycleRegistryOwner {

    private var lifeRegister = LifecycleRegistry(this)
    private val TAG_ANDROID = "Android"
    private val TAG_IOS = "IOS"
    private val TAG_WELFARE = "福利"
    private val TAG_DEVELOPER = "开发者"

    override fun getLifecycle(): LifecycleRegistry {
        return lifeRegister
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.main_container, AndroidFragment(), AndroidFragment.TAG)
                .commit()
        initBottomBar()
    }

    private fun initBottomBar() {
        val navController = bottomTab
                .custom()
                .addItem(newItem(R.drawable.bottom_android, R.drawable.bottom_android_pressed, TAG_ANDROID))
                .addItem(newItem(R.drawable.bottom_ios, R.drawable.bottom_ios_pressed, TAG_IOS))
                .addItem(newItem(R.drawable.bottom_welfare, R.drawable.bottom_welfare_pressed, TAG_WELFARE))
                .addItem(newItem(R.drawable.bottom_git, R.drawable.bottom_git_pressed, TAG_DEVELOPER))
                .build()
        navController.addTabItemSelectedListener(object : OnTabItemSelectedListener {
            override fun onSelected(index: Int, old: Int) {

            }

            override fun onRepeat(index: Int) {

            }
        })
    }

    private fun newItem(drawable: Int, checkedDrawable: Int, text: String): BaseTabItem {
        val normalItemView = NormalItemView(this)
        normalItemView.initialize(drawable, checkedDrawable, text)
        normalItemView.setTextDefaultColor(resources.getColor(R.color.bottomText))
        normalItemView.setTextCheckedColor(resources.getColor(R.color.colorPrimary))
        return normalItemView
    }
}
