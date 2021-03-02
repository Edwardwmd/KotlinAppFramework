package com.edw.kotlinappframework.net

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
class ApiDns private constructor(private val ipAddr: String,private val host:String) : Dns {
    override fun lookup(hostname: String): List<InetAddress> {
        Log.i("DNS------>", "当前的DNS: $hostname")
        return try {
            //尝试使用系统默认解析
            Dns.SYSTEM.lookup(hostname)
        } catch (e: UnknownHostException) {
            //系统解析失败后尝试使用IP解析
            if (hostname.contains(host)) {
                InetAddress.getAllByName(ipAddr).toList()
            } else {
                throw (e)
            }
        }
    }

    companion object {
        @Volatile
        private var instance: ApiDns? = null
        fun genInstance(ipAddr: String,host:String) = instance ?: synchronized(this) {
            instance ?: ApiDns(ipAddr,host).apply {
                instance = this
            }
        }

    }

}