package com.martin.marsdemo.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.martin.marsdemo.R
import com.martin.marsdemo.network.MarsProperty


class DetailViewModel(
    @Suppress("UNUSED_PARAMETER")
    marsProperty: MarsProperty,
    app: Application
    ) : AndroidViewModel(app) {

    // 2. 新增 selected 的 MarsProperty LiveData
    private val _selectedProperty = MutableLiveData<MarsProperty>()
    val selectedProperty : LiveData<MarsProperty>
        get() = _selectedProperty

    init {
        _selectedProperty.value = marsProperty
    }

    // 19. 新增 displayPropertyPrice 與 displayPropertyType 方法用 Transformations.map 轉換資源
    val displayPropertyPrice = Transformations.map(selectedProperty) {
        app.applicationContext.getString(
            when(it.isRental){
            true -> R.string.display_price_monthly_rental
            false -> R.string.display_price
        }, it.price)
    }

    val displayPropertyType = Transformations.map(selectedProperty) {
        app.applicationContext.getString(R.string.display_type,
            app.applicationContext.getString(
                when(it.isRental) {
                    true -> R.string.type_rent
                    false -> R.string.type_sale
                }
            ))
    }
}