package com.example.lovecalculator.room

import androidx.room.Insert
import androidx.room.Query
import com.example.lovecalculator.remote.LoveModel

@androidx.room.Dao
interface LoveDao {
    @Insert
    fun insert(loveModel: LoveModel)

    @Query("SELECT * FROM `love table` ORDER BY firstName ASC")
    fun getAll():List<LoveModel>
}