package com.example.cathay.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.cathay.base.BaseViewModel
import com.example.cathay.data.bean.ItemBean
import com.example.cathay.data.entity.ItemEntity
import com.example.cathay.data.entity.ItemEntity.Companion.ITEM_HOME
import com.example.cathay.data.model.RegionInfo
import com.example.cathay.data.respository.RegionDataRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeViewModel(
    private val regionDataRepository: RegionDataRepository
) : BaseViewModel() {

    val regionInfoListData = MutableLiveData<List<RegionInfo>>()
    val entityListLiveData = Transformations.map(regionInfoListData) {
        it.map { info ->
            val bean = ItemBean(
                picUrl = info.regionPicUrl,
                title = info.regionName,
                content = info.regionInfo,
                memo = info.regionMemo
            )
            ItemEntity(ITEM_HOME, bean = bean)
        }
    }

    fun getRegionList() {
        viewModelLaunch(
            action = {
                withContext(Dispatchers.IO) {
                    if (regionDataRepository.loadRegionList().isNullOrEmpty()) {
                        val list = mutableListOf<RegionInfo>().apply {
                            add(
                                RegionInfo(
                                    regionName = "臺灣動物區",
                                    regionPicUrl = "http://www.zoo.gov.tw/iTAP/05_Exhibit/01_FormosanAnimal.jpg",
                                    regionInfo = "臺灣動物區以臺灣原生動物與棲息環境為展示重點，佈置模擬動物原生棲地之生態環境，讓動物表現如野外般自然的生活習性，引導觀賞者更正確地認識本土野生動物。臺灣位處於亞熱帶，雨量充沛、氣候溫暖，擁有各種地形景觀，因而孕育了豐富龐雜的生物資源。",
                                    regionMemo = "",
                                    regionWebUrl = "http://www.zoo.gov.tw/introduce/gq.aspx?tid=12"
                                )
                            )
                            add(
                                RegionInfo(
                                    regionName = "熱帶雨林區",
                                    regionPicUrl = "http://www.zoo.gov.tw/iTAP/05_Exhibit/03_TropicalRainforest.jpg",
                                    regionInfo = "熱帶雨林區模擬東南亞熱帶雨林的自然生態景觀，依展示動線規劃成河口生態、密林生態及林緣生態三大展示區，區內可見板根、氣生根、支柱根、附生植物、林間霧氣等熱帶雨林特有的生態現象。",
                                    regionMemo = "",
                                    regionWebUrl = "http://www.zoo.gov.tw/introduce/gq.aspx?tid=14"
                                )
                            )
                            add(
                                RegionInfo(
                                    regionName = "教育中心",
                                    regionPicUrl = "http://www.zoo.gov.tw/iTAP/05_Exhibit/09_EducationCenter.jpg",
                                    regionInfo = "教育中心包括博物館展示區、圖書館、演講廳、動物藝坊及動物學堂等，為本園展示動物園文化的櫥窗。館內以動物標本、生態全景展示傳達動物知識及保育觀念，最特別的是還有亞洲象「林旺」的標本展示區，以及恐龍模型展示喔！",
                                    regionMemo = "每週一休館，入館門票：全票20元、優待票10元",
                                    regionWebUrl = "http://www.zoo.gov.tw/introduce/gq.aspx?tid=22"
                                )
                            )
                            add(
                                RegionInfo(
                                    regionName = "企鵝館",
                                    regionPicUrl = "http://www.zoo.gov.tw/iTAP/05_Exhibit/10_PenguinHouse.jpg",
                                    regionInfo = "企鵝館是「國王企鵝」及「黑腳企鵝」的家，牠們是不會飛的水生鳥類，牠們全是游泳的專家，在水裡潛泳可以「飛」得又快又好。在觀賞可愛的企鵝之餘，也可以在企鵝館了解牠們的分布、形態特徵、生活習性及繁殖求偶行為喔！",
                                    regionMemo = "每月第二個週一休館",
                                    regionWebUrl = "http://www.zoo.gov.tw/introduce/gq.aspx?tid=9"
                                )
                            )
                            add(
                                RegionInfo(
                                    regionName = "兩棲爬蟲動物館",
                                    regionPicUrl = "http://www.zoo.gov.tw/iTAP/05_Exhibit/12_AmphibianReptile.jpg",
                                    regionInfo = "本館以不同的生態系展示各種兩棲爬蟲活體動物，包括溼地、熱帶雨林、溫帶森林、沙漠等四大類型。除了經常性的動物生態展示區之外，館內還有靜態的解說教育展示區及定期更換主題的特展區，希望藉由各類動物、寫實模型、互動教材與文化藝品的多元展示，引導遊客進入兩棲爬蟲動物的奧秘世界。",
                                    regionMemo = "每月第三個週一休館",
                                    regionWebUrl = "http://www.zoo.gov.tw/introduce/gq.aspx?tid=10"
                                )
                            )
                            add(
                                RegionInfo(
                                    regionName = "昆蟲館",
                                    regionPicUrl = "http://www.zoo.gov.tw/iTAP/05_Exhibit/13_Insectarium.jpg",
                                    regionInfo = "教育中心包括博物館展示區、圖書館、演講廳、動物藝坊及動物學堂等，為本園展示動物園文化的櫥窗。館內以動物標本、生態全景展示傳達動物知識及保育觀念，最特別的是還有亞洲象「林旺」的標本展示區，以及恐龍模型展示喔！",
                                    regionMemo = "每月第四個週一休館",
                                    regionWebUrl = "http://www.zoo.gov.tw/introduce/gq.aspx?tid=5"
                                )
                            )
                            add(
                                RegionInfo(
                                    regionName = "新光特展館（大貓熊館）",
                                    regionPicUrl = "http://www.zoo.gov.tw/iTAP/05_Exhibit/14_PandaHouse.jpg",
                                    regionInfo = "新光特展館（大貓熊館）包括一個戶外場地和兩個室內場地，提供多樣的活動空間及攀爬、遮蔭設施，還有大小石塊及流瀑水。戶外場地則模擬大貓熊野外棲息地，草坪寬闊，並以濃綠喬木構成背景。",
                                    regionMemo = "每月第一個週一休館",
                                    regionWebUrl = "http://www.zoo.gov.tw/introduce/gq.aspx?tid=8"
                                )
                            )
                            add(
                                RegionInfo(
                                    regionName = "酷Cool節能屋",
                                    regionPicUrl = "http://www.zoo.gov.tw/iTAP/05_Exhibit/16_EcoHouse.jpg",
                                    regionInfo = "經濟部能源局及工業技術研究院，結合臺北市立動物園的環境教育及生態保育理念，建造這一棟位於沙漠動物區入口處的酷Cool節能屋。這是一座結合空間設計、節能材料及再生能源利用的節能建築，導入童話故事的解說活動，適合全家人一起來體驗。",
                                    regionMemo = "每週一休館",
                                    regionWebUrl = "http://www.zoo.gov.tw/introduce/gq.aspx?tid=21"
                                )
                            )
                            add(
                                RegionInfo(
                                    regionName = "生命驛站",
                                    regionPicUrl = "http://www.zoo.gov.tw/iTAP/05_Exhibit/18_TheStage.jpg",
                                    regionInfo = "為了讓更多的市民貼近、瞭解大自然，同時寓教於樂，兒童動物區內的特展區「生命驛站」，希望透過農田環境、動物標本和農具展示，以及自導式互動遊戲，呈現大自然萬物的生命循環歷程，讓參觀者感受生命與環境間的重要關聯。",
                                    regionMemo = "每週一休館",
                                    regionWebUrl = "https://www.zoo.gov.taipei/cp.aspx?n=274AFEA1B85B9D7B"
                                )
                            )
                        }
                        regionDataRepository.updateAllRegion(list)
                    }
                    regionInfoListData.postValue(regionDataRepository.loadRegionList())
                }
            }
        )

    }
}