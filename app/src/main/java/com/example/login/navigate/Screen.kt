package com.example.login.navigate

sealed class Screen(val route: String) {
    object SignUpScreen: Screen("sign_up")
    object SignInScreen: Screen("sign_in")

    fun args(vararg arg: String): String{
        return buildString {
            append(route)
            arg.forEach {
                append("/$it")
            }
        }
    }
}
