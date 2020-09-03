package com.example.cathay.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.cathay.base.BaseViewModel
import com.example.cathay.data.bean.InfoBean
import com.example.cathay.data.bean.ItemBean
import com.example.cathay.data.entity.ItemEntity
import com.example.cathay.data.model.RegionInfo
import com.example.cathay.data.respository.PlantApiRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailViewModel(
    private val plantApiRepository: PlantApiRepository
) : BaseViewModel() {

    val regionInfoLiveData = MutableLiveData<RegionInfo>()
    val itemEntityListLiveData = MutableLiveData<List<ItemEntity>>()
    val infoBeanListLiveData = MutableLiveData<List<InfoBean>>()

    fun getPlantList() {
        viewModelLaunch(
            action = {
                withContext(Dispatchers.IO) {
                    val filterResponse = plantApiRepository.getPlantList().result.results
                        .distinctBy { it["F_Name_Latin"] }
                        .filter { map ->
                            map["F_Location"]?.contains(regionInfoLiveData.value!!.regionName)
                                ?: map.isNotEmpty()
                        }
                    val itemList = filterResponse.map { map ->
                        ItemEntity(
                            ItemEntity.ITEM_DETAIL,
                            ItemBean(
                                picUrl = map["F_Pic01_URL"] ?: "",
                                title = map["\uFEFFF_Name_Ch"] ?: "",
                                content = map["F_AlsoKnown"] ?: ""
                            )
                        )
                    }
                    itemEntityListLiveData.postValue(itemList)
                    val infoBeanList = filterResponse.map { map ->
                        InfoBean(
                            picUrl = map["F_Pic01_URL"] ?: "",
                            chName = map["\uFEFFF_Name_Ch"] ?: "",
                            engName = map["F_Name_En"] ?: "",
                            aliasName = map["F_AlsoKnown"] ?: "",
                            intro = map["F_Brief"] ?: "",
                            feature = map["F_Feature"] ?: "",
                            function = map["F_Function＆Application"] ?: "",
                            lastUpdate = map["F_Update"] ?: ""
                        )
                    }
                    infoBeanListLiveData.postValue(infoBeanList)
                }
            },
            errorAction = {
                Log.e(TAG, " ${it.message}")
            }
        )
    }
}