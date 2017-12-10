package com.edu.hrbeu.jygankclient.repository.room.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.edu.hrbeu.jygankclient.repository.room.model.WelfareModel

/**
 * JYGod丶 创建于 17/12/10 下午3:09
 */
@Dao
interface WelfareDao {

    @Query("select * from welfare order by publishedAt desc")
    fun selectList(): LiveData<List<WelfareModel>>

    @Query("select * from welfare where _id = :arg0")
    fun selectById(id: String): List<WelfareModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(list: List<WelfareModel>)

}