package com.edw.kotlinappframework.bean

/**
 * Author: EdwardWMD
 * Data: 2021/3/8
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
 data class TabInfoBean(
    val tabInfo: TabInfo
)

data class TabInfo(
    val defaultIdx: Int,
    val tabList: List<Tab>
)

data class Tab(
    val adTrack: Any,
    val apiUrl: String,
    val id: Int,
    val name: String,
    val nameType: Int,
    val tabType: Int
)