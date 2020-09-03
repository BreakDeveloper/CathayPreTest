package com.example.cathay.data.respository

import com.example.cathay.data.model.RegionInfo
import com.example.cathay.data.room.RegionDao

class RegionDataRepository(private val regionDao: RegionDao) {
    fun loadRegionList(): List<RegionInfo> = regionDao.getAllList()
    fun updateAllRegion(saveList: List<RegionInfo>) = regionDao.updateAllRate(saveList)
}