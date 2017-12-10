package com.edu.hrbeu.jygankclient.di.module

import android.arch.persistence.room.Room
import android.content.Context
import com.edu.hrbeu.jygankclient.repository.room.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * JYGod丶 创建于 17/12/9 下午2:19
 */
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context,
                AppDatabase::class.java, AppDatabase.TAG)
                .allowMainThreadQueries()
                .build()
    }
}