package com.example.cathay.data.bean

import java.io.Serializable

data class InfoBean(
    var picUrl: String = "",
    var chName: String = "",
    var engName: String = "",
    var aliasName: String = "",
    var intro: String = "",
    var feature: String = "",
    var function: String = "",
    var lastUpdate: String = ""
) : Serializable