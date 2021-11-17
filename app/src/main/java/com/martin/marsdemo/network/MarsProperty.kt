package com.martin.marsdemo.network

import com.squareup.moshi.Json


// 2. 創建 data class 符合 Mars API參數
data class MarsProperty(
    val id: String,
    // 可以用Json註解來符合api參數，讓變數符合kotlin code 駝峰規範
    @Json(name = "img_src") val imgSrcUrl: String,
    val type: String,
    val price: Double
)