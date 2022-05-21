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

class HitungViewModel(private val db: DiskonDao) : ViewModel() {
    private val hasilDiskon = MutableLiveData<HasilDiskon?>()
    val data = db.getLastDiskon()

    fun hitungDiskon(harga:Float, diskon: Float) {
        val hitungDiskon = harga.toFloat() * (diskon.toFloat() /100)
        val totalDiskon = harga.toFloat() - hitungDiskon
        hasilDiskon.value = HasilDiskon(hitungDiskon, totalDiskon)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataDiskon = DiskonEntity(
                    harga = harga,
                    diskon = diskon)
                db.insert(dataDiskon)
            }
        }
    }
    fun getHasilDiskon(): LiveData<HasilDiskon?> = hasilDiskon
}