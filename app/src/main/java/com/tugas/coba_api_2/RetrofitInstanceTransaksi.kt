package com.tugas.coba_api_2

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstanceTransaksi {
    // Menentukan URL dasar untuk API
    private const val BASE_URL = "https://gudang-pakaian-api.infitechd.my.id/"

    // Membuat instance Retrofit secara lazy (hanya dibuat saat dibutuhkan)
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL) // Menetapkan base URL untuk semua endpoint API
            .addConverterFactory(GsonConverterFactory.create()) // Menambahkan converter untuk mengonversi data JSON ke objek Kotlin
            .build() // Membuat instance Retrofit
    }

    // Membuat instance ApiService yang digunakan untuk memanggil API endpoints
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java) // Membuat implementasi dari interface ApiService
    }
}
