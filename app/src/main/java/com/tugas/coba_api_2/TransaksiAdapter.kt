package com.tugas.coba_api_2

import com.tugas.coba_api_2.databinding.ItemTransaksiBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

// Adapter untuk menampilkan daftar transaksi dalam RecyclerView
class TransaksiAdapter(
    private val onClick: (Transaksi) -> Unit // Callback untuk menangani klik item
) : ListAdapter<Transaksi, TransaksiAdapter.TransaksiViewHolder>(TransaksiDiffCallback()) {

    // ViewHolder untuk mengikat item layout dan data transaksi
    class TransaksiViewHolder(val binding: ItemTransaksiBinding) :
        RecyclerView.ViewHolder(binding.root)

    // Menyediakan ViewHolder dan mengikat layout ItemTransaksi
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransaksiViewHolder {
        val binding = ItemTransaksiBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TransaksiViewHolder(binding)
    }

    // Mengikat data transaksi ke item layout untuk ditampilkan di RecyclerView
    override fun onBindViewHolder(holder: TransaksiViewHolder, position: Int) {
        val data = getItem(position) // Mengambil item transaksi berdasarkan posisi
        holder.binding.apply {
            // Mengisi nilai data ke dalam tampilan
            namaBarang.text = data.barang_nama
            jumlahBarang.text = data.jumlah_barang.toString()
            tatolHargaBarang.text = data.total_harga_barang.toString()

            // Menambahkan listener klik pada root item
            root.setOnClickListener {
                onClick(data) // Menjalankan callback ketika item diklik
            }
        }
    }

    // DiffUtil untuk membandingkan item transaksi dan menentukan perubahan
    class TransaksiDiffCallback : DiffUtil.ItemCallback<Transaksi>() {
        // Menentukan apakah dua item transaksi adalah item yang sama (berdasarkan id)
        override fun areItemsTheSame(oldItem: Transaksi, newItem: Transaksi): Boolean {
            return oldItem.id_transaksi == newItem.id_transaksi
        }

        // Menentukan apakah dua item transaksi memiliki konten yang sama
        override fun areContentsTheSame(oldItem: Transaksi, newItem: Transaksi): Boolean {
            return oldItem == newItem
        }
    }
}
