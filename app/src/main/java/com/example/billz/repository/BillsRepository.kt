package com.example.billz.repository

import androidx.lifecycle.LiveData
import com.example.billz.viewmodel.BillzApp
import com.example.billz.database.BillsDb
import com.example.billz.model.Bill
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillsRepository {
    var db = BillsDb.getDatabase(BillzApp.appContaext)
    val billsDao = db.billsDao()


    suspend fun saveBill(bill: Bill) {
        withContext(Dispatchers.IO) {
            billsDao.insertBill(bill)
        }
    }

    fun getAllBills(): LiveData<List<Bill>> {
        return billsDao.getAllBills()
    }


}