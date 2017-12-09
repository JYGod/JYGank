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

    private var list = MutableLiveData<List<AndroidModel>>()


    init {
        initializeDagger()
    }

    private fun initializeDagger() = App.appComponent.inject(this)

//    fun getList(): LiveData<List<AndroidModel>>? {
//        if (list == null) {
//            list = MutableLiveData()
//            loadList()
//        }
//        return list
//    }

    fun getList(page: String): MutableLiveData<List<AndroidModel>>? {
        androidRepo.loadList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ entity ->
                    list.value = entity.results
                }, { t: Throwable -> t.printStackTrace() })
        return list
    }


//    private fun loadList() {
//        repo.getAndroidList().enqueue(object : Callback<List<AndroidModel>>{
//            override fun onFailure(call: Call<List<AndroidModel>>?, t: Throwable?) {
//
//            }
//
//            override fun onResponse(call: Call<List<AndroidModel>>?, response: Response<List<AndroidModel>>?) {
//                Logger.d(response?.body())
//            }
//        })
//    }

}