package com.edu.hrbeu.jygankclient.di.module

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.edu.hrbeu.jygankclient.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * JYGod丶 创建于 17/12/8 下午9:41
 */
@Module
open class AppModule(val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApplication(): Application = app

    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return Room.databaseBuilder(app.applicationContext,
                AppDatabase::class.java, AppDatabase.TAG)
                .build()
    }


}