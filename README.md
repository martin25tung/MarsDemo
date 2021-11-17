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


## 套用 RecyclerView

1. Build.gradle：新增 RecyclerView 依賴
2. OverviewViewModel：將 _property 改成 List<MarsProperty>
3. grid_view_item.xml：將 viewModel 改成 MarsProperty type
4. OverviewFragment：改回 FragmentOverviewBinding
5. fragment_overview.xml：將 TextView 改成 RecyclerView
6. PhotoGridAdapter：新增 PhotoGridAdapter 並加上 DiffCallback 
7. PhotoGridAdapter：新增 DiffCallback 實作
8. PhotoGridAdapter：創建 MarsPropertyViewHolder
9. PhotoGridAdapter：實作 onCreateViewHolder 功能
10. bindRecyclerView：新增綁定 RecyclerView 與 submitList 的方法
11. fragment_overview.xml：將 viewModel.properties 綁在 RecyclerView 上
12. OverviewFragment：宣告 RecyclerView 的 Adapter 是 PhotoGridAdapter
13. fragment_overview.xml：將 clipToPadding 為 false


## 處理 RecyclerView 錯誤狀態

1. OverviewViewModel：創建一個 MarsApiStatus 列舉三種狀態
2. OverviewViewModel：修改 _status 的資料型別為 MarsApiStatus
3. OverviewViewModel：設定取得網路資料的三種狀態
4. BindingAdapter：新增一個方法 用 MarsApi 狀態來決定用什麼圖
5. fragment_overview.xml：新增 ImageView 顯示狀態


## MarsProperty 序列化

1. Build.gradle：新增 plugins kotlin-parcelize
2. MarsProperty：將 MarsProperty 宣告成 Parcelize

## Detail 畫面

1. build.gradle：新增 Navigation 依賴
2. DetailViewModel：新增 selected 的 MarsProperty LiveData
3. fragment_detail：新增 data binding 的 data 參數
4. fragment_detail：在 ImageView 的 imageUrl 新增 viewModel的 selectedProperty.imgSrcUrl
5. fragment_detail：在 TextView 裡加上資料綁定
6. OverviewViewModel：新增 navigateToSelectedProperty MutableLiveData
7. OverviewViewModel：新增顯示詳細頁方法
8. PhotoGridAdapter：創建一個 OnClickListener class
9. PhotoGridAdapter：在 PhotoGridAdapter 的建構子裡面，新增 onClickListener 的參數
10. PhotoGridAdapter：pass onClickLister 方法
11. OverviewFragment：實作 OnClickListener
12. nav_graph.xml： 加上 selectedProperty 參數
13. MarsProperty：將 MarsProperty 宣告成 Parcelize
14. OverviewFragment：觀察 navigateToSelectedProperty ，導航至DetailFragment
15. DetailFragment：從 fragment arguments 取得 selectedProperty
16. DetailFragment：宣告 DetailViewModelFactory 實例
17. DetailFragment：用 ViewModelProvider 宣告出 DetailViewModel 實例
18. MarsProperty： 新增 isRental 參數
19. DetailViewModel：新增 displayPropertyPrice 與 displayPropertyType 方法用 Transformations.map 轉換資源
20. fragment_detail：修改資料綁定，改為綁定 viewModel.displayPropertyType


## 加上過濾器功能

1. MarsApiService：新增列舉三種過濾狀態
2. MarsApiService：加上 filter 參數
3. OverviewViewModel：在 getMarsRealEstateProperties 新增 MarsApiFilter 參數
4. OverviewViewModel：傳入 filter 參數
5. OverviewViewModel：傳入 MarsApiFilter.SHOW_ALL
6. OverviewViewModel：新增 updateFilter 方法
7. OverviewFragment：在 onOptionsItemSelected 裡，控制 viewModel.updateFilter
