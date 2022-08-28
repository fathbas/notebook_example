package com.example.notebook_example.ui.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notebook_example.model.ErrorResponseModel
import com.example.notebook_example.model.UserModel
import com.example.notebook_example.util.SingleEvent
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class LoginFragmentViewModel : ViewModel() {

    val loginResponse: SingleEvent<LoginEvent> by lazy { SingleEvent() }
    private var mFirestore: FirebaseFirestore? = null


    init {
        mFirestore = FirebaseFirestore.getInstance()
    }

     fun login(email: String, password: String) {
         var user: UserModel? = null
        viewModelScope.launch(Dispatchers.Main) {
            loginResponse.value = LoginEvent.Loading
            mFirestore!!.collection("users").whereEqualTo("email", email)
                .whereEqualTo("password", password).addSnapshotListener { value, _ ->
                        for (doc in value!!) {
                                user =
                                    UserModel(
                                        doc.id,
                                        doc.data["nameAndSurname"].toString(),
                                        doc.data["email"].toString(),
                                        doc.data["password"].toString(),
                                        doc.data["securityQuestion"].toString(),
                                        doc.data["questionAnswer"].toString()
                                    )
                                loginResponse.value = LoginEvent.Success(loginResponse = user)

                        }
                    }
            if (user == null){
                loginResponse.value = LoginEvent.Failure(errorResponseModel = ErrorResponseModel("Invalid email or password.Please try again!"))
            }



        }

    }
    sealed class LoginEvent {
        class Success(val loginResponse: UserModel?) : LoginEvent()
        class Failure(val errorResponseModel: ErrorResponseModel) : LoginEvent()
        object Loading : LoginEvent()
        object Empty : LoginEvent()
    }
}



