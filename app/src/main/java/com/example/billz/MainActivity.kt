package com.example.billz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.billz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        binding.btnRegister.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        onResume()

    }

    override fun onResume() {
        super.onResume()
       validate()
    }

    fun validate(){
        val username = binding.tielUsername.text.toString()
        val phoneNumber = binding.tiePhoneNumber.text.toString()
        val email = binding.tieEmail.text.toString()
        val password = binding.tiePassword.text.toString()
        var error = false


        if (username.isEmpty()) {
            binding.tielUsername.error = "Username is required"
            error = true

        }

        if (phoneNumber.isEmpty()) {
            binding.tiePhoneNumber.error = "Phone number is required"
            error = true

        }

        if (email.isEmpty()) {
            binding.tieEmail.error = "Email is required"
            error = true

        }


        if (password.isEmpty()) {
            binding.tiePassword.error = "Password is required"
            error = true

        }


    }
}



