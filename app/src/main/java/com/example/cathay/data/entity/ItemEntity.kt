package com.example.cathay.data.entity

import androidx.annotation.IntDef
import com.example.cathay.base.BaseEntity
import com.example.cathay.data.bean.ItemBean

class ItemEntity(
    @ItemType type: Int,
    var bean: ItemBean
) : BaseEntity() {
    init {
        mItemType = type
    }

    companion object {
        const val ITEM_DETAIL = 0
        const val ITEM_HOME = 1
    }

    @IntDef(
        ITEM_HOME,
        ITEM_DETAIL
    )
    @Retention(AnnotationRetention.SOURCE)
    annotation class ItemType
}