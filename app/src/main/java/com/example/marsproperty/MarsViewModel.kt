package com.example.marsproperty

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MarsViewModel:ViewModel() {
    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties:LiveData<List<MarsProperty>>
    get() = _properties
    init {
        getMarsRealStateProperties()
    }

    private fun getMarsRealStateProperties() {
        var viewModelJob = Job()
        val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main )
        coroutineScope.launch {
            var getPropertiesDeferred = MarsApi.retrofitService.getProperties()
            try {
                var marsProperty = getPropertiesDeferred.await()
                _properties.value = marsProperty

            } catch (e: Exception) {
                Log.e("ERROR", e.toString())
            }
        }
    }

}

