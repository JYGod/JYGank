package com.edu.hrbeu.jygankclient.view.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.edu.hrbeu.jygankclient.R
import com.edu.hrbeu.jygankclient.repository.room.model.AndroidModel

/**
 * JYGod丶 创建于 17/12/9 下午12:16
 */
class AndroidAdapter : BaseQuickAdapter<AndroidModel, BaseViewHolder>(R.layout.item_android, null) {

    override fun convert(helper: BaseViewHolder, item: AndroidModel) {
        helper.setText(R.id.tvTitle, item.desc)
        helper.setText(R.id.tvAuth, "作者: ${item.who?:"匿名"}    " +
                "发布时间: ${item.publishedAt.substring(0, 10)}")
    }
}