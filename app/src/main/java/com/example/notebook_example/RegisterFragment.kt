package com.example.notebook_example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.notebook_example.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var registerBinding: FragmentRegisterBinding

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            registerBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_register,container,false)

            





            // Inflate the layout for this fragment
            return registerBinding.root
        }

}
