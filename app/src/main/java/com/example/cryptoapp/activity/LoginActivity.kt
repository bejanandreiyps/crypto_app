package com.example.cryptoapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityLoginBinding
import com.example.cryptoapp.fragment.HomeScreenFragment
import com.example.cryptoapp.fragment.LoginFragment
import com.example.cryptoapp.fragment.SearchFragment

class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.HomeFragment -> replaceFragment(HomeScreenFragment)
                R.id.SearchFragment -> replaceFragment(SearchFragment)
            }
            true
        }

        if(savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_login, LoginFragment())
                .commit()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_login, fragment)
        fragmentTransaction.commit()
    }
}