package com.example.notebook_example

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notebook_example.model.UserModel


class LoginFragmentViewModel : ViewModel() {

    private var userMutableLiveData: MutableLiveData<UserModel>? = null
    private var userRepository: UserRepository? = null
    private var observer: Observer = Observer()


    init {
        userRepository = UserRepository()

    }

    fun getObserver(): Observer {
        return observer
    }


    fun loginOnclick(): MutableLiveData<UserModel>? {
        userMutableLiveData = userRepository!!.getUserListMutableLiveData(
            getObserver().getUserEmail(),
            getObserver().getUserPassword()
        )
        return userMutableLiveData
    }


    class Observer : BaseObservable() {
        private var userEmail: String
        private var userPassword: String

        init {
            userEmail = ""
            userPassword = ""
        }

        @Bindable
        fun getUserEmail(): String {
            return userEmail
        }

        fun setUserEmail(email: String?) {
            if (email != null) {
                userEmail = email
            }
            notifyPropertyChanged(BR.userEmail)
        }

        @Bindable
        fun getUserPassword(): String {
            return userPassword
        }

        fun setUserPassword(password: String?) {
            if (password != null) {
                userPassword = password
            }
            notifyPropertyChanged(BR.userPassword)

        }
    }

}