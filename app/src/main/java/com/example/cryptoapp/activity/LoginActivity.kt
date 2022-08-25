package com.example.cryptoapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityLoginBinding
import com.example.cryptoapp.fragment.HomeScreenFragment
import com.example.cryptoapp.fragment.LoginFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private val binding: ActivityLoginBinding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

//        binding.bottomNavigationView.setOnItemSelectedListener {
//            when(it.itemId) {
//                R.id.homeFragment -> replaceFragment(HomeScreenFragment())
//                //R.id.SearchFragment -> replaceFragment(SearchFragment)
//            }
//            true
//        }

//        if(savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .add(R.id.loginFragment, LoginFragment())
//                .commit()
//        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.loginFragment, fragment)
        fragmentTransaction.commit()
    }
}