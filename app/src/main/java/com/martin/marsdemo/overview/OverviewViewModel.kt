package com.martin.marsdemo.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.martin.marsdemo.network.MarsApi
import com.martin.marsdemo.network.MarsApiFilter
import com.martin.marsdemo.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


enum class MarsApiStatus{ LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()
    val status: LiveData<MarsApiStatus>
        get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>>
        get() = _properties


    private val _navigateToSelectedProperty = MutableLiveData<MarsProperty>()
    val navigateToSelectedProperty: LiveData<MarsProperty>
        get() = _navigateToSelectedProperty


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        // 5. 傳入 MarsApiFilter.SHOW_ALL
        getMarsRealEstateProperties(MarsApiFilter.SHOW_ALL)
    }

    // 3. 在 getMarsRealEstateProperties 新增 MarsApiFilter 參數
    private fun getMarsRealEstateProperties(filter: MarsApiFilter) {
        coroutineScope.launch {
            // 4. 傳入 filter 參數
            var getPropertiesDeferred = MarsApi.retrofitService.getProperties(filter.value)
            try {
                _status.value = MarsApiStatus.LOADING
                var listResult = getPropertiesDeferred.await()
                _status.value = MarsApiStatus.DONE
                if (listResult.size > 0) {
                    _properties.value = listResult
                }
            } catch (t: Throwable) {
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }


    fun displayPropertyDetails(marsProperty: MarsProperty) {
        _navigateToSelectedProperty.value = marsProperty
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedProperty.value = null
    }

    // 6. 新增 updateFilter 方法
    fun updateFilter(filter: MarsApiFilter) {
        getMarsRealEstateProperties(filter)
    }
}