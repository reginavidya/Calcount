package org.d3if2126.calcount.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2126.calcount.db.DiskonDao
import org.d3if2126.calcount.db.DiskonDb
import org.d3if2126.calcount.db.DiskonEntity
import org.d3if2126.calcount.model.HasilDiskon
import org.d3if2126.calcount.model.hitungDiskon

class HitungViewModel(private val db: DiskonDao) : ViewModel() {
    private val hasilDiskon = MutableLiveData<HasilDiskon?>()

    fun hitungDiskon(harga:Float, diskon: Float) {
        val dataDiskon = DiskonEntity(
            harga = harga,
            diskon = diskon
        )
        hasilDiskon.value = dataDiskon.hitungDiskon()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataDiskon)
            }
        }
    }
    fun getHasilDiskon(): LiveData<HasilDiskon?> = hasilDiskon
}