package com.example.billz.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import com.example.billz.R
import com.example.billz.databinding.ActivityAddBillBinding
import com.example.billz.model.Bill
import com.example.billz.utils.Constants
import com.example.billz.viewmodel.BillsViewModel
import java.util.Calendar
import java.util.UUID

class AddBillActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddBillBinding
    var selectedMonth = 0
    var selectedDate = 0
    val billsViewModel: BillsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBillBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNav()
    }
    fun setUpNav(){
        setSupportActionBar(binding.tbarAddBill)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowCustomEnabled(true)


    }



    override fun onResume() {
        super.onResume()
        setUpFreqSpinner()
        binding.btnsavebill.setOnClickListener {
            validateBill()
        }
    }

    private fun setUpFreqSpinner() {
        val frequencies = arrayOf(Constants.WEEKLY, Constants.MONTHLY, Constants.ANNUAL)
        val freqAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, frequencies)
        freqAdapter
            .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spFrequency.adapter = freqAdapter

        binding.spFrequency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                when (binding.spFrequency.selectedItem.toString()) {
                    Constants.WEEKLY -> {
                        hideDatePicker()
                        setUpDueDateSpinner(Array(7) { it + 1 })

                    }

                    Constants.MONTHLY -> {
                        hideDatePicker()
                        setUpDueDateSpinner(Array(31) { it + 1 })

                    }

                    Constants.ANNUAL -> {
                        showDatePicker()
                        setUpDpDueDate()
                    }
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

    }

    fun setUpDueDateSpinner(dates: Array<Int>) {
        val dueDateAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dates)
        dueDateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spDueDate.adapter = dueDateAdapter
    }

    fun showDatePicker() {
        binding.dpDueDate.show()
        binding.spDueDate.hide()

    }

    fun hideDatePicker() {
        binding.dpDueDate.hide()
        binding.spDueDate.show()

    }

    fun setUpDpDueDate() {
        val calendar = Calendar.getInstance()
        binding.dpDueDate.init(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ) { view, year, month, date ->

            selectedMonth = month + 1
            selectedDate = date
        }

    }

    fun validateBill() {
        val billName = binding.etName.text.toString()
        val amount = binding.etAmount.text.toString()
        val frequency = binding.spFrequency.selectedItem
        var duedate = Constants.EMPTY_STRING
        if (frequency == Constants.ANNUAL) {
            duedate = "$selectedDate/$selectedMonth"
        } else {
            duedate = binding.spDueDate.selectedItem.toString()
        }

        var error = false
        if (billName.isBlank()) {
            error = true
            binding.etName.error = "Bill name required"
        }
        if (!error) {
            val prefs = getSharedPreferences(Constants.PREFS, MODE_PRIVATE)
            val userId = prefs.getString(Constants.USER_ID, Constants.USER_ID)
            val bill = Bill(
            billId = UUID.randomUUID().toString(),
            name = billName,
            amount = amount.toDouble(),
            userId = userId.toString(),
            frequency = frequency.toString(),
            dueDate = duedate
            )
            billsViewModel.saveBill(bill)
            resetForm()
        }
    }

    fun View.show() {
        this.visibility = View.VISIBLE

    }

    fun View.hide() {
        this.visibility = View.GONE

    }

    fun resetForm(){
        binding.etName.setText(Constants.EMPTY_STRING)
        binding.etAmount.setText(Constants.EMPTY_STRING)
        binding.spFrequency.setSelection(0)
        hideDatePicker()
        binding.spDueDate.setSelection(0)
    }
}

