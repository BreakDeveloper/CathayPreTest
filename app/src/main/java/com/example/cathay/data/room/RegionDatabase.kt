package com.example.cathay.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cathay.data.model.RegionInfo

@Database(entities = [RegionInfo::class], version = 1)
abstract class RegionDatabase : RoomDatabase() {
    abstract fun regionDao(): RegionDao
}