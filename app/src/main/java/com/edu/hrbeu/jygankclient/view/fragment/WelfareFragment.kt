package com.edu.hrbeu.jygankclient.view.fragment

import android.arch.lifecycle.LifecycleFragment
import android.arch.lifecycle.ViewModelProviders
import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bm.library.Info
import com.bm.library.PhotoView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable
import com.chad.library.adapter.base.BaseQuickAdapter
import com.edu.hrbeu.douban.view.CustomLoadMoreView
import com.edu.hrbeu.jygankclient.R
import com.edu.hrbeu.jygankclient.view.adapter.WelfareAdapter
import com.edu.hrbeu.jygankclient.view.viewmodel.WelfareViewModel
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.fragment_welfare.*
import org.jetbrains.anko.backgroundDrawable
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.image
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * JYGod丶 创建于 17/12/10 下午1:51
 */
class WelfareFragment : LifecycleFragment() {


    private var PAGE = 1
    private lateinit var mAdapter: WelfareAdapter
    private lateinit var viewModel: WelfareViewModel

    companion object {
        val TAG = "WelfareFragment"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welfare, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycler()
        viewModel = ViewModelProviders.of(this).get(WelfareViewModel::class.java)
        viewModel.getList(PAGE.toString())?.observeForever { list ->
            Logger.d("list----->发生变化, listSize:${list?.size}")
            list?.let {
                mAdapter.addData(it)
                mAdapter.loadMoreComplete()
            }
        }
        mAdapter.setOnLoadMoreListener {
            PAGE += 1
            viewModel.getList(PAGE.toString())
        }
        photoView.enable()
        var mInfo: Info? = null
        mAdapter.setOnItemClickListener { adapter, view, position ->
            val img = view.findViewById<ImageView>(R.id.imgWelfare)
            Logger.d("img: ${img.image}, drawable: ${img.drawable}" +
                    "backgroundDrawable: ${img.backgroundDrawable}")
            mInfo = PhotoView.getImageViewInfo(img)
            mAdapter.getItem(position)?._id?.let {
                Glide.with(context)
                        .load(viewModel.getImgById(it)[0].url)
                        .into(photoView)
            }
            photoLayout.visibility = View.VISIBLE
            photoView.animaFrom(mInfo)
        }
        photoView.onClick {
            photoView.animaTo(mInfo) {
                photoLayout.visibility = View.GONE
            }
        }

    }

    private fun initRecycler() {
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        mAdapter = WelfareAdapter()
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