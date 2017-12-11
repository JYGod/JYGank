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

/**
 * JYGod丶 创建于 17/12/8 下午7:23
 */
class AndroidFragment : LifecycleFragment() {

    private lateinit var viewModel: AndroidViewModel
    private lateinit var mAdapter: AndroidAdapter
    private var PAGE = 1

    companion object {
        val TAG = "AndroidFragment"
        val TITLE = "title"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_android, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycler()
        val bundel = arguments
        val type = bundel.getString("title")
        viewModel = ViewModelProviders.of(this).get(AndroidViewModel::class.java)
        viewModel.getList(PAGE.toString(), type)?.observeForever { list ->
            list?.let {
                mAdapter.addData(it)
                mAdapter.loadMoreComplete()
            }
        }
        mAdapter.setOnLoadMoreListener {
            viewModel.getList((PAGE++).toString(), type)
        }
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

    override fun onDestroyView() {
        super.onDestroyView()
        PAGE = 1
    }

}