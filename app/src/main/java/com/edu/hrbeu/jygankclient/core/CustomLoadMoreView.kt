package com.edu.hrbeu.douban.view

import com.chad.library.adapter.base.loadmore.LoadMoreView
import com.edu.hrbeu.jygankclient.R


class CustomLoadMoreView: LoadMoreView() {

    override fun getLayoutId(): Int {
        return R.layout.view_load_more
    }

    override fun getLoadingViewId(): Int {
        return R.id.load_more_loading_view
    }

    override fun getLoadEndViewId(): Int {
        return 0
    }

    override fun getLoadFailViewId(): Int {
        return R.id.load_more_load_fail_view
    }
}