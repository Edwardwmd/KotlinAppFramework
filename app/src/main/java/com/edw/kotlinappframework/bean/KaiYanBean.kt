package com.edw.kotlinappframework.bean

/**
 * Author: EdwardWMD
 * Data: 2021/3/8
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */

class KaiYanBean constructor(
    val dialog: Any,
    val issueList: List<Issue>,
    val newestIssueType: String,
    val nextPageUrl: String,
    val nextPublishTime: Long
) {


    data class Issue(
        val count: Int,
        val date: Long,
        val itemList: List<Item>,
        val publishTime: Long,
        val releaseTime: Long,
        val type: String
    )

    data class Item(
        val adIndex: Int,
        val `data`: Data,
        val id: Int,
        val tag: Any,
        val trackingData: Any,
        val type: String
    )

    data class Data(
        val actionUrl: String,
        val ad: Boolean,
        val adTrack: Any,
        val author: Author,
        val autoPlay: Boolean,
        val brandWebsiteInfo: Any,
        val campaign: Any,
        val category: String,
        val collected: Boolean,
        val consumption: Consumption,
        val cover: Cover,
        val dataType: String,
        val date: Long,
        val description: String,
        val descriptionEditor: String,
        val descriptionPgc: Any,
        val duration: Int,
        val favoriteAdTrack: Any,
        val header: Any,
        val id: Int,
        val idx: Int,
        val ifLimitVideo: Boolean,
        val image: String,
        val label: Any,
        val labelList: Any,
        val lastViewTime: Any,
        val library: String,
        val playInfo: List<PlayInfo>,
        val playUrl: String,
        val played: Boolean,
        val playlists: Any,
        val promotion: Any,
        val provider: Provider,
        val reallyCollected: Boolean,
        val recallSource: Any,
        val recall_source: Any,
        val releaseTime: Long,
        val remark: String,
        val resourceType: String,
        val searchWeight: Int,
        val shade: Boolean,
        val shareAdTrack: Any,
        val slogan: Any,
        val src: Any,
        val subtitles: List<Any>,
        val tags: List<Tag>,
        val thumbPlayUrl: Any,
        val title: String,
        val titlePgc: Any,
        val type: String,
        val videoPosterBean: VideoPosterBean,
        val waterMarks: Any,
        val webAdTrack: Any,
        val webUrl: WebUrl
    )

    data class Author(
        val adTrack: Any,
        val approvedNotReadyVideoCount: Int,
        val description: String,
        val expert: Boolean,
        val follow: Follow,
        val icon: String,
        val id: Int,
        val ifPgc: Boolean,
        val latestReleaseTime: Long,
        val link: String,
        val name: String,
        val recSort: Int,
        val shield: Shield,
        val videoNum: Int
    )

    data class Consumption(
        val collectionCount: Int,
        val realCollectionCount: Int,
        val replyCount: Int,
        val shareCount: Int
    )

    data class Cover(
        val blurred: String,
        val detail: String,
        val feed: String,
        val homepage: String,
        val sharing: Any
    )

    data class PlayInfo(
        val height: Int,
        val name: String,
        val type: String,
        val url: String,
        val urlList: List<Url>,
        val width: Int
    )

    data class Provider(
        val alias: String,
        val icon: String,
        val name: String
    )

    data class Tag(
        val actionUrl: String,
        val adTrack: Any,
        val bgPicture: String,
        val childTagIdList: Any,
        val childTagList: Any,
        val communityIndex: Int,
        val desc: Any,
        val haveReward: Boolean,
        val headerImage: String,
        val id: Int,
        val ifNewest: Boolean,
        val name: String,
        val newestEndTime: Any,
        val tagRecType: String
    )

    data class VideoPosterBean(
        val fileSizeStr: String,
        val scale: Double,
        val url: String
    )

    data class WebUrl(
        val forWeibo: String,
        val raw: String
    )

    data class Follow(
        val followed: Boolean,
        val itemId: Int,
        val itemType: String
    )

    data class Shield(
        val itemId: Int,
        val itemType: String,
        val shielded: Boolean
    )

    data class Url(
        val name: String,
        val size: Int,
        val url: String
    )
}


