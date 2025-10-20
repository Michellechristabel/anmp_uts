package com.moonnieyy.anmpproject.model

import com.google.gson.annotations.SerializedName

data class Profil(
    var id:String?,
    @SerializedName("weight")
    var weight:Int?,
    @SerializedName("height")
    var height:Int?,
    @SerializedName("age")
    var age:Int?,
)