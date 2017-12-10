package com.edu.hrbeu.jygankclient.repository

import android.arch.lifecycle.LiveData
import com.edu.hrbeu.jygankclient.repository.remote.RemoteDataSource
import com.edu.hrbeu.jygankclient.repository.room.AppDatabase
import com.edu.hrbeu.jygankclient.repository.room.model.WelfareModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * JYGod丶 创建于 17/12/10 下午3:07
 */
@Singleton
class WelfareRepo @Inject constructor(
        private val roomDataSource: AppDatabase,
        private val remoteDataSource: RemoteDataSource) {

    fun loadList(page: String) : LiveData<List<WelfareModel>>? {
        remoteDataSource.loadWelfareList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ entity ->
                    entity.results?.let { resList ->
                        roomDataSource.welfareDao().insertList(resList)
                    }
                }, { t: Throwable -> t.printStackTrace() })
        return roomDataSource.welfareDao().selectList()
    }

    fun getImgById(id: String) = roomDataSource.welfareDao().selectById(id)
}