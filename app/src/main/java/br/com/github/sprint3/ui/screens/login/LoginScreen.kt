package br.com.github.sprint3.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.github.sprint3.ui.components.EurofarmaLogo
import br.com.github.sprint3.ui.theme.CardBackground
import br.com.github.sprint3.ui.theme.EuroBlue
import br.com.github.sprint3.ui.theme.EuroYellow
import br.com.github.sprint3.ui.theme.InputBorder
import br.com.github.sprint3.ui.theme.TextDark
import br.com.github.sprint3.ui.theme.TextMuted

@Composable
fun LoginScreen() {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val isWideScreen = configuration.screenWidthDp > 600

    Scaffold(
        containerColor = Color.White
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Surface(
                modifier = Modifier
                    .padding(24.dp)
                    .widthIn(max = 850.dp)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE5E5E8),
                        shape = RoundedCornerShape(20.dp)
                    ),
                shape = RoundedCornerShape(20.dp),
                color = CardBackground,
                shadowElevation = 4.dp
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 32.dp, vertical = 40.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.Center
                ) {
                    if (isWideScreen) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Left form column
                            Column(
                                modifier = Modifier
                                    .weight(1.2f)
                                    .padding(end = 24.dp)
                            ) {
                                LoginFormContent(
                                    email = email,
                                    onEmailChange = { email = it },
                                    password = password,
                                    onPasswordChange = { password = it },
                                    isPasswordVisible = isPasswordVisible,
                                    onPasswordVisibleChange = { isPasswordVisible = it }
                                )
                            }

                            // Right logo graphic
                            Box(
                                modifier = Modifier
                                    .weight(0.8f),
                                contentAlignment = Alignment.Center
                            ) {
                                EurofarmaLogo(size = 200.dp)
                            }
                        }
                    } else {
                        // Portrait view layout
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            LoginFormContent(
                                email = email,
                                onEmailChange = { email = it },
                                password = password,
                                onPasswordChange = { password = it },
                                isPasswordVisible = isPasswordVisible,
                                onPasswordVisibleChange = { isPasswordVisible = it }
                            )

                            Spacer(modifier = Modifier.height(28.dp))

                            EurofarmaLogo(size = 150.dp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun LoginFormContent(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onPasswordVisibleChange: (Boolean) -> Unit
) {
    Text(
        text = "BEM VINDO(A)",
        color = EuroBlue,
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.5.sp
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = "Preencha seus dados",
        color = Color(0xFF2B65A8),
        fontSize = 14.sp,
        fontWeight = FontWeight.Medium
    )

    Spacer(modifier = Modifier.height(24.dp))

    // Email label & field
    Text(
        text = "Email",
        color = TextDark,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(4.dp))

    OutlinedTextField(
        value = email,
        onValueChange = onEmailChange,
        placeholder = {
            Text(
                text = "Seu email",
                color = Color.Gray,
                fontSize = 13.sp
            )
        },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = EuroBlue,
            unfocusedBorderColor = InputBorder,
            focusedTextColor = TextDark,
            unfocusedTextColor = TextDark
        ),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Senha label & field
    Text(
        text = "Senha",
        color = TextDark,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(4.dp))

    OutlinedTextField(
        value = password,
        onValueChange = onPasswordChange,
        placeholder = {
            Text(
                text = "••••••••",
                color = Color.Gray,
                fontSize = 13.sp
            )
        },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            focusedBorderColor = EuroBlue,
            unfocusedBorderColor = InputBorder,
            focusedTextColor = TextDark,
            unfocusedTextColor = TextDark
        ),
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(6.dp))

    // Mostrar Senha checkbox
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onPasswordVisibleChange(!isPasswordVisible) }
            .padding(vertical = 4.dp)
    ) {
        Checkbox(
            checked = isPasswordVisible,
            onCheckedChange = onPasswordVisibleChange,
            colors = CheckboxDefaults.colors(
                checkedColor = EuroBlue,
                uncheckedColor = Color.Gray
            ),
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(6.dp))

        Text(
            text = "Mostrar Senha",
            color = TextMuted,
            fontSize = 12.sp
        )
    }

    Spacer(modifier = Modifier.height(20.dp))

    // Entrar Button
    Button(
        onClick = { /* First stage UI only */ },
        colors = ButtonDefaults.buttonColors(
            containerColor = EuroBlue,
            contentColor = EuroYellow
        ),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
    ) {
        Text(
            text = "Entrar",
            color = EuroYellow,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Preview(showBackground = true, widthDp = 800, heightDp = 500)
@Composable
fun LoginScreenWidePreview() {
    LoginScreen()
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}
