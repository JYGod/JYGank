package com.edu.hrbeu.jygankclient.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.edu.hrbeu.jygankclient.db.dao.AndroidDao
import com.edu.hrbeu.jygankclient.db.model.AndroidModel
import org.jetbrains.anko.Android
import javax.inject.Inject

/**
 * JYGod丶 创建于 17/12/8 下午10:12
 */
@Database(entities = arrayOf(AndroidModel::class), version = 1)
abstract class AppDatabase : RoomDatabase() {


    companion object {
        val TAG = "jy_gank"
    }

    abstract fun androidDao(): AndroidDao
}