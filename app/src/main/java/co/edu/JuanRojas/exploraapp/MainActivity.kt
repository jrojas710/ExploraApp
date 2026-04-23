package co.edu.JuanRojas.exploraapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
// --- AGREGADO: Importamos Text y Box para la pantalla de Home temporal ---
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
// --------------------------------------------------------------------------
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
// --- AGREGADO: Importar Firebase Auth ---
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val myNavController = rememberNavController()

            val auth = FirebaseAuth.getInstance()
            val startRoute = if (auth.currentUser != null) "home" else "login"

            NavHost(
                navController = myNavController,
                startDestination = startRoute,
                modifier = Modifier.fillMaxSize()
            ) {
                composable(route = "login") {
                    LoginScreen(
                        onLoginSuccess = {
                            myNavController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onNavigateToRegister = {
                            myNavController.navigate("register")
                        }
                    )
                }
                composable(route = "register") {
                    RegisterScreen(
                        onRegisterSuccess = {
                            myNavController.navigate("home") {
                                popUpTo("login") { inclusive = true }
                            }
                        },
                        onNavigateToLogin = { myNavController.popBackStack() },
                        onBackClick = { myNavController.popBackStack() }
                    )
                }

                composable(route = "home") {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "PANTALLLA PRINCIPAL")
                    }
                }
            }
        }
    }
}