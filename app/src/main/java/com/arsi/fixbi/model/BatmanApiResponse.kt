package com.arsi.fixbi.model

import com.google.gson.annotations.SerializedName

data class BatmanApiResponse(
    @SerializedName("batman") val batmanImages: ArrayList<BatmanModel>
)