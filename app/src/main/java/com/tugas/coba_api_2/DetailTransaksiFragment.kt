package com.tugas.coba_api_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.tugas.coba_api_2.databinding.FragmentDetailTransaksiBinding

class DetailTransaksiFragment : Fragment() {
    private var _binding: FragmentDetailTransaksiBinding? = null
    private val binding get() = _binding!!

    private lateinit var transaksiViewModel: TransaksiViewModel
    private var transaksiId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transaksiViewModel = ViewModelProvider(this).get(TransaksiViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailTransaksiBinding.inflate(inflater, container, false)

        // Mendapatkan argumen jika ada untuk edit transaksi
        transaksiId = arguments?.getInt("transaksiId") ?: 0

        if (transaksiId != 0) {
            transaksiId.let {
                // Ambil detail transaksi untuk di-edit
                Toast.makeText(requireContext(), "Detail Transaksi: ${transaksiId}", Toast.LENGTH_SHORT).show()
                transaksiViewModel.getTransaksiById(it)

                transaksiViewModel.transaksiDetail.observe(viewLifecycleOwner) { transaksi ->
                    if (transaksi != null) {
                        binding.tvBarangNama.text = transaksi.barang_nama
                        binding.tvKategoriBarang.text = transaksi.kategori_barang
                        binding.tvHargaBarang.text = transaksi.harga_barang.toString()
                        binding.tvJumlahBarang.text = transaksi.jumlah_barang.toString()
                        binding.tvTotalHargaBarang.text = transaksi.total_harga_barang.toString()
                        // Handle jika transaksi tidak ditemukan
                        Toast.makeText(requireContext(), "Transaksi ditemukan", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }



        binding.update.setOnClickListener {
            onUpdateClick(transaksiId)
        }

        binding.delete.setOnClickListener {
            transaksiViewModel.deleteTransaksi(transaksiId)
            transaksiViewModel.statusMessage.observe(viewLifecycleOwner) { message ->
                Toast.makeText(requireContext(), "Data ${transaksiId} berhasil dihapus!", Toast.LENGTH_SHORT).show()
            }

            toFragmentTransaksi()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onUpdateClick(id_transaksi: Int) {

        val bundle = Bundle().apply {
            putInt("transaksiId", id_transaksi ?: 0)
        }
        val updateFragment = AddEditTransaksiFragment()
        updateFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_layout, updateFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun toFragmentTransaksi() {
        val transaksi = TransaksiFragment()
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_layout, transaksi)
//            .addToBackStack(null)
            .commit()
    }
}