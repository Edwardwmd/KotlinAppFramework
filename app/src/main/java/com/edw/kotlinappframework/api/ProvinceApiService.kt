package com.edw.kotlinappframework.api

import com.edw.kotlinappframework.bean.Province
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

/**
 * Author: EdwardWMD
 * Data: 2021/3/2
 * Project: KotlinAppFramework
 * Website: https://github.com/Edwardwmd
 * Desc: File Information!
 */
interface ProvinceApiService {
    @GET(Api.PROVINCE_URL)
     fun getProvinces():Observable<List<Province>>
}