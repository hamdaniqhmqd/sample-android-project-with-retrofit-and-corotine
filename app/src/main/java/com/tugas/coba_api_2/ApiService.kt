package com.tugas.coba_api_2

import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    // Endpoint untuk list transaksi
    @GET("/api/transaksi")
    suspend fun getTransaksi(): TransaksiResponse

    // Endpoint untuk detail transaksi
    @GET("/api/transaksi/{id}")
    suspend fun getTransaksiById(@Path("id") id: Int): TransaksiResponseDetail

    // Endpoint untuk tambah transaksi
    @POST("/api/transaksi")
    suspend fun addTransaksi(@Body transaksi: Transaksi): TransaksiResponseDetail

    // Endpoint untuk ubah transaksi
    @PUT("/api/transaksi/{id}")
    suspend fun updateTransaksi(
        @Path("id") id: Int, @Body transaksi: Transaksi
    ): TransaksiResponseDetail

    // Endpoint untuk hapus transaksi
    @DELETE("/api/transaksi/{id}")
    suspend fun deleteTransaksi(@Path("id") id: Int): TransaksiResponseDetail
}

