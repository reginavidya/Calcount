package org.d3if2126.calcount.ui.Lainnya

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2126.calcount.R
import org.d3if2126.calcount.model.Diskon
import org.d3if2126.calcount.network.DiskonApi
import java.util.concurrent.TimeUnit

class LainnyaViewModel : ViewModel() {

    private val data = MutableLiveData<List<Diskon>>()


    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            try {
                data.postValue(DiskonApi.service.getDiskon())
            } catch (e: Exception) {
                Log.d("LainnyaViewModel", "Failure: ${e.message}")
            }
        }
    }

    fun getDiskon(): LiveData<List<Diskon>> = data
}