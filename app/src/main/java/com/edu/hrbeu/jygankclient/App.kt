package com.edu.hrbeu.jygankclient

import android.app.Application
import com.edu.hrbeu.jygankclient.di.component.AppComponent
import com.edu.hrbeu.jygankclient.di.component.DaggerAppComponent
import com.edu.hrbeu.jygankclient.di.module.AppModule
import com.edu.hrbeu.jygankclient.di.module.RemoteModule
import com.edu.hrbeu.jygankclient.di.module.RoomModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

/**
 * JYGod丶 创建于 17/12/8 下午9:00
 */
class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(AndroidLogAdapter())
            Logger.d("开启Debug模式")
        }
        initializeDagger()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .roomModule(RoomModule())
                .remoteModule(RemoteModule())
                .build()
    }
}