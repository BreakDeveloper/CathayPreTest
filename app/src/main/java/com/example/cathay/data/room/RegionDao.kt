package com.example.cathay.data.room

import androidx.room.*
import com.example.cathay.data.model.RegionInfo

@Dao
interface RegionDao {
    @Query("SELECT * from region_table")
    fun getAllList(): List<RegionInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateAllRate(regionList: List<RegionInfo>)
}