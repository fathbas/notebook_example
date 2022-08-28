package com.example.notebook_example.ui.forgotpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.notebook_example.R
import com.example.notebook_example.databinding.FragmentForgotPasswordBinding
import com.example.notebook_example.model.CustomSpinnerModel
import com.example.notebook_example.ui.register.adapter.CustomSpinnerAdapter

class ForgotPasswordFragment : Fragment() {

    private lateinit var forgotPasswordBinding: FragmentForgotPasswordBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        forgotPasswordBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_forgot_password,container,false)

        val modelList: List<CustomSpinnerModel> = listOf(
            CustomSpinnerModel("Please select security question..."),
            CustomSpinnerModel("What is your favourite color?"),
            CustomSpinnerModel("What is your favourite animal?")
        )
        val customDropDownAdapter = CustomSpinnerAdapter(requireContext(), modelList)

        forgotPasswordBinding.forgotPasswordSpinner.adapter = customDropDownAdapter
        // Inflate the layout for this fragment
        return forgotPasswordBinding.root
    }
}