package com.edu.hrbeu.jygankclient.view.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.edu.hrbeu.jygankclient.App
import com.edu.hrbeu.jygankclient.repository.WelfareRepo
import com.edu.hrbeu.jygankclient.repository.room.model.WelfareModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * JYGod丶 创建于 17/12/10 下午3:05
 */
class WelfareViewModel : ViewModel() {

    @Inject lateinit var welfareRepo: WelfareRepo

    private var list = MutableLiveData<List<WelfareModel>>()

    init {
        initializeDagger()
    }

    private fun initializeDagger() = App.appComponent.injectWelfare(this)

    fun getList(page: String): LiveData<List<WelfareModel>>? {
        return welfareRepo.loadList(page)
    }

    fun getImgById(id: String) = welfareRepo.getImgById(id)
}