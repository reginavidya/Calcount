package org.d3if2126.calcount.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DiskonDao {
    @Insert
    fun insert(diskon: DiskonEntity)

    @Query("SELECT * FROM diskon1 ORDER BY id DESC")
    fun getLastDiskon(): LiveData<List<DiskonEntity>>
}