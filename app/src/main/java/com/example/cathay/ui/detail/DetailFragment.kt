package com.example.cathay.ui.detail

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.cathay.*
import com.example.cathay.base.BaseFragment
import com.example.cathay.data.model.RegionInfo
import com.example.cathay.ui.ItemAdapter
import com.example.cathay.ui.MainActivity
import com.example.cathay.ui.plant.PlantFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment(), OnItemClickListener {

    private val mRegionInfo by getBundleValue(PASS_REGION, RegionInfo())

    private val viewModel by viewModel<DetailViewModel>()

    private val itemAdapter by lazy { ItemAdapter() }

    override val layoutId = R.layout.fragment_detail

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        initToolbar(toolbar, true)
        toolbar.title = mRegionInfo.regionName
        detail_pic.load(mRegionInfo.regionPicUrl)
        detail_content.text = mRegionInfo.regionInfo
        website.setOnClickListener { activity?.openBrowser(mRegionInfo.regionWebUrl) }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
            itemAdapter.setOnItemClickListener(this@DetailFragment)
        }
    }

    private fun initData() {
        viewModel.apply {
            itemEntityListLiveData.observe(viewLifecycleOwner, {
                itemAdapter.apply {
                    setNewInstance(it.toMutableList())
                    loadMoreModule.loadMoreEnd(true)
                }
            })
            regionInfoLiveData.value = mRegionInfo
            getPlantList()
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        viewModel.infoBeanListLiveData.value?.apply {
            (activity as MainActivity).addFragment(PlantFragment().withBundleValue {
                putSerializable(PASS_INFO, this@apply[position])
            })
        }
    }
}