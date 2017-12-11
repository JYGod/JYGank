package com.edu.hrbeu.jygankclient.view.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.edu.hrbeu.jygankclient.App
import com.edu.hrbeu.jygankclient.repository.room.model.AndroidModel
import com.edu.hrbeu.jygankclient.repository.AndroidRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * JYGod丶 创建于 17/12/9 上午9:16
 */
class AndroidViewModel : ViewModel() {

    @Inject lateinit var androidRepo: AndroidRepo

    init {
        initializeDagger()
    }

    private fun initializeDagger() = App.appComponent.injectAndroid(this)

    fun getList(page: String, type: String): LiveData<List<AndroidModel>>? {
        return androidRepo.loadList(page, type)
    }

}