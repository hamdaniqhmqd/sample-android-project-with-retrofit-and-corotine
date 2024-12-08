package com.tugas.coba_api_2

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

class TransaksiRepository {
    private val apiService: ApiService = RetrofitInstanceTransaksi.apiService

    // Fungsi untuk mengambil daftar transaksi
    suspend fun getAllTransaksi(): List<Transaksi> {
        // Menggunakan coroutine di thread IO untuk operasi jaringan
        return withContext(Dispatchers.IO) {
            try {
                // Memanggil API untuk mendapatkan semua transaksi
                val response = apiService.getTransaksi()

                // Memeriksa apakah response berhasil dan data tersedia
                if (response.success) {
                    // Mengembalikan data transaksi yang diterima dari API
                    return@withContext response.data

                } else {
                    // Menangani error jika API gagal
                    throw Exception("Failed to fetch transaksi list: ${response.message}")
                }
            } catch (e: Exception) {
                // Menangani error jaringan atau masalah lainnya
                throw Exception("Network error: ${e.message}")
            }
        }
    }

    // Fungsi untuk mengambil detail transaksi berdasarkan ID
    suspend fun getTransaksById(id: Int): Transaksi {
        // Menggunakan coroutine di thread IO untuk operasi jaringan
        return withContext(Dispatchers.IO) {
            try {
                // Memanggil API untuk mendapatkan transaksi berdasarkan ID
                val response = apiService.getTransaksiById(id)

                if (response.success) {
                    // Mengembalikan detail transaksi yang diterima
                    return@withContext response.data
                } else {
                    // Menangani error jika gagal mendapatkan detail
                    throw Exception("Failed to fetch transaksi detail: ${response.message}")
                }
            } catch (e: Exception) {
                // Menangani error jaringan
                throw Exception("Network error: ${e.message}")
            }
        }
    }

    // Fungsi untuk menambah transaksi
    suspend fun addTransaksi(transaksi: Transaksi): Transaksi {
        // Menggunakan coroutine di thread IO untuk operasi jaringan
        return withContext(Dispatchers.IO) {
            try {
                // Memanggil API untuk menambahkan transaksi baru
                val response = apiService.addTransaksi(transaksi)
                if (response.success) {
                    // Mengembalikan transaksi yang baru ditambahkan
                    return@withContext response.data
                } else {
                    // Menangani error jika gagal menambah transaksi
                    throw Exception("Failed to add transaksi: ${response.message}")
                }
            } catch (e: Exception) {
                // Menangani error jaringan
                throw Exception("Network error: ${e.message}")
            }
        }
    }

    // Fungsi untuk memperbarui transaksi berdasarkan ID
    suspend fun updateTransaksi(id: Int, transaksi: Transaksi): Transaksi {
        // Menggunakan coroutine di thread IO untuk operasi jaringan
        return withContext(Dispatchers.IO) {
            try {
                // Memanggil API untuk memperbarui transaksi berdasarkan ID
                val response = apiService.updateTransaksi(id, transaksi)

                // Memeriksa apakah response berhasil
                if (response.success) {
                    // Mengembalikan transaksi yang sudah diperbarui
                    return@withContext response.data
                } else {
                    // Menangani error jika gagal memperbarui transaksi
                    throw Exception("Gagal memperbarui transaksi: ${response.message}")
                }
            } catch (e: Exception) {
                // Menangani kesalahan lainnya
                throw Exception("Kesalahan: ${e.message}")
            }
        }
    }


    // Fungsi untuk menghapus transaksi berdasarkan ID
    suspend fun deleteTransaksi(idTransaksi: Int) {
        // Menggunakan coroutine di thread IO untuk operasi jaringan
        return withContext(Dispatchers.IO) {
            try {
                // Memanggil API untuk menghapus transaksi berdasarkan ID
                val response = apiService.deleteTransaksi(idTransaksi)
                if (!response.success) {
                    // Menangani error jika gagal menghapus transaksi
                    throw Exception("Failed to delete transaksi: ${response.message}")
                }
            } catch (e: Exception) {
                // Menangani error jaringan
                throw Exception("Network error: ${e.message}")
            }
        }
    }
}
