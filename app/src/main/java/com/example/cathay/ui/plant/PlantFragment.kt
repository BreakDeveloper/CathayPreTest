package com.example.cathay.ui.plant

import android.os.Bundle
import android.view.View
import com.example.cathay.PASS_INFO
import com.example.cathay.R
import com.example.cathay.base.BaseFragment
import com.example.cathay.data.bean.InfoBean
import com.example.cathay.getBundleValue
import com.example.cathay.load
import kotlinx.android.synthetic.main.fragment_plant.*
import kotlinx.android.synthetic.main.view_toolbar.*

class PlantFragment : BaseFragment() {
    private val mInfoBean by getBundleValue(PASS_INFO, InfoBean())

    override val layoutId: Int = R.layout.fragment_plant

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initToolbar(toolbar, true)
        toolbar.title = mInfoBean.chName
        mInfoBean.apply {
            if (picUrl.isNotEmpty()) detail_pic.load(picUrl)
            name_ch.text = chName
            name_en.text = engName
            alias_content.text = aliasName
            intro_content.text = intro
            feature_content.text = feature
            function_content.text = function
            last_update.text = "最後更新：$lastUpdate"
        }
    }
}