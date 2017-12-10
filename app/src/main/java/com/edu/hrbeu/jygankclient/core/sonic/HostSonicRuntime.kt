package com.edu.hrbeu.jygankclient.core.sonic

import android.content.Context
import android.os.Build
import android.text.TextUtils
import android.util.Log
import android.webkit.CookieManager
import android.webkit.WebResourceResponse
import com.orhanobut.logger.Logger
import com.tencent.sonic.sdk.SonicRuntime
import com.tencent.sonic.sdk.SonicSessionClient
import java.io.InputStream

/**
 * JYGod丶 创建于 17/12/10 下午5:54
 */
class HostSonicRuntime constructor(context: Context) : SonicRuntime(context) {
    override fun isSonicUrl(url: String?): Boolean {
        return true
    }

    override fun showToast(text: CharSequence?, duration: Int) {

    }

    override fun setCookie(url: String?, cookies: MutableList<String>?): Boolean {
        if (!TextUtils.isEmpty(url) && cookies != null && cookies.size > 0) {
            val cookieManager = CookieManager.getInstance()
            for (cookie in cookies) {
                cookieManager.setCookie(url, cookie)
            }
            return true
        }
        return false
    }

    override fun getCookie(url: String?): String? {
        return CookieManager.getInstance().getCookie(url)
    }

    override fun log(tag: String?, level: Int, message: String?) {
        when (level) {
            Log.ERROR -> Logger.e(message)
            Log.INFO -> Logger.i(message)
            else -> Logger.d(message)
        }
    }

    override fun getUserAgent(): String {
        return "Mozilla/5.0 (Linux; Android 5.1.1; Nexus 6 Build/LYZ28E) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/61.0.3163.100 Mobile Safari/537.36"
    }

    override fun createWebResourceResponse(mimeType: String?, encoding: String?, data: InputStream?, headers: MutableMap<String, String>?): Any {
        val resourceResponse = WebResourceResponse(mimeType, encoding, data)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            resourceResponse.responseHeaders = headers
        }
        return resourceResponse
    }

    override fun isNetworkValid(): Boolean {
        return true
    }

    override fun getCurrentUserAccount(): String {
        return ""
    }

    override fun postTaskToThread(task: Runnable?, delayMillis: Long) {
        val thread = Thread(task, "SonicThread")
        thread.start()
    }

    override fun notifyError(client: SonicSessionClient?, url: String?, errorCode: Int) {

    }
}