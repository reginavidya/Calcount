package org.d3if2126.calcount.ui.Lainnya

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if2126.calcount.MainActivity
import org.d3if2126.calcount.R
import org.d3if2126.calcount.model.Diskon
import org.d3if2126.calcount.network.DiskonApi
import org.d3if2126.calcount.network.UpdateWorker
import java.util.concurrent.TimeUnit

class LainnyaViewModel : ViewModel() {

    private val data = MutableLiveData<List<Diskon>>()
    private val status = MutableLiveData<DiskonApi.ApiStatus>()

    init {
        retrieveData()
    }
    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(DiskonApi.ApiStatus.LOADING)
            try {
                data.postValue(DiskonApi.service.getDiskon())
                status.postValue(DiskonApi.ApiStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("LainnyaViewModel", "Failure: ${e.message}")
                status.postValue(DiskonApi.ApiStatus.FAILED)
            }
        }
    }

    fun getDiskon(): LiveData<List<Diskon>> = data
    fun getStatus(): LiveData<DiskonApi.ApiStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()

        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}
