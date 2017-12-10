package com.edu.hrbeu.jygankclient.view.adapter

import android.view.View
import android.widget.ImageView
import com.bm.library.Info
import com.bm.library.PhotoView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.edu.hrbeu.jygankclient.R
import com.edu.hrbeu.jygankclient.repository.room.model.WelfareModel
import com.orhanobut.logger.Logger
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * JYGod丶 创建于 17/12/10 下午2:58
 */
class WelfareAdapter : BaseQuickAdapter<WelfareModel, BaseViewHolder>(R.layout.item_welfare, null) {

    override fun convert(helper: BaseViewHolder, item: WelfareModel?) {
        val imgView = helper.getView<ImageView>(R.id.imgWelfare)
        Glide.with(mContext)
                .load(item?.url)
                .into(imgView)
    }
}