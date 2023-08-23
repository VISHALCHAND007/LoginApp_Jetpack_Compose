package com.example.login.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.login.data.rules.Validator

class RegistrationViewModel : ViewModel() {
    val registrationUIState = mutableStateOf(RegistrationUIState())

    fun onEvent(event: UIEvents) {
        validateDataWithRules()
        when (event) {
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
    }

    private fun validateDataWithRules() {
        val firstName = Validator.validateFirstName(
            firstName = registrationUIState.value.firstName
        )
        val lastName = Validator.validateLastName(
            lastName = registrationUIState.value.lastName
        )
        val email = Validator.validateEmail(
            email = registrationUIState.value.email
        )
        val password = Validator.validatePassword(
            password = registrationUIState.value.password
        )
        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = firstName.result,
            lastNameError = lastName.result,
            emailError = email.result,
            passwordError = password.result
        )
    }
}