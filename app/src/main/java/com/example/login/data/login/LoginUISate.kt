package com.example.login.data.login

data class LoginUISate(
    val email: String = "",
    val password: String = "",

    val emailError: Boolean = false,
    val passwordError: Boolean = false
)
