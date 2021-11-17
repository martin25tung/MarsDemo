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

class OverviewViewModel : ViewModel() {

    // 2. 將 _response 改成 _status
    private val _status = MutableLiveData<String>()

    val status: LiveData<String>
        get() = _status

    // 3. 新增 MarsProperty LiveData
    private val _property = MutableLiveData<MarsProperty>()
    val property: LiveData<MarsProperty>
        get() = _property


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        coroutineScope.launch {
            var getPropertiesDeferred = MarsApi.retrofitService.getProperties()
            try {
                var listResult = getPropertiesDeferred.await()
                _status.value = "Success: ${listResult.size} Mars properties retrieved"

                // 4. 將第一個結果給 property
                if (listResult.size > 0) {
                    _property.value = listResult[0]
                }
            } catch (t: Throwable) {
                _status.value = "Failure: " + t.message
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}