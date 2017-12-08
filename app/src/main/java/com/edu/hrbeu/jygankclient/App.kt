package com.edu.hrbeu.jygankclient

import android.app.Application
import com.edu.hrbeu.jygankclient.db.AppDatabase
import com.edu.hrbeu.jygankclient.di.DaggerAppComponent
import com.edu.hrbeu.jygankclient.di.module.AppModule
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * JYGod丶 创建于 17/12/8 下午9:00
 */
class App : Application() {


    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(AndroidLogAdapter())
            Logger.d("开启Debug模式")
        }

        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
                .injectApp(this)

    }


}