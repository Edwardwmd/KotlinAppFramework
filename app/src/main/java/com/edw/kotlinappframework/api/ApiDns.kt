package com.edw.kotlinappframework.api

import android.util.Log
import okhttp3.Dns
import java.net.InetAddress
import java.net.UnknownHostException

/**
 * Author: EdwardWMD
 * Data: 2021/2/23
 * Project: KotlinUseDev
 * Website: https://github.com/Edwardwmd
 * Desc: 自定义DNS,使用DNS解析
 */
class ApiDns : Dns {
    override fun lookup(hostname: String): List<InetAddress> {
        Log.e("DNS------>", hostname)//hostname:api.apiopen.top
        return try {
            //尝试使用系统默认解析
            Dns.SYSTEM.lookup(hostname)
        } catch (e: UnknownHostException) {
            //系统解析失败后尝试使用IP解析
            if (hostname.contains(Api.HOSTNAME)) {
                InetAddress.getAllByName(IPAddr).toList()
            } else {
                throw (e)
            }
        }
    }

    //单例模式
    companion object {
        //这个是对应的hostname的IP地址,服务器中可获取
        const val IPAddr = "47.100.122.205"
        val INSTANCE: ApiDns by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) { ApiDns() }
    }
}