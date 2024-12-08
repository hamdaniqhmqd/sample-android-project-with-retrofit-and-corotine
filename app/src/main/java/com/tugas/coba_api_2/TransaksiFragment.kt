package com.tugas.coba_api_2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tugas.coba_api_2.databinding.FragmentTransaksiBinding


class TransaksiFragment : Fragment() {
    private lateinit var transaksiViewModel: TransaksiViewModel
    private lateinit var transaksiAdapter: TransaksiAdapter
    private var _binding: FragmentTransaksiBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transaksiViewModel = ViewModelProvider(this).get(TransaksiViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTransaksiBinding.inflate(inflater, container, false)

        transaksiAdapter = TransaksiAdapter { transaksi ->
            onDetailClick(transaksi)
        }

        binding.recyclerViewTransaksi.adapter = transaksiAdapter
        binding.recyclerViewTransaksi.layoutManager = LinearLayoutManager(requireContext())

        transaksiViewModel.transaksiList.observe(viewLifecycleOwner) { products ->
            transaksiAdapter.submitList(products)
        }

        transaksiViewModel.getTransaksi()

        binding.tambah.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_layout, AddEditTransaksiFragment())
                .addToBackStack(null) // Memungkinkan kembali ke fragment sebelumnya
                .commit()
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onDetailClick(transaksi: Transaksi) {

        val bundle = Bundle().apply {
            putInt("transaksiId", transaksi.id_transaksi ?: 0)
        }
//        Toast.makeText(requireContext(), "Detail Transaksi: ${transaksi.id_transaksi}", Toast.LENGTH_SHORT).show()
        val detailFragment = DetailTransaksiFragment()
        detailFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_layout, detailFragment)
            .addToBackStack(null)
            .commit()
    }
}