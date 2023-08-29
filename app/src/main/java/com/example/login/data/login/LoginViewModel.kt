package com.example.login.data.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.example.login.data.rules.Validator
import com.example.login.navigate.Screen
import com.google.firebase.auth.FirebaseAuth

class LoginViewModel: ViewModel() {
    val loginUIState = mutableStateOf(LoginUISate())
    var allValidationChecked = mutableStateOf(false)
    var loginInProgress = mutableStateOf(false)
    private val TAG = "here=="

    fun onEvent(event: LoginUIEvent, navController: NavController? = null) {
        validateFields()
        when(event) {
            is LoginUIEvent.EmailChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    email = event.email
                )
            }
            is LoginUIEvent.PasswordChanged -> {
                loginUIState.value = loginUIState.value.copy(
                    password = event.password
                )
            }
            is LoginUIEvent.LoginButtonClicked -> {
                login(navController)
            }
        }
    }

    private fun login(navController: NavController?) {
        val email = loginUIState.value.email
        val password = loginUIState.value.password

        val firebaseAuth = FirebaseAuth.getInstance()
        loginInProgress.value = true
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener{
            if(it.isSuccessful) {
                loginInProgress.value = false
                Log.e(TAG, "login success")
                navController?.navigate(Screen.HomeScreen.route)
            }
        }. addOnFailureListener{
            loginInProgress.value = false
            Log.e(TAG, "login failed")
        }
    }

    private fun validateFields() {
        val email = Validator.validateEmail(
            email = loginUIState.value.email
        )
        val password = Validator.validatePassword(
            password = loginUIState.value.password
        )
        loginUIState.value = loginUIState.value.copy(
            emailError = email.result,
            passwordError = password.result
        )
        allValidationChecked.value = email.result && password.result
    }
}