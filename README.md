# MarsDemo







###修改json 回傳值，改用moshi 套件解析

1. Build.grade ：新增 Moshi 依賴
2. MarsProperty：創建 data class 符合 Mars API參數
3. MarsApiService：用 Moshi builder 創建 Moshi 物件
4. MarsApiService：改用 moshi 來轉換 json response
5. Build.grade：移除 scalars 依賴
6. MarsApiService：更新 MarsApiService
7. OverviewViewModel：修改回傳值
