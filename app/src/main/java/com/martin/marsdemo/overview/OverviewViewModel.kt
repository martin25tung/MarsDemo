package com.martin.marsdemo.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.martin.marsdemo.network.MarsApi
import com.martin.marsdemo.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()

    val response: LiveData<String>
        get() = _response

    // 4. 創建 Coroutine Job 和 CoroutineScope 使用主線程
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        // 5. 啟動 coroutineScope
        coroutineScope.launch {
            // 6. 呼叫 MarsApi.retrofitService.getProperties
            var getPropertiesDeferred = MarsApi.retrofitService.getProperties()
            try {
                // await 使非阻塞性的，不會卡在主線程，當他完成網路請求，會從上次中斷的地方繼續執行。
                // 這樣寫法就好像沒有切換到別的線程一樣，很直觀
                var listResult = getPropertiesDeferred.await()
                _response.value = "Success: ${listResult.size} Mars properties retrieved"
            } catch (t: Throwable) {
                // 7. 我們用 try catch 來取得錯誤資訊
                _response.value = "Failure: " + t.message
            }
        }
    }

    // 8. 當 viewModel 結束時，必須取消 Coroutine Job
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}