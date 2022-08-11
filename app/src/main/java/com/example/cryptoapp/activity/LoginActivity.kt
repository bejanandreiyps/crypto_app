package com.example.cryptoapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityLoginBinding
import com.example.cryptoapp.fragment.LoginFragment

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view_tag, LoginFragment())
            .commit()
    }
}