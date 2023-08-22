package com.example.billz.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.billz.R
import com.example.billz.databinding.ActivityHome2Binding
import com.example.billz.utils.Constants
import java.net.Inet4Address

class HomeActivity2 : AppCompatActivity() {
    lateinit var binding: ActivityHome2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedprefs = getSharedPreferences(Constants.PREFS, Context.MODE_PRIVATE)
        val logoutBtn = findViewById<Button>(R.id.btnLogOut)
        logoutBtn.setOnClickListener{
            val editor = sharedprefs.edit()
            editor.clear()
            editor.apply()
            val loginActivity = Intent(this@HomeActivity2, LoginActivity::class.java)
            startActivity(loginActivity)
            finish()
        }
    }
}