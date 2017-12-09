package com.edu.hrbeu.jygankclient.di.component


import com.edu.hrbeu.jygankclient.di.module.AppModule
import com.edu.hrbeu.jygankclient.di.module.RemoteModule
import com.edu.hrbeu.jygankclient.di.module.RoomModule
import com.edu.hrbeu.jygankclient.view.viewmodel.AndroidViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * JYGod丶 创建于 17/12/8 下午9:39
 */

@Component(modules = arrayOf(AppModule::class, RoomModule::class, RemoteModule::class))
@Singleton
interface AppComponent {

    fun inject(viewModel: AndroidViewModel)

}