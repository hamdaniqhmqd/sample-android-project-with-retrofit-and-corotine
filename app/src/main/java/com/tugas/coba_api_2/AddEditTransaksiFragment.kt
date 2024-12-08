package com.tugas.coba_api_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.tugas.coba_api_2.databinding.FragmentAddEditTransaksiBinding

class AddEditTransaksiFragment : Fragment() {
    private lateinit var transaksiViewModel: TransaksiViewModel
    private var _binding: FragmentAddEditTransaksiBinding? = null
    private val binding get() = _binding!!

    private var transaksiId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transaksiViewModel = ViewModelProvider(this).get(TransaksiViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEditTransaksiBinding.inflate(inflater, container, false)

        // Mendapatkan argumen jika ada untuk edit transaksi
        transaksiId = arguments?.getInt("transaksiId") ?: 0

        if (transaksiId != 0) {
            transaksiId.let {
                // Ambil detail transaksi untuk di-edit
                Toast.makeText(
                    requireContext(),
                    "Detail Transaksi: ${transaksiId}",
                    Toast.LENGTH_SHORT
                ).show()
                transaksiViewModel.getTransaksiById(it)

                transaksiViewModel.transaksiDetail.observe(viewLifecycleOwner) { transaksi ->
                    if (transaksi != null) {
                        binding.etNamaBarang.setText(transaksi.barang_nama)
//                        binding.tvKategoriBarang.text = transaksi.kategori_barang
                        binding.etHargaBarang.setText(transaksi.harga_barang.toString())
                        binding.etJumlahBarang.setText(transaksi.jumlah_barang.toString())
//                        binding.tvTotalHargaBarang.text = transaksi.total_harga_barang.toString()
                        // Handle jika transaksi tidak ditemukan
                        Toast.makeText(requireContext(), "Transaksi ditemukan", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        binding.btnSave.setOnClickListener {
            val namaBarang = binding.etNamaBarang.text.toString()
            val hargaBarang = binding.etHargaBarang.text.toString().toInt()
            val jumlahBarang = binding.etJumlahBarang.text.toString().toInt()

            val transaksi = Transaksi(
                id_transaksi = transaksiId, // Diabaikan, karena dibuat oleh server
                barang_id = 0, // Contoh, gunakan nilai dinamis
                barang_nama = namaBarang,
                kategori_barang = "Pakaian",
                harga_barang = hargaBarang,
                stok_barang = 100, // Contoh, gunakan nilai dinamis
                ukuran_barang = "M",
                jumlah_barang = jumlahBarang,
                total_harga_barang = hargaBarang * jumlahBarang,
                user_id = 1, // Contoh
                usernama = "Ahmad", // Contoh
                supplier_id = 1, // Contoh
                supplier_nama = "Supplier A", // Contoh
                bulan = "2024-11",
                tanggal = "2024-11-01",
                tanggalAkhir = "2024-11-14",
                status = 1,
                statusAkhir = 0,
                created_at = "",
                updated_at = ""
            )

            if (transaksiId == 0) {
                transaksiViewModel.addTransaksi(transaksi)
                Toast.makeText(requireContext(), "Data berhasil disimpan!", Toast.LENGTH_SHORT)
                    .show()

                toFragmentTransaksi()
            } else if (transaksiId > 0) {
                transaksiViewModel.updateTransaksi(transaksi.id_transaksi,transaksi)
                Toast.makeText(requireContext(), "Data berhasil diubah!", Toast.LENGTH_SHORT)
                    .show()
                toFragmentTransaksi()
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun toFragmentTransaksi() {
        val transaksi = TransaksiFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_layout, transaksi)
//            .addToBackStack(null)
            .commit()
    }
}