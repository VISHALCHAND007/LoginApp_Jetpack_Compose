package com.example.login.data

sealed class UIEvents {
    data class FirstNameChanged(val firstName: String): UIEvents()
    data class LastNameChanged(val lastName: String): UIEvents()
    data class EmailChanged(val email: String): UIEvents()
    data class PasswordChanged(val password: String): UIEvents()
    object RegistrationButtonClicked: UIEvents()
}
