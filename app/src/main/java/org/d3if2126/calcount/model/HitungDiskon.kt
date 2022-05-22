package org.d3if2126.calcount.model

import org.d3if2126.calcount.db.DiskonEntity

fun DiskonEntity.hitungDiskon():HasilDiskon{
    val hitungDiskon1 = harga * (diskon/100)
    val totalDiskon = harga - hitungDiskon1
    return HasilDiskon(hitungDiskon1, totalDiskon)
}