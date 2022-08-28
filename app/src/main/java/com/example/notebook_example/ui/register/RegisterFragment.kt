package com.example.notebook_example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notebook_example.databinding.FragmentRegisterBinding
import com.example.notebook_example.model.UserModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterFragment : Fragment() {

    private lateinit var registerBinding: FragmentRegisterBinding
    private lateinit var db: FirebaseFirestore

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            registerBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_register,container,false)
            initDb()
            registerBinding.registerButton.setOnClickListener {
                val nameAndSurname = registerBinding.nameAndSurnameRegister.text.toString()
                val email = registerBinding.emailRegister.text.toString()
                val password = registerBinding.passwordRegister.text.toString()
                val confirmPassword = registerBinding.rePasswordRegister.text.toString()
                if(nameAndSurname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                    Toast.makeText(requireContext(), "Please enter empty blanks.", Toast.LENGTH_SHORT).show()
                }
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    Toast.makeText(requireContext(), "Please enter valid email address.", Toast.LENGTH_SHORT).show()
                }
                else if (password.length < 6 || password != confirmPassword){
                    Toast.makeText(requireContext(), "The length of your password must be greater than 6 and the same as confirm password.", Toast.LENGTH_SHORT).show()
                }
                else{
                    val user = UserModel("",nameAndSurname, email,password,"What is your favorite animal?","dog", emptyList())
                    db.collection("users").add(user).addOnSuccessListener {
                        Toast.makeText(requireContext(), "Registration is success.", Toast.LENGTH_SHORT).show()
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                    }.addOnFailureListener {
                        Toast.makeText(
                            requireContext(),
                            "Registration is failure. Please try again later",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            // Inflate the layout for this fragment
            return registerBinding.root
        }

    private fun initDb(){
        db = Firebase.firestore
    }
}
