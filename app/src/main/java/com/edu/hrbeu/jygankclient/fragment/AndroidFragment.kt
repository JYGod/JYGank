package com.edu.hrbeu.jygankclient.fragment

import android.arch.lifecycle.LifecycleFragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.edu.hrbeu.jygankclient.R
import kotlinx.android.synthetic.main.fragment_android.*

/**
 * JYGod丶 创建于 17/12/8 下午7:23
 */
class AndroidFragment : LifecycleFragment() {

//    lateinit var mRecyclerView: RecyclerView

    companion object {
        val TAG = "AndroidFragment"
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_android, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(context)

    }

}