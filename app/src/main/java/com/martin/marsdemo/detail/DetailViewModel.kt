package com.martin.marsdemo.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.martin.marsdemo.network.MarsProperty


class DetailViewModel(
    @Suppress("UNUSED_PARAMETER")
    marsProperty: MarsProperty,
    app: Application
    ) : AndroidViewModel(app) {
}