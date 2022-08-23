package com.example.cryptoapp.fragment
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.example.cryptoapp.view_model.LoginViewModel
import com.example.cryptoapp.R
import com.example.cryptoapp.database.DatabaseProvider
import com.example.cryptoapp.databinding.FragmentLoginBinding
import com.example.cryptoapp.view_model.LoginState

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.loginViewModel = viewModel
        binding.loginButton.setOnClickListener {
            viewModel.login()
        }
        viewModel.state.observe(viewLifecycleOwner) { state ->
            loginStateObserver(state)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    @SuppressLint("SetTextI18n")
    private fun loginStateObserver(state: LoginState) {
        when (state) {
            is LoginState.Error -> {
                binding.loginButton.text = "Login"
                binding.loginButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.secondaryColor))

                Toast.makeText(
                    requireContext(),
                    state.message,
                    Toast.LENGTH_LONG
                ).show()
            }
            LoginState.InProgress -> {
                binding.loginButton.text = "In progress"
                binding.loginButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.secondaryTextColor))
            }
            LoginState.Success -> {
                binding.loginButton.text = "Login"
                binding.loginButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.secondaryColor))

                binding.loginButton.setOnClickListener {
                    viewModel.login()
                    findNavController().navigate(
                        R.id.homeAction,
                        null,
                        navOptions { popUpTo(R.id.loginFragment) { inclusive = true } })
                }
            }
        }
    }
}