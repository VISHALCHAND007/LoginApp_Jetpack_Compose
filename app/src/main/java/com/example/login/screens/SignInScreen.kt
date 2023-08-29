package com.example.login.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.login.R
import com.example.login.components.BoldText
import com.example.login.components.ButtonComposable
import com.example.login.components.ClickableText
import com.example.login.components.DividerComposable
import com.example.login.components.ForgotPasswordComposable
import com.example.login.components.NormalText
import com.example.login.components.PasswordTextField
import com.example.login.components.Spacer
import com.example.login.components.TextField
import com.example.login.data.login.LoginUIEvent
import com.example.login.data.login.LoginViewModel
import com.example.login.navigate.Screen

@Composable
fun SignInScreen(
    navigationController: NavHostController,
    loginViewModel: LoginViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White)
        ) {
            NormalText(text = stringResource(id = R.string.hey_there))
            Spacer()
            BoldText(text = stringResource(id = R.string.welcome_back))
            Spacer(value = 40.dp)
            TextField(
                icon = Icons.Outlined.Email,
                label = stringResource(id = R.string.email),
                errorStatus = loginViewModel.loginUIState.value.emailError,
                onValueChanged = {
                    loginViewModel.onEvent(LoginUIEvent.EmailChanged(it))
                })
            Spacer()
            PasswordTextField(
                icon = Icons.Outlined.Lock,
                label = stringResource(id = R.string.password),
                errorStatus = loginViewModel.loginUIState.value.passwordError,
                onValueChanged = {
                    loginViewModel.onEvent(LoginUIEvent.PasswordChanged(it))
                })
            Spacer()
            ForgotPasswordComposable(text = stringResource(id = R.string.forgot_password)) {}
            Spacer(value = 100.dp)
            ButtonComposable(
                text = stringResource(id = R.string.app_name),
                icon = Icons.Default.ExitToApp,
                {
                    loginViewModel.onEvent(
                        LoginUIEvent.LoginButtonClicked,
                        navigationController
                    )
                },
                isEnabled = loginViewModel.allValidationChecked.value
            )
            Spacer()
            DividerComposable()
            ClickableText(displayText = stringResource(id = R.string.dont_have_an_account),
                clickableText = stringResource(id = R.string.register),
                onClick = {
                    if (it == "Register") {
                        navigationController.navigate(Screen.SignUpScreen.route)
                    }
                })
        }
        if (loginViewModel.loginInProgress.value) {
            CircularProgressIndicator()
        }
    }
}