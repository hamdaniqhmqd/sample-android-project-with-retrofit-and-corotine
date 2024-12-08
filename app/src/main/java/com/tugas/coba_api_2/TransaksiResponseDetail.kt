package com.tugas.coba_api_2

import com.google.gson.annotations.SerializedName

class TransaksiResponseDetail (
    @SerializedName("success") val success: Boolean,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: Transaksi
)