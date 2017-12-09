package com.edu.hrbeu.jygankclient.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * JYGod丶 创建于 17/12/8 下午9:41
 */
@Module
open class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

}