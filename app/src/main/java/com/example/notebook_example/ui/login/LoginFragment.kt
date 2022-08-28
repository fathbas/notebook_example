package com.example.notebook_example.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.notebook_example.NoteActivity
import com.example.notebook_example.R
import com.example.notebook_example.databinding.FragmentLoginBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var loginBinding: FragmentLoginBinding
    private val loginViewModel: LoginFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        loginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)
        observers()

        loginBinding.apply {
            this.newUserLogin.setOnClickListener {
                it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
            this.forgotPasswordLogin.setOnClickListener {
                it.findNavController().navigate(R.id.action_loginFragment_to_forgotPasswordFragment)
            }
            this.loginButton.setOnClickListener {
                loginViewModel.login(
                    this.emailLogin.text.toString(),
                    this.passwordLogin.text.toString()
                )
            }
        }

        // Inflate the layout for this fragment
        return loginBinding.root
    }

    private fun observers() {
        lifecycleScope.launch(Dispatchers.Main) {
            forLifeCycle()
        }
    }

    private fun forLifeCycle() {
        loginViewModel.loginResponse.observe(viewLifecycleOwner) { event ->
            when (event) {
                is LoginFragmentViewModel.LoginEvent.Success -> {
                    Toast.makeText(requireContext(), "Login is successful", Toast.LENGTH_SHORT)
                        .show()
                    val intent = Intent(activity, NoteActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("userData", event.loginResponse)
                    activity?.startActivity(intent)
                    activity?.finish()
                }
                is LoginFragmentViewModel.LoginEvent.Failure -> {
                    Toast.makeText(
                        requireContext(),
                        event.errorResponseModel.errorMessage,
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
                is LoginFragmentViewModel.LoginEvent.Loading -> {
                    // no-op
                }

                is LoginFragmentViewModel.LoginEvent.Empty -> {
                    // no-op
                }
                else -> {
                    // no-op
                }
            }
        }
    }
}