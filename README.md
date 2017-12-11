# 中文说明

### 关键技术

- **语言：Kotlin**
- **Google官方推荐框架： Architecture Component（ Room + Lifecycle + ViewModel + LiveData )**
- **本地数据持久化 : Room**
- **依赖注入：Dagger2**
- **网络请求：Retrofit2 + OkHttp3**
- **rxKotlin + rxAndroid**




### 架构原则

首先是模块分离的原则，Activity 和 Fragment 中只做界面组件初始化操作而不做其他业务逻辑的操作。

其次是利用 Model 驱动 UI ， 其中 Model 最好是持久化的 Model，保证应用销毁、网络中断后不会丢失数据。Model只负责针对数据，而不关注UI的问题。

ViewModel 作为 Model 和 UI 的桥梁，当 Model 中的数据发生改变时， ViewModel控制相应的 UI 来将数据的变换反映到页面上。



## 用法

下面，我将结合自己写的 Demo 来介绍一下框架的具体使用。

Demo 暂时写得比较简单，以后会不定期增加功能或美化UI... 项目中使用到的API来源于
[干货集中营](http://gank.io/api)


**程序效果图如下：** 



<img src="/screenshots/UI.png" width="250" alt="screenshot" title="screenshot"/><img src="/screenshots/meizhi.png" width="250"alt="screenshot" title="screenshot" />



### 整体思路

![AC](/screenshots/AC.png)

#### Activity

这个就不多说了...

```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
                .add(R.id.main_container, AndroidFragment(), AndroidFragment.TAG)
                .commit()
    }
```

#### Fragment

在 Fragment 中只进行界面组件的初始化和相应的界面改变操作

```kotlin
override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        toolbar.setTitleTextColor(context.resources.getColor(R.color.pureWhite))
        initRecycler()
        viewModel = ViewModelProviders.of(this).get(AndroidViewModel::class.java)
        viewModel.getList(PAGE.toString())?.observeForever { list ->
            list?.let {
                mAdapter.addData(it)
                mAdapter.loadMoreComplete()
            }
        }
        mAdapter.setOnLoadMoreListener {
            viewModel.getList((PAGE++).toString())
        }
    }
```

这里的 `viewModel.getList()` 返回的是一个 LiveData 类型的 list，**LiveDate** 是一个可观察的数据持有者，在 ViewModel 中持有一个 list 的实例，当这个 list 的值改变时，viewModel 都会将改变信息返回给 Fragment，通知Fragment更新UI。

因此，只需要在 Fragment 初始化时 使用 LiveData 的 **observe**方法来监听数据的改变即可。

**但是**，这里有个问题， 如果只是在 ViewModel 中持有这个 list，在退出应用或其他异常时都可能丢失数据... 这样是不科学的🤔。因此需要一个能够持久化数据的方案，所以就引入了 **Room** 作为数据持久化的策略。

然而，viewModel 中的数据都来自 **Repository**，它是提供数据的地方，数据来源分两种，一种是通过远程的 Web API 获取数据；另一种是通过本地 Room 获取本地数据库中的数据。

为了保证**单一数据来源**的原则，这里我的策略是：向 viewModel 提供的数据都是来自本地 RoomDatabase，从Web API 获取的数据都会存入 Room 中，这样，我们需要观察的 LiveData 就可以直接来源于 Room了。

### ViewModel

```kotlin

    fun getList(page: String): LiveData<List<AndroidModel>>? {
        return androidRepo.loadList(page)
    }
```



### Repository

从远程 API 获取的数据都先存入RoomDatabase中，提供给viewModel的数据唯一来源是roomDatabase，返回LiveData类型可供Fragment观察。

```kotlin
 fun loadList(page: String): LiveData<List<AndroidModel>>? {
        remoteDataSource.loadAndroidList(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ entity ->
                    entity.results?.let { resList ->
                        roomDataSource.androidDao().insertList(resList)
                    }
                }, { t: Throwable -> t.printStackTrace() })
        return roomDataSource.androidDao().selectList()
    }
```



学生狗…🐶，平时比较忙...  前段时间研究了下google推荐框架，一直没时间去实践…之后会一直更新学习该框架的状态~





