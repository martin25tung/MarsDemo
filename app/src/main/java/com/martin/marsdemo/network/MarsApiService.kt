package com.martin.marsdemo.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://mars.udacity.com/"

// 3. 用 Moshi builder 創建 Moshi 物件
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    // 4. 改用 moshi 來轉換 json response
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()


// 6. 更新 MarsApiService
interface MarsApiService {
    // 改成回傳 MarsProperty
    @GET("realestate")
    fun getProperties(): Call<List<MarsProperty>>

}

object MarsApi {
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}