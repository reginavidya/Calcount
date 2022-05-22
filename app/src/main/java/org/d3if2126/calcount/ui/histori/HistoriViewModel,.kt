package org.d3if2126.calcount.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if2126.calcount.db.DiskonDao

class HistoriViewModel(db: DiskonDao) : ViewModel() {
    val data = db.getLastDiskon()
}