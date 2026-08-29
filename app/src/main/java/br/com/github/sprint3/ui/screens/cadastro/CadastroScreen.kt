package br.com.github.sprint3.ui.screens.cadastro

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Book
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
import br.com.github.sprint3.data.addTurma
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

private enum class CadastroMode {
    ALUNO, TURMA
}

@Composable
fun CadastroScreen(
    onTabSelected: (BottomTab) -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    var mode by remember { mutableStateOf(CadastroMode.ALUNO) }

    // Aluno form state
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var birthDate by remember { mutableStateOf("") }
    var selectedTurma by remember { mutableStateOf<Turma?>(null) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    var nameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var turmaError by remember { mutableStateOf<String?>(null) }

    // Turma form state
    var newTurmaName by remember { mutableStateOf("") }
    var newTurmaPeriod by remember { mutableStateOf("") }
    var newTurmaSubject by remember { mutableStateOf("") }
    var newTurmaTeacher by remember { mutableStateOf("") }
    var isPeriodDropdownExpanded by remember { mutableStateOf(false) }

    var newTurmaNameError by remember { mutableStateOf<String?>(null) }
    var newTurmaPeriodError by remember { mutableStateOf<String?>(null) }

    var successMessage by remember { mutableStateOf<String?>(null) }

    val periodOptions = listOf("Manhã", "Tarde", "Noite")

    fun validateAndSubmitStudent() {
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

    fun validateAndSubmitTurma() {
        var isValid = true
        newTurmaNameError = null
        newTurmaPeriodError = null
        successMessage = null

        if (newTurmaName.trim().isBlank()) {
            newTurmaNameError = "Digite o nome da nova turma."
            isValid = false
        }

        if (newTurmaPeriod.trim().isBlank()) {
            newTurmaPeriodError = "Selecione o período (Manhã, Tarde ou Noite)."
            isValid = false
        }

        if (isValid) {
            val createdTurma = addTurma(
                name = newTurmaName.trim(),
                period = newTurmaPeriod.trim(),
                subject = newTurmaSubject.trim(),
                teacherName = newTurmaTeacher.trim()
            )
            successMessage = "Turma \"${createdTurma.name}\" (${createdTurma.period}) criada com sucesso!"
            newTurmaName = ""
            newTurmaPeriod = ""
            newTurmaSubject = ""
            newTurmaTeacher = ""
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
                    // Mode Selector Tabs (Cadastrar Aluno / Criar Nova Turma)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFE9ECEF), RoundedCornerShape(12.dp))
                            .padding(4.dp)
                    ) {
                        Surface(
                            color = if (mode == CadastroMode.ALUNO) EuroBlue else Color.Transparent,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    mode = CadastroMode.ALUNO
                                    successMessage = null
                                }
                        ) {
                            Box(
                                modifier = Modifier.padding(vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Cadastrar Aluno",
                                    color = if (mode == CadastroMode.ALUNO) EuroYellow else TextDark,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }

                        Surface(
                            color = if (mode == CadastroMode.TURMA) EuroBlue else Color.Transparent,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .weight(1f)
                                .clickable {
                                    mode = CadastroMode.TURMA
                                    successMessage = null
                                }
                        ) {
                            Box(
                                modifier = Modifier.padding(vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Criar Nova Turma",
                                    color = if (mode == CadastroMode.TURMA) EuroYellow else TextDark,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }

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
                                text = if (mode == CadastroMode.ALUNO) "Cadastrar Novo Aluno" else "Criar Nova Turma",
                                color = EuroYellow,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = if (mode == CadastroMode.ALUNO)
                                    "Preencha os dados do aluno e escolha uma turma para matricular."
                                else
                                    "Cadastre uma nova turma com período para disponibilizar matrículas de alunos.",
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

                if (mode == CadastroMode.ALUNO) {
                    // Form Cadastrar Aluno
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
                                                        text = selectedTurma?.let { "${it.name} (${it.period})" }
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
                                                                text = "${turma.name} (${turma.period})",
                                                                fontWeight = FontWeight.Bold,
                                                                color = TextDark
                                                            )
                                                            Text(
                                                                text = "Disciplina: ${turma.subject}",
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

                                Button(
                                    onClick = { validateAndSubmitStudent() },
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
                } else {
                    // Form Criar Nova Turma
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
                                    text = "Dados da Turma",
                                    color = TextDark,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold
                                )

                                // Nome da Turma
                                Column {
                                    OutlinedTextField(
                                        value = newTurmaName,
                                        onValueChange = {
                                            newTurmaName = it
                                            newTurmaNameError = null
                                        },
                                        label = { Text("Nome da Turma / Curso *") },
                                        leadingIcon = {
                                            Icon(
                                                imageVector = Icons.Filled.School,
                                                contentDescription = null,
                                                tint = EuroBlue
                                            )
                                        },
                                        isError = newTurmaNameError != null,
                                        singleLine = true,
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedBorderColor = EuroBlue,
                                            focusedLabelColor = EuroBlue
                                        ),
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    if (newTurmaNameError != null) {
                                        Text(
                                            text = newTurmaNameError!!,
                                            color = Color.Red,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(start = 4.dp, top = 2.dp)
                                        )
                                    }
                                }

                                // Período (Dropdown: Manhã, Tarde, Noite)
                                Column {
                                    Text(
                                        text = "Período *",
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
                                                    color = if (newTurmaPeriodError != null) Color.Red else InputBorder,
                                                    shape = RoundedCornerShape(4.dp)
                                                )
                                                .clickable { isPeriodDropdownExpanded = !isPeriodDropdownExpanded },
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
                                                        imageVector = Icons.Filled.AccessTime,
                                                        contentDescription = null,
                                                        tint = EuroBlue,
                                                        modifier = Modifier.size(20.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(10.dp))
                                                    Text(
                                                        text = if (newTurmaPeriod.isNotBlank()) newTurmaPeriod else "Selecione o período (Manhã, Tarde, Noite)...",
                                                        color = if (newTurmaPeriod.isNotBlank()) TextDark else TextMuted,
                                                        fontSize = 14.sp
                                                    )
                                                }
                                                Icon(
                                                    imageVector = Icons.Filled.ArrowDropDown,
                                                    contentDescription = "Expandir períodos",
                                                    tint = EuroBlue
                                                )
                                            }
                                        }

                                        DropdownMenu(
                                            expanded = isPeriodDropdownExpanded,
                                            onDismissRequest = { isPeriodDropdownExpanded = false },
                                            modifier = Modifier.fillMaxWidth(0.9f)
                                        ) {
                                            periodOptions.forEach { periodOpt ->
                                                DropdownMenuItem(
                                                    text = {
                                                        Text(
                                                            text = periodOpt,
                                                            fontWeight = FontWeight.Bold,
                                                            color = TextDark
                                                        )
                                                    },
                                                    onClick = {
                                                        newTurmaPeriod = periodOpt
                                                        newTurmaPeriodError = null
                                                        isPeriodDropdownExpanded = false
                                                    }
                                                )
                                            }
                                        }
                                    }

                                    if (newTurmaPeriodError != null) {
                                        Text(
                                            text = newTurmaPeriodError!!,
                                            color = Color.Red,
                                            fontSize = 12.sp,
                                            modifier = Modifier.padding(start = 4.dp, top = 4.dp)
                                        )
                                    }
                                }

                                // Disciplina (Opcional)
                                OutlinedTextField(
                                    value = newTurmaSubject,
                                    onValueChange = { newTurmaSubject = it },
                                    label = { Text("Disciplina (opcional, ex: Arquitetura Cloud)") },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Filled.Book,
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

                                // Professor (Opcional)
                                OutlinedTextField(
                                    value = newTurmaTeacher,
                                    onValueChange = { newTurmaTeacher = it },
                                    label = { Text("Professor responsável (opcional)") },
                                    leadingIcon = {
                                        Icon(
                                            imageVector = Icons.Filled.Person,
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

                                Spacer(modifier = Modifier.height(8.dp))

                                Button(
                                    onClick = { validateAndSubmitTurma() },
                                    colors = ButtonDefaults.buttonColors(containerColor = EuroBlue),
                                    shape = RoundedCornerShape(10.dp),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(50.dp)
                                ) {
                                    Text(
                                        text = "Criar Turma",
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
}

@Preview(showBackground = true)
@Composable
fun CadastroScreenPreview() {
    CadastroScreen()
}
