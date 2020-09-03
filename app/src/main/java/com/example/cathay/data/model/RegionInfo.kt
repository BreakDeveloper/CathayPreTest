package com.example.cathay.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "region_table")
data class RegionInfo(
    @PrimaryKey
    @ColumnInfo(name = "region_name") var regionName: String = "",
    @ColumnInfo(name = "region_pic_url") var regionPicUrl: String = "",
    @ColumnInfo(name = "region_info") var regionInfo: String = "",
    @ColumnInfo(name = "region_web_url") var regionWebUrl: String = "",
    @ColumnInfo(name = "region_memo") var regionMemo: String = ""
) : Serializable