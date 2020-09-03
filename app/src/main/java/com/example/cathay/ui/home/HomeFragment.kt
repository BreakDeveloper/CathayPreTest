package com.example.cathay.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.listener.OnItemClickListener
import com.example.cathay.PASS_REGION
import com.example.cathay.R
import com.example.cathay.addFragment
import com.example.cathay.base.BaseFragment
import com.example.cathay.ui.ItemAdapter
import com.example.cathay.ui.MainActivity
import com.example.cathay.ui.detail.DetailFragment
import com.example.cathay.withBundleValue
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.view_toolbar.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment(), OnItemClickListener {

    private val itemAdapter by lazy { ItemAdapter() }
    private val viewModel by viewModel<HomeViewModel>()

    override val layoutId = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
    }

    private fun initView() {
        toolbar.title = "台北市立動物園"
        initToolbar(toolbar, false)

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = itemAdapter
            itemAdapter.setOnItemClickListener(this@HomeFragment)
        }
    }

    private fun initData() {
        viewModel.apply {
            entityListLiveData.observe(viewLifecycleOwner) {
                itemAdapter.apply {
                    setNewInstance(it.toMutableList())
                    loadMoreModule.loadMoreEnd(true)
                }
            }
            getRegionList()
        }
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val info = viewModel.regionInfoListData.value!![position]

        (activity as MainActivity).addFragment(DetailFragment().withBundleValue {
            putSerializable(PASS_REGION, info)
        })
    }
}