package com.tugas.coba_api_2

import com.google.gson.annotations.SerializedName

data class Transaksi(
    @SerializedName("id_transaksi") val id_transaksi: Int,
    @SerializedName("barang_id") val barang_id: Int,
    @SerializedName("barang_nama") val barang_nama: String,
    @SerializedName("kategori_barang") val kategori_barang: String,
    @SerializedName("harga_barang") val harga_barang: Int,
    @SerializedName("stok_barang") val stok_barang: Int,
    @SerializedName("ukuran_barang") val ukuran_barang: String,
    @SerializedName("jumlah_barang") val jumlah_barang: Int,
    @SerializedName("total_harga_barang") val total_harga_barang: Int,
    @SerializedName("user_id") val user_id: Int,
    @SerializedName("usernama") val usernama: String,
    @SerializedName("supplier_id") val supplier_id: Int,
    @SerializedName("supplier_nama") val supplier_nama: String,
    @SerializedName("bulan") val bulan: String,
    @SerializedName("tanggal") val tanggal: String,
    @SerializedName("tanggalAkhir") val tanggalAkhir: String,
    @SerializedName("status") val status: Int,
    @SerializedName("statusAkhir") val statusAkhir: Int,
    @SerializedName("created_at") val created_at: String,
    @SerializedName("updated_at") val updated_at: String
)

