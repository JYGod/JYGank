package com.edu.hrbeu.jygankclient.repository

import android.arch.lifecycle.MutableLiveData
import com.edu.hrbeu.jygankclient.repository.room.AppDatabase
import com.edu.hrbeu.jygankclient.repository.remote.RemoteDataSource
import com.edu.hrbeu.jygankclient.repository.room.model.AndroidModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

/**
 * JYGod丶 创建于 17/12/9 下午12:49
 */
@Singleton
class AndroidRepo @Inject constructor(
        private val roomDataSource: AppDatabase,
        private val remoteDataSource: RemoteDataSource) {

    fun loadList(page: String) =  remoteDataSource.loadAndroidList(page)

}


