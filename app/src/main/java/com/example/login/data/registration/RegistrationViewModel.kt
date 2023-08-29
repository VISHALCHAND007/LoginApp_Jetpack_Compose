package com.example.login.data.registration

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.login.data.rules.Validator
import com.example.login.navigate.Screen
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener

class RegistrationViewModel : ViewModel() {
    val registrationUIState = mutableStateOf(RegistrationUIState())
    var allValidationsChecked = mutableStateOf(false)
    var registrationInProgress = mutableStateOf(false)
    val TAG = "here=="

    fun onEvent(event: RegistrationUIEvents, navController: NavController? = null) {
        validateDataWithRules()
        when (event) {
            is RegistrationUIEvents.FirstNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    firstName = event.firstName
                )
            }

            is RegistrationUIEvents.LastNameChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    lastName = event.lastName
                )
            }

            is RegistrationUIEvents.EmailChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    email = event.email
                )
            }

            is RegistrationUIEvents.PasswordChanged -> {
                registrationUIState.value = registrationUIState.value.copy(
                    password = event.password
                )
            }

            is RegistrationUIEvents.RegistrationButtonClicked -> {
                signUpWithFirebase(
                    registrationUIState.value.email,
                    registrationUIState.value.password,
                    navController
                )
            }
            is RegistrationUIEvents.PrivacyPolicyCheckBoxClicked -> {
                registrationUIState.value = registrationUIState.value.copy(
                    privacyPolicyAccepted = event.status
                )
            }
        }
    }

    private fun signUpWithFirebase(email: String, password: String, navController: NavController?) {
        registrationInProgress.value = true
        Log.e(TAG, "Inside signUpWithFirebase")
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(
                email,
                password
            ).addOnCompleteListener {
                registrationInProgress.value = false

                Log.e(TAG, "Inside addOnSuccessListener")
                Log.e(TAG, "isSuccessful=${it.isSuccessful}")

                if(it.isSuccessful) {
                    navController?.navigate(Screen.HomeScreen.route)
                }

            }
            .addOnFailureListener {
                registrationInProgress.value = false

                Log.e(TAG, "Inside addOnFailureListener")
                Log.e(TAG, "onFailMessage=${it.localizedMessage}")
            }
    }
    fun logout(navigationController: NavHostController) {
        val firebaseAuth = FirebaseAuth.getInstance()

        firebaseAuth.signOut()
        val authStateListener = AuthStateListener{
            if(it.currentUser == null) {
                Log.e(TAG, "Logout Success")
                navigationController.navigate(Screen.SignInScreen.route)

            } else {
                Log.e(TAG, "Logout failed")
            }
        }
        firebaseAuth.addAuthStateListener(authStateListener)
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
        val privacyPolicyChecked = Validator.validatePrivacyPolicy(
            status = registrationUIState.value.privacyPolicyAccepted
        )

        registrationUIState.value = registrationUIState.value.copy(
            firstNameError = firstName.result,
            lastNameError = lastName.result,
            emailError = email.result,
            passwordError = password.result,
            privacyPolicyAcceptedError = privacyPolicyChecked.result
        )
        allValidationsChecked.value = firstName.result && lastName.result && email.result && password.result && privacyPolicyChecked.result
    }
}