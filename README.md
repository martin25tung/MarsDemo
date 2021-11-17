# MarsDemo


## 取得網路資料

1. build.gradle：新增 Retrofit 依賴
2. MarsApiService：使用 Retrofit Builder 跟 ScalarsConverterFactory 創建實例
3. MarsApiService：定義 api 介面
4. MarsApiService：創建 MarsApi object 來使用 Retrofit 實作 MarsApiService
5. OverviewViewModel：呼叫 MarsApi
6. AndroidManifest：新增網路權限


## 修改json 回傳值，改用moshi 套件解析

1. Build.gradle：新增 Moshi 依賴
2. MarsProperty：創建 data class 符合 Mars API參數
3. MarsApiService：用 Moshi builder 創建 Moshi 物件
4. MarsApiService：改用 moshi 來轉換 json response
5. Build.grade：移除 scalars 依賴
6. MarsApiService：更新 MarsApiService
7. OverviewViewModel：修改回傳值


## 用 Coroutine 來取得網路資料

1. Build.gradle：新增 coroutines 依賴
2. MarsApiService：讓 retrofit 支援 Coroutine API
3. MarsApiService：修改 getProperties()的回傳型態 Deferred
4. OverviewViewModel：創建 Coroutine Job 和 CoroutineScope 使用主線程
5. OverviewViewModel：啟動 coroutineScope
6. OverviewViewModel：呼叫 MarsApi.retrofitService.getProperties
7. OverviewViewModel：我們用 try catch 來取得錯誤資訊
8. OverviewViewModel：當 viewModel 結束時，必須取消 Coroutine Job


## 使用 Glide 讀取網路圖片

1. Build.gradle：新增 Glide 依賴
2. OverviewViewModel：將 _response 改成 _status
3. OverviewViewModel：新增 MarsProperty LiveData
4. OverviewViewModel：將第一個結果給 property  
5. fragment_overview.xml：將 viewModel.property.imgSrcUrl 呈現在 TextView 上
6. BindingAdapters：新增能解析 imgUrl 字串成 URI 的 BindingAdapter
7. BindingAdapters：用 Glide 來 load 圖
8. grid_view_item.xml：新增 viewModel data
9. grid_view_item.xml：將 imageUrl 參數設定成 viewModel.property.imgSrcUrl
10. OverviewFragment：改成用 GridViewItemBinding 顯示
11. BindingAdapters：用 RequestOptions 來加上 loading 或 error 的圖片

[material icons](https://fonts.google.com/icons)