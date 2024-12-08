package com.tugas.coba_api_2

import com.google.gson.annotations.SerializedName
import retrofit2.Response

data class TransaksiResponse(
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: List<Transaksi>
)
