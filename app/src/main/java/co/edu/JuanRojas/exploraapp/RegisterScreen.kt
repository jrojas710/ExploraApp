package co.edu.JuanRojas.exploraapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.*
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import co.edu.JuanRojas.exploraapp.ui.theme.ExploraAppTheme

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onBackClick: () -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(false) }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    val primaryOrange = Color(0xFFE45D25)
    val lightGrayBg = Color(0xFFF8F9FE)
    val inputBg = Color(0xFFE5E5EA)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = lightGrayBg
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp)
        ) {

            IconButton(onClick = onBackClick) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = primaryOrange)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text("Explorando Colombia", color = primaryOrange, fontSize = 22.sp, fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(32.dp))

            Text("Crea tu cuenta", fontSize = 32.sp, fontWeight = FontWeight.Bold)
            Text("Empieza tu aventura por el realismo mágico", color = Color.Gray)

            Spacer(modifier = Modifier.height(32.dp))

            RegisterField("NOMBRE COMPLETO", name, { name = it }, "Tu nombre", Icons.Default.Person, inputBg)

            Spacer(modifier = Modifier.height(20.dp))

            RegisterField("CORREO", email, { email = it }, "correo@ejemplo.com", Icons.Default.Email, inputBg)

            Spacer(modifier = Modifier.height(20.dp))

            RegisterField(
                "CONTRASEÑA",
                password,
                { password = it },
                "********",
                Icons.Default.Lock,
                inputBg,
                true,
                passwordVisible,
                { passwordVisible = !passwordVisible }
            )

            Spacer(modifier = Modifier.height(20.dp))

            RegisterField(
                "CONFIRMAR",
                confirmPassword,
                { confirmPassword = it },
                "********",
                Icons.Default.Lock,
                inputBg,
                true,
                confirmPasswordVisible,
                { confirmPasswordVisible = !confirmPasswordVisible }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = acceptedTerms, onCheckedChange = { acceptedTerms = it })
                Text("Acepto los términos", fontSize = 12.sp)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { if (acceptedTerms) onRegisterSuccess() },
                modifier = Modifier.fillMaxWidth().height(60.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                contentPadding = PaddingValues()
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().background(
                        Brush.horizontalGradient(listOf(primaryOrange, Color(0xFFFF8A65)))
                    ),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        Text("Registrarse")
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Text("¿Ya tienes cuenta? ")
                Text("Login", color = primaryOrange, modifier = Modifier.clickable { onNavigateToLogin() })
            }
        }
    }
}

@Composable
fun RegisterField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    inputBg: Color,
    isPassword: Boolean = false,
    passwordVisible: Boolean = false,
    onToggleVisibility: () -> Unit = {}
) {
    Column {
        Text(label, fontSize = 12.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth().height(56.dp),
            placeholder = { Text(placeholder) },
            leadingIcon = { Icon(icon, null) },
            visualTransformation = if (isPassword && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                if (isPassword) {
                    Icon(
                        imageVector = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                        contentDescription = null,
                        modifier = Modifier.clickable { onToggleVisibility() }
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = if (isPassword) KeyboardType.Password else KeyboardType.Text
            ),
            singleLine = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewRegister() {
    ExploraAppTheme {
        RegisterScreen({}, {})
    }
}