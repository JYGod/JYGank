package com.edu.hrbeu.jygankclient.entity

import com.edu.hrbeu.jygankclient.repository.room.model.AndroidModel

/**
 * JYGod丶 创建于 17/12/9 下午3:50
 * 用于解析api返回结果
 */
class AndroidEntity {
    var error = ""
    var results: List<AndroidModel>? = null
}