package com.example.notebook_example.ui.forgotpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.notebook_example.R
import com.example.notebook_example.databinding.FragmentForgotPasswordBinding

class NewPasswordFragment : Fragment() {

    private lateinit var newPasswordBinding: FragmentForgotPasswordBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newPasswordBinding = DataBindingUtil.inflate(inflater,R.id.newPasswordFragment,container,false)
        // Inflate the layout for this fragment
        return newPasswordBinding.root
    }
}