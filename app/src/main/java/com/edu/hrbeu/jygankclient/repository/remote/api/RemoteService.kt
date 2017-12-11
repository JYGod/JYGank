package com.edu.hrbeu.jygankclient.repository.remote.api

import com.edu.hrbeu.jygankclient.entity.AndroidEntity
import com.edu.hrbeu.jygankclient.entity.WelfareEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * JYGod丶 创建于 17/12/9 下午12:46
 */
interface RemoteService {

    @GET("data/{type}/10/{page}")
    fun getAndroidList(@Path("page") page:String, @Path("type") type:String):Observable<AndroidEntity>

    @GET("data/福利/10/{page}")
    fun getWelfareList(@Path("page") page:String):Observable<WelfareEntity>

}