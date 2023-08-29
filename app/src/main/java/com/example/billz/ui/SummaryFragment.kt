package com.example.billz.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.billz.R
import com.example.billz.databinding.FragmentSummaryBinding
import com.example.billz.viewmodel.BillsViewModel


class SummaryFragment : Fragment() {
    private var binding: FragmentSummaryBinding? = null
    //    private val binding get() = binding!!
    private lateinit var billsViewModel: BillsViewModel
    private lateinit var adapter:SaveBillRvAdapter// Initialize your BillAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        billsViewModel = ViewModelProvider(requireActivity())[BillsViewModel::class.java]
        adapter = SaveBillRvAdapter(requireContext(), R.layout.bill_item, mutableListOf())
        // Set the adapter to your ListView or RecyclerView
        binding?.listViewBills?.adapter = adapter
        // Observe the list of bills from the ViewModel and update the adapter
        billsViewModel.getAllBills().observe(viewLifecycleOwner) { bills ->
            adapter.clear()
            adapter.addAll(bills)
            adapter.notifyDataSetChanged()
        }

        binding?.fabAddBill?.setOnClickListener {
            startActivity(Intent(requireContext(),AddBillActivity::class.java))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSummaryBinding.inflate(layoutInflater,container,false)
        return  binding?.root

    }
    override fun onResume() {
        super.onResume()
        binding?.fabAddBill?.setOnClickListener {
            startActivity(Intent(requireContext(),AddBillActivity::class.java))
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding=null
    }
}