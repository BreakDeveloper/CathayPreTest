package com.example.cathay.base

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder

abstract class BaseMultiItemQuickAdapter<T : MultiItemEntity> :
    BaseMultiItemQuickAdapter<T, BaseViewHolder>(mutableListOf()), LoadMoreModule
