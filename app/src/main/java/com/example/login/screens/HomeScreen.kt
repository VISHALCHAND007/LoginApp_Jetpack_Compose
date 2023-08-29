package com.example.login.screens
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.login.components.BoldText
import com.example.login.components.ButtonComposable
import com.example.login.components.Spacer
import com.example.login.data.registration.RegistrationViewModel

@Composable
fun HomeScreen(
    navigationController: NavHostController,
    registrationViewModel: RegistrationViewModel = viewModel()
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column {
            BoldText(text = "Home")
            Spacer(value = 10.dp)
            ButtonComposable(
                text = "Logout", onButtonClicked = {
                    registrationViewModel.logout(navigationController)
                },
                isEnabled = true
            )
        }
    }
}


