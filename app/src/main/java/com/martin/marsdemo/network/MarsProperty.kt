package com.martin.marsdemo.network


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

// 13. 將 MarsProperty 宣告成 Parcelize
@Parcelize
data class MarsProperty(
    val id: String,
    @Json(name = "img_src") val imgSrcUrl: String,
    val type: String,
    val price: Double
) : Parcelable {
    // 18. 新增 isRental 參數
    val isRental
        get() = type == "rent"
}