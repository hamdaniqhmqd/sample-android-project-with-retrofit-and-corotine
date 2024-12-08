package com.tugas.coba_api_2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TransaksiViewModel(application: Application) : AndroidViewModel(application) {
    // Membuat instance repository untuk berinteraksi dengan data transaksi
    private val repository = TransaksiRepository()

    // LiveData untuk menyimpan daftar transaksi dan memberikan pembaruan ke UI
    private val _transaksiList = MutableLiveData<List<Transaksi>>()
    val transaksiList: LiveData<List<Transaksi>> get() = _transaksiList

    // LiveData untuk menyimpan detail transaksi dan memberikan pembaruan ke UI
    private val _transaksiDetail = MutableLiveData<Transaksi>()
    val transaksiDetail: LiveData<Transaksi> get() = _transaksiDetail

    // LiveData untuk menyimpan status pesan, seperti error atau informasi lainnya
    private val _statusMessage = MutableLiveData<String>()
    val statusMessage: LiveData<String> get() = _statusMessage

    // Fungsi untuk mendapatkan daftar transaksi
    fun getTransaksi() {
        viewModelScope.launch { // Melakukan eksekusi di dalam coroutine
            try {
                // Memanggil repository untuk mengambil data transaksi
                val transaksiList = repository.getAllTransaksi()
                _transaksiList.postValue(transaksiList) // Memperbarui LiveData dengan data yang diterima
            } catch (e: Exception) {
                e.printStackTrace() // Menangani error dan menampilkan stack trace
                // Mengirim pesan kesalahan ke UI
                _statusMessage.postValue("Kesalahan saat memuat data transaksi: ${e.message ?: "Unknown error"}")
            }
        }
    }

    // Fungsi untuk mendapatkan detail transaksi berdasarkan ID
    fun getTransaksiById(id: Int) {
        viewModelScope.launch {
            try {
                // Memanggil repository untuk mengambil detail transaksi berdasarkan ID
                val transaksiDetail = repository.getTransaksById(id)
                _transaksiDetail.postValue(transaksiDetail) // Memperbarui LiveData dengan detail transaksi
            } catch (e: Exception) {
                e.printStackTrace()
                // Mengirim pesan kesalahan ke UI jika terjadi error
                _statusMessage.postValue("Kesalahan saat memuat detail transaksi: ${e.message ?: "Unknown error"}")
            }
        }
    }

    // Fungsi untuk menambahkan transaksi baru
    fun addTransaksi(transaksi: Transaksi) {
        viewModelScope.launch {
            try {
                // Memanggil repository untuk menambahkan transaksi
                val addedTransaksi = repository.addTransaksi(transaksi)
                // Mengirimkan pesan sukses dengan ID transaksi yang baru ditambahkan
                _statusMessage.postValue("Transaksi berhasil ditambahkan: ${addedTransaksi.id_transaksi}")
                getTransaksi() // Memuat ulang daftar transaksi setelah penambahan
            } catch (e: Exception) {
                e.printStackTrace()
                // Mengirim pesan kesalahan ke UI jika terjadi error
                _statusMessage.postValue("Kesalahan saat menambahkan transaksi: ${e.message ?: "Unknown error"}")
            }
        }
    }

    // Fungsi untuk mengubah transaksi berdasarkan ID
    fun updateTransaksi(id: Int, transaksi: Transaksi) {
        viewModelScope.launch {
            try {
                // Memanggil repository untuk memperbarui transaksi
                val updatedTransaksi = repository.updateTransaksi(id, transaksi)
                // Mengirimkan pesan sukses setelah perubahan
                _statusMessage.postValue("Transaksi berhasil diubah: ${updatedTransaksi.id_transaksi}")
                getTransaksi() // Memuat ulang daftar transaksi setelah perubahan
            } catch (e: Exception) {
                e.printStackTrace()
                // Mengirim pesan kesalahan ke UI jika terjadi error
                _statusMessage.postValue("Kesalahan saat mengubah transaksi: ${e.message ?: "Unknown error"}")
            }
        }
    }

    // Fungsi untuk menghapus transaksi berdasarkan ID
    fun deleteTransaksi(idTransaksi: Int) {
        viewModelScope.launch {
            try {
                // Memanggil repository untuk menghapus transaksi
                repository.deleteTransaksi(idTransaksi)
                // Mengirimkan pesan sukses setelah penghapusan
                _statusMessage.postValue("Transaksi berhasil dihapus")
                getTransaksi() // Memuat ulang daftar transaksi setelah penghapusan
            } catch (e: Exception) {
                e.printStackTrace()
                // Mengirim pesan kesalahan ke UI jika terjadi error
                _statusMessage.postValue("Kesalahan saat menghapus transaksi: ${e.message ?: "Unknown error"}")
            }
        }
    }
}
