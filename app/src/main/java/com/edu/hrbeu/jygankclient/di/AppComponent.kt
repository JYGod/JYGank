package com.edu.hrbeu.jygankclient.di

import com.edu.hrbeu.jygankclient.App
import com.edu.hrbeu.jygankclient.di.module.AppModule
import dagger.Component

/**
 * JYGod丶 创建于 17/12/8 下午9:39
 */

@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun injectApp(app: App)
}