package com.edu.hrbeu.jygankclient.repository.room.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * JYGod丶 创建于 17/12/10 下午3:08
 */
@Entity(tableName = "welfare")
class WelfareModel {

    @PrimaryKey
    var _id = ""
    var createdAt: String? = null
    var desc: String? = null
    var publishedAt: String? = null
    var type: String? = null
    var url: String? = null
    var who: String? = null

}