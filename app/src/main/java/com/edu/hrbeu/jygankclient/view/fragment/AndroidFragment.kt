package com.edu.hrbeu.jygankclient.view.fragment

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.edu.hrbeu.douban.view.CustomLoadMoreView
import com.edu.hrbeu.jygankclient.R
import com.edu.hrbeu.jygankclient.view.adapter.AndroidAdapter
import com.edu.hrbeu.jygankclient.view.viewmodel.AndroidViewModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_android.*
import me.dkzwm.widget.srl.SmoothRefreshLayout
import me.dkzwm.widget.srl.extra.header.ClassicHeader
import me.dkzwm.widget.srl.indicator.IIndicator
import org.jetbrains.anko.support.v4.toast

/**
 * JYGod丶 创建于 17/12/8 下午7:23
 */
class AndroidFragment : LifecycleFragment() {

    private lateinit var viewModel: AndroidViewModel
    lateinit var mAdapter: AndroidAdapter
    private var PAGE = 1

    companion object {
        val TAG = "AndroidFragment"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_android, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setTitleTextColor(context.resources.getColor(R.color.pureWhite))
        initRecycler()
        initRefreshView()
        viewModel = ViewModelProviders.of(this).get(AndroidViewModel::class.java)
        viewModel.getList(PAGE.toString())?.observeForever { list ->
            Logger.d("list----->发生变化, listSize:${list?.size}")
            list?.let {
                mAdapter.addData(it)
                mAdapter.loadMoreComplete()
//                refreshLayout.refreshComplete()
            }
        }

        mAdapter.setOnLoadMoreListener {
            PAGE += 1
            viewModel.getList(PAGE.toString())
        }
//
//        refreshLayout.setOnRefreshListener(object : SmoothRefreshLayout.OnRefreshListener {
//            override fun onRefreshComplete(isSuccessful: Boolean) {
//                refreshLayout.refreshComplete()
//            }
//            override fun onRefreshBegin(isRefresh: Boolean) {
//                refreshLayout.refreshComplete()
//            }
//        })

    }

    private fun initRefreshView() {
//        refreshLayout.setHeaderView(ClassicHeader(context))
//        refreshLayout.setIndicatorOffsetCalculator { status, currentPos, offset ->
//            when (status) {
//                IIndicator.MOVING_HEADER -> Math.pow(Math.pow((currentPos / 2).toDouble(), 1.28) + offset, 1 / 1.28).toFloat() * 2 - currentPos
//                IIndicator.MOVING_FOOTER -> -(Math.pow(Math.pow((currentPos / 2).toDouble(), 1.28) - offset, 1 / 1.28).toFloat() * 2 - currentPos)
//                else -> when {
//                    offset > 0 -> Math.pow(offset.toDouble(), 1 / 1.28).toFloat() * 2
//                    offset < 0 -> -Math.pow(-offset.toDouble(), 1 / 1.28).toFloat() * 2
//                    else -> offset
//                }
//            }
//        }
    }

    private fun initRecycler() {
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context)
        mAdapter = AndroidAdapter()
        mAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN)
        mAdapter.setLoadMoreView(CustomLoadMoreView())
        mAdapter.setEnableLoadMore(true)
        mRecyclerView.adapter = mAdapter
    }

}