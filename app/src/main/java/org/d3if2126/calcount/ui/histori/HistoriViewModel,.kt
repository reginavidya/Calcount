package org.d3if2126.calcount.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if2126.calcount.db.DiskonDao

class HistoriViewModel(private val db: DiskonDao) : ViewModel() {
    val data = db.getLastDiskon()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            db.clearData()
        }
    }
}