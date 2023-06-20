package com.example.billz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.billz.databinding.ActivityLoginBinding
import com.example.billz.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRegisterthree.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogIn.setOnClickListener {
            val intent = Intent(this,LoginActivity::class.java)
            startActivity(intent)
        }
        onResume()
    }

    override fun onResume() {
        super.onResume()
        validateLogin()
    }

    fun validateLogin(){
    val username = binding.tieUsernametwo.text.toString()
    val password = binding.tiePasswordtwo.text.toString()
        var error = false

        if (username.isEmpty()) {
            binding.tieUsernametwo.error = "Username is required"
            error = true

        }

        if (password.isEmpty()) {
            binding.tiePasswordtwo.error = "Password is required"
            error = true

        }


        Toast.makeText(this, "Logged in as $username", Toast.LENGTH_SHORT).show()
    }
}












