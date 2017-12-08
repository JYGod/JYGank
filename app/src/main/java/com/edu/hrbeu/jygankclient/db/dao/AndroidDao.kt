package com.edu.hrbeu.jygankclient.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.edu.hrbeu.jygankclient.db.model.AndroidModel

/**
 * JYGod丶 创建于 17/12/8 下午10:18
 */

@Dao
interface AndroidDao {

    @Query("select * from android limit 5")
    fun selectList(): List<AndroidModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<AndroidModel>)

}