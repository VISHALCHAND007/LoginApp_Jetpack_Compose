package com.example.login.data.registration

sealed class RegistrationUIEvents {
    data class FirstNameChanged(val firstName: String): RegistrationUIEvents()
    data class LastNameChanged(val lastName: String): RegistrationUIEvents()
    data class EmailChanged(val email: String): RegistrationUIEvents()
    data class PasswordChanged(val password: String): RegistrationUIEvents()
    data class PrivacyPolicyCheckBoxClicked(val status: Boolean): RegistrationUIEvents()
    object RegistrationButtonClicked: RegistrationUIEvents()
}
