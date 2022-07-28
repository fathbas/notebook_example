package com.example.notebook_example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.notebook_example.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_login,container,false)

        loginBinding.newUserLogin.apply {
            this.setOnClickListener {
                it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }

        // Inflate the layout for this fragment
        return loginBinding.root
    }
}