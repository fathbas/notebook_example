package com.example.notebook_example

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.notebook_example.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private val loginViewModel: LoginFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        loginBinding.apply {
            this.loginFragmentViewModelObserver = loginViewModel.getObserver()
            this.newUserLogin.setOnClickListener {
                it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            this.loginButton.setOnClickListener {
                loginViewModel.loginOnclick()?.observe(viewLifecycleOwner) {
                    Log.d("userData", it.toString())
                    if (it.email == "") {
                        Toast.makeText(
                            requireContext(),
                            "Invalid email or password.Please try again!",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(requireContext(), "Login is successful", Toast.LENGTH_SHORT)
                            .show()
                        Log.d("userData", it.toString())
                    }
                }
            }
        }

        // Inflate the layout for this fragment
        return loginBinding.root
    }
}