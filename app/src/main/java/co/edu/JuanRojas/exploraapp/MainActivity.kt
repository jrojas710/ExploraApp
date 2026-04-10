package co.edu.JuanRojas.exploraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.edu.JuanRojas.exploraapp.ui.theme.ExploraAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val myNavController = rememberNavController()

            NavHost(
                navController = myNavController,
                startDestination = "login",
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = "login") {
                    LoginScreen(onLoginSuccess = {}, onNavigateToRegister = {
                        myNavController.navigate("register")
                    })
                }
                composable(route = "register") {
                    RegisterScreen(
                        onRegisterSuccess = {},
                        onNavigateToLogin = { myNavController.popBackStack() },
                        onBackClick = { myNavController.popBackStack() }
                    )
                }
            }
        }
    }
}