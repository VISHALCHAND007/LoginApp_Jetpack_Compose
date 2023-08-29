package com.example.login.data.rules

object Validator {
    fun validateFirstName(firstName: String): ValidationResult {
        return ValidationResult(
            (!firstName.isNullOrBlank() && firstName.length >= 4)
        )
    }

    fun validateLastName(lastName: String): ValidationResult {
        return ValidationResult(
            (!lastName.isNullOrBlank() && lastName.length >= 4)
        )
    }

    fun validateEmail(email: String): ValidationResult {
        return ValidationResult(
            (!email.isNullOrBlank() && email.length >= 4)
        )
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(
            (!password.isNullOrBlank() && password.length >= 4)
        )
    }

    fun validatePrivacyPolicy(status: Boolean): ValidationResult {
        return ValidationResult(
            status
        )
    }
}

data class ValidationResult(
    val result: Boolean = false
)