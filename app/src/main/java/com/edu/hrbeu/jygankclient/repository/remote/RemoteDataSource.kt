package com.edu.hrbeu.jygankclient.repository.remote

import com.edu.hrbeu.jygankclient.repository.remote.api.RemoteService
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * JYGod丶 创建于 17/12/9 下午3:24
 */
class RemoteDataSource @Inject constructor(private val service: RemoteService) {

    fun loadAndroidList(page: String) = service.getAndroidList(page)
}