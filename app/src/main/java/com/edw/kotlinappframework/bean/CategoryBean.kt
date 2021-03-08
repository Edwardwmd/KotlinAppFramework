package com.edw.kotlinappframework.bean

/**
 * Author: EdwardWMD
 * Data: 2021/3/8
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
class CategoryBean : ArrayList<CategoryBeanItem>()

data class CategoryBeanItem(
    val alias: Any,
    val bgColor: String,
    val bgPicture: String,
    val defaultAuthorId: Int,
    val description: String,
    val headerImage: String,
    val id: Int,
    val name: String,
    val tagId: Int
)