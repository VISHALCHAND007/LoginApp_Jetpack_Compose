package com.example.login.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel

class RegistrationViewModel : ViewModel() {
    val registrationUIState = mutableStateOf(RegistrationUIState())

    fun onEvent(event: UIEvents) {
        when(event) {
            is UIEvents.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
            }
            is UIEvents.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
            }
            is UIEvents.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
            }
            is UIEvents.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
            }
            is UIEvents.RegistrationButtonClicked -> {
                signUpUser()
            }
        }
    }

    private fun signUpUser() {
        Log.e("here==", registrationUIState.value.toString())
    }
}