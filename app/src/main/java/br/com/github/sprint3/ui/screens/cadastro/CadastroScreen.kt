package br.com.github.sprint3.ui.screens.cadastro

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.github.sprint3.data.addStudentToTurma
import br.com.github.sprint3.data.mockTurmas
import br.com.github.sprint3.model.Turma
import br.com.github.sprint3.ui.components.AppBottomNavigation
import br.com.github.sprint3.ui.components.BottomTab
import br.com.github.sprint3.ui.screens.home.HomeTopBar
import br.com.github.sprint3.ui.theme.EuroBlue
import br.com.github.sprint3.ui.theme.EuroYellow
import br.com.github.sprint3.ui.theme.InputBorder
import br.com.github.sprint3.ui.theme.TextDark
import br.com.github.sprint3.ui.theme.TextMuted

@Composable
fun CadastroScreen(
    onTabSelected: (BottomTab) -> Unit = {}
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var selectedTurma by remember { mutableStateOf<Turma?>(null) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var turmaError by remember { mutableStateOf<String?>(null) }
    var successMessage by remember { mutableStateOf<String?>(null) }

    fun validateAndSubmit() {
        var isValid = true
        nameError = null
        emailError = null
        turmaError = null
        successMessage = null

        if (name.trim().isBlank()) {
            nameError = "Digite o nome completo do aluno."
            isValid = false
        }

        if (email.trim().isBlank() || !email.contains("@") || !email.contains(".")) {
            emailError = "Digite um e-mail válido (ex: aluno@eurofarma.com)."
            isValid = false
        }

        if (selectedTurma == null) {
            turmaError = "Selecione a turma em que o aluno será cadastrado."
            isValid = false
        }

        if (isValid && selectedTurma != null) {
            val turma = selectedTurma!!
            val success = addStudentToTurma(
                turmaId = turma.id,
                studentName = name.trim(),
                email = email.trim()
            )

            if (success) {
                successMessage = "Aluno \"${name.trim()}\" cadastrado com sucesso na ${turma.name} (${turma.subject})!"
                name = ""
                email = ""
                birthDate = ""
                selectedTurma = null
            }
        }
    }

    Scaffold(
        topBar = {
            HomeTopBar()
        },
        bottomBar = {
            AppBottomNavigation(
                currentTab = BottomTab.CADASTRO,
                onTabSelected = onTabSelected
            )
        },
        containerColor = Color(0xFFF8F9FA)
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    // Header Banner
                    Card(
                        colors = CardDefaults.cardColors(containerColor = EuroBlue),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp)
                        ) {
                            Text(
                                text = "Cadastrar Novo Aluno",
                                color = EuroYellow,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Preencha os dados e selecione a turma de Tecnologia para matricular o aluno.",
                                color = Color.White,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                if (successMessage != null) {
                    item {
                        Card(
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color(0xFF81C784), RoundedCornerShape(12.dp))
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = "Sucesso",
                                    tint = Color(0xFF2E7D32),
                                    modifier = Modifier.size(28.dp)
                                )
                                Spacer(modifier = Modifier.width(12.dp))
                                Text(
                                    text = successMessage!!,
                                    color = Color(0xFF1B5E20),
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }

                item {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, InputBorder, RoundedCornerShape(14.dp)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(20.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "Dados do Aluno",
                                color = TextDark,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                            // Nome Completo
                            Column {
                                OutlinedTextField(
                                    value = name,
                                    onValueChange = {
                                        name = it
                                        nameError = null
                                    },
                                    label = { Text("Nome completo *") },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Filled.Person,
                                            contentDescription = null,
                                            tint = EuroBlue
                                        )
                                    },
                                    isError = nameError != null,
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = EuroBlue,
                                        focusedLabelColor = EuroBlue
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                if (nameError != null) {
                                    Text(
                                        text = nameError!!,
                                        color = Color.Red,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                                    )
                                }
                            }

                            // E-mail
                            Column {
                                OutlinedTextField(
                                    value = email,
                                    onValueChange = {
                                        email = it
                                        emailError = null
                                    },
                                    label = { Text("E-mail *") },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Filled.Email,
                                            contentDescription = null,
                                            tint = EuroBlue
                                        )
                                    },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                    isError = emailError != null,
                                    singleLine = true,
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = EuroBlue,
                                        focusedLabelColor = EuroBlue
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                )
                                if (emailError != null) {
                                    Text(
                                        text = emailError!!,
                                        color = Color.Red,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                                    )
                                }
                            }

                            // Data de Nascimento (Opcional)
                            OutlinedTextField(
                                value = birthDate,
                                onValueChange = { birthDate = it },
                                label = { Text("Data de nascimento (ex: 15/04/2002)") },
                                leadingIcon = {
                                    Icon(
                                        imageVector = Icons.Filled.CalendarToday,
                                        contentDescription = null,
                                        tint = EuroBlue
                                    )
                                },
                                singleLine = true,
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = EuroBlue,
                                    focusedLabelColor = EuroBlue
                                ),
                                modifier = Modifier.fillMaxWidth()
                            )

                            // Seleção de Turma (Dropdown)
                            Column {
                                Text(
                                    text = "Turma de Tecnologia *",
                                    color = TextDark,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier.padding(bottom = 6.dp)
                                )

                                Box(modifier = Modifier.fillMaxWidth()) {
                                    Surface(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .border(
                                                width = 1.dp,
                                                color = if (turmaError != null) Color.Red else InputBorder,
                                                shape = RoundedCornerShape(4.dp)
                                            )
                                            .clickable { isDropdownExpanded = !isDropdownExpanded },
                                        shape = RoundedCornerShape(4.dp),
                                        color = Color.White
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 12.dp, vertical = 14.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Icon(
                                                    imageVector = Icons.Filled.School,
                                                    contentDescription = null,
                                                    tint = EuroBlue,
                                                    modifier = Modifier.size(20.dp)
                                                )
                                                Spacer(modifier = Modifier.width(10.dp))
                                                Text(
                                                    text = selectedTurma?.let { "${it.name} - ${it.subject}" }
                                                        ?: "Selecione uma turma...",
                                                    color = if (selectedTurma != null) TextDark else TextMuted,
                                                    fontSize = 14.sp
                                                )
                                            }
                                            Icon(
                                                imageVector = Icons.Filled.ArrowDropDown,
                                                contentDescription = "Expandir turmas",
                                                tint = EuroBlue
                                            )
                                        }
                                    }

                                    DropdownMenu(
                                        expanded = isDropdownExpanded,
                                        onDismissRequest = { isDropdownExpanded = false },
                                        modifier = Modifier.fillMaxWidth(0.9f)
                                    ) {
                                        mockTurmas.forEach { turma ->
                                            DropdownMenuItem(
                                                text = {
                                                    Column {
                                                        Text(
                                                            text = "${turma.name} - ${turma.subject}",
                                                            fontWeight = FontWeight.Bold,
                                                            color = TextDark
                                                        )
                                                        Text(
                                                            text = turma.course,
                                                            fontSize = 12.sp,
                                                            color = TextMuted
                                                        )
                                                    }
                                                },
                                                onClick = {
                                                    selectedTurma = turma
                                                    turmaError = null
                                                    isDropdownExpanded = false
                                                }
                                            )
                                        }
                                    }
                                }

                                if (turmaError != null) {
                                    Text(
                                        text = turmaError!!,
                                        color = Color.Red,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(8.dp))

                            // Botão Cadastrar
                            Button(
                                onClick = { validateAndSubmit() },
                                colors = ButtonDefaults.buttonColors(containerColor = EuroBlue),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(50.dp)
                            ) {
                                Text(
                                    text = "Cadastrar Aluno",
                                    color = EuroYellow,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CadastroScreenPreview() {
    CadastroScreen()
}
