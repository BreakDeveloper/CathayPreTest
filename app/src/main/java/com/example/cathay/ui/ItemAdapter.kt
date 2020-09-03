package com.example.cathay.ui

import android.view.View
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.cathay.R
import com.example.cathay.base.BaseMultiItemQuickAdapter
import com.example.cathay.data.entity.ItemEntity
import com.example.cathay.data.entity.ItemEntity.Companion.ITEM_DETAIL
import com.example.cathay.data.entity.ItemEntity.Companion.ITEM_HOME
import com.example.cathay.load
import kotlinx.android.synthetic.main.item_info.view.*

class ItemAdapter : BaseMultiItemQuickAdapter<ItemEntity>() {

    init {
        addItemType(ITEM_HOME, R.layout.item_info)
        addItemType(ITEM_DETAIL, R.layout.item_info)
    }

    override fun convert(holder: BaseViewHolder, item: ItemEntity) {
        val bean = item.bean
        when (holder.itemViewType) {
            ITEM_HOME -> {
                holder.itemView.apply {
                    if (bean.picUrl.isNotEmpty()) info_pic.load(bean.picUrl)
                    info_title.text = bean.title
                    info_content.text = bean.content
                    info_memo.text = bean.memo
                }
            }
            ITEM_DETAIL -> {
                holder.itemView.apply {
                    if (bean.picUrl.isNotEmpty()) info_pic.load(bean.picUrl)
                    else info_pic.setImageResource(R.mipmap.ic_launcher)
                    info_title.text = bean.title
                    info_content.text = bean.content
                    info_memo.visibility = View.GONE
                }
            }
        }
    }
}