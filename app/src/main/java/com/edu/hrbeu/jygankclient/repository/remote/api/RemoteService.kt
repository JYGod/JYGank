package com.edu.hrbeu.jygankclient.repository.remote.api

import com.edu.hrbeu.jygankclient.entity.ResultEntity
import com.edu.hrbeu.jygankclient.repository.room.model.AndroidModel
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * JYGod丶 创建于 17/12/9 下午12:46
 */
interface RemoteService {

    @GET("data/Android/10/{page}")
    fun getAndroidList(@Path("page") page:String):Observable<ResultEntity>
}