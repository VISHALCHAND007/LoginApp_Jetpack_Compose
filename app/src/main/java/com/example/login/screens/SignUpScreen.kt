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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.login.R
import com.example.login.components.BoldText
import com.example.login.components.NormalText
import com.example.login.components.PasswordTextField
import com.example.login.components.Spacer
import com.example.login.components.TermsAndConditions
import com.example.login.components.TextField

@Composable
fun SignUpScreen() {
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
        }
    }
}
