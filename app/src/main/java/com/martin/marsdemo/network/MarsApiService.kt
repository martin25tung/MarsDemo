package com.martin.marsdemo.network

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


private const val BASE_URL = "https://mars.udacity.com/"

// 2. 使用 Retrofit Builder 跟 ScalarsConverterFactory 創建實例
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

// 3. 定義 api 介面
interface MarsApiService {

    @GET("realestate")
    fun getProperties(): Call<String>

}

// 4. 創建 MarsApi object 來使用 Retrofit 實作 MarsApiService
object MarsApi {
    val retrofitService : MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }
}