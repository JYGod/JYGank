package com.edu.hrbeu.jygankclient.view.adapter

import android.content.Intent
import android.support.v7.widget.CardView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.edu.hrbeu.jygankclient.R
import com.edu.hrbeu.jygankclient.repository.room.model.AndroidModel
import com.edu.hrbeu.jygankclient.view.activity.WebviewActivity
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * JYGod丶 创建于 17/12/9 下午12:16
 */
class AndroidAdapter : BaseQuickAdapter<AndroidModel, BaseViewHolder>(R.layout.item_android, null) {

    override fun convert(helper: BaseViewHolder, item: AndroidModel) {
        helper.setText(R.id.tvTitle, item.desc)
        helper.setText(R.id.tvAuth, "作者: ${item.who?:"匿名"}    " +
                "发布时间: ${item.publishedAt?.substring(0, 10)}")
        helper.getView<CardView>(R.id.itemAndroid).onClick {
            val intent = Intent()
            intent.putExtra(WebviewActivity.PARAM_URL, item.url)
            intent.setClass(mContext, WebviewActivity::class.java)
            mContext.startActivity(intent)
        }
    }
}