package com.example.login.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.login.R
import com.example.login.components.BoldText
import com.example.login.components.ButtonComposable
import com.example.login.components.ClickableText
import com.example.login.components.DividerComposable
import com.example.login.components.NormalText
import com.example.login.components.PasswordTextField
import com.example.login.components.Spacer
import com.example.login.components.TermsAndConditions
import com.example.login.components.TextField
import com.example.login.navigate.Screen

@Composable
fun SignUpScreen(navigationController: NavHostController) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            NormalText(text = stringResource(id = R.string.hey_there))
            Spacer()
            BoldText(text = stringResource(id = R.string.create_account))
            Spacer(value = 40.dp)
            TextField(
                icon = Icons.Outlined.AccountCircle,
                label = stringResource(id = R.string.first_name),
                onValueChanged = {

                })
            Spacer()
            TextField(
                icon = Icons.Outlined.AccountCircle,
                label = stringResource(id = R.string.last_name),
                onValueChanged = {

                })
            Spacer()
            TextField(
                icon = Icons.Outlined.Email,
                label = stringResource(id = R.string.email),
                onValueChanged = {

                })
            Spacer()
            PasswordTextField(
                icon = Icons.Outlined.Lock,
                label = stringResource(id = R.string.password),
                onValueChanged = {})
            Spacer()
            TermsAndConditions(onCheckBoxClicked = {}, onTextClicked = {
                Log.e("here==", it)
            })
            Spacer(value = 80.dp)
            ButtonComposable(text = "Register")
            Spacer()
            DividerComposable()
            Spacer(value = 20.dp)
            ClickableText(displayText = stringResource(id = R.string.already_have_an_account),
                clickableText = stringResource(id = R.string.app_name),
                onClick = {
                    if(it == "Login") {
                        navigationController.navigate(Screen.SignInScreen.route)
                    }
                })
        }
    }
}
