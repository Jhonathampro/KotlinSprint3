package br.com.github.sprint3.ui.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.github.sprint3.data.getEngagementByPeriod
import br.com.github.sprint3.data.getTopStudentsWithAbsences
import br.com.github.sprint3.data.mockTurmas
import br.com.github.sprint3.ui.components.AppBottomNavigation
import br.com.github.sprint3.ui.components.BottomTab
import br.com.github.sprint3.ui.components.EurofarmaLogo
import br.com.github.sprint3.ui.screens.turmas.ClickableTurmaCard
import br.com.github.sprint3.ui.theme.EuroBlue
import br.com.github.sprint3.ui.theme.EuroYellow
import br.com.github.sprint3.ui.theme.InputBorder
import br.com.github.sprint3.ui.theme.TextDark
import br.com.github.sprint3.ui.theme.TextMuted

@Composable
fun HomeScreen(
    onTurmaClick: (String) -> Unit = {},
    onTabSelected: (BottomTab) -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            HomeTopBar(onLogoutClick = onLogoutClick)
        },
        bottomBar = {
            AppBottomNavigation(
                currentTab = BottomTab.HOME,
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
            HomeTabContent(
                onTurmaClick = onTurmaClick,
                onNavigateToTurmasTab = { onTabSelected(BottomTab.TURMAS) }
            )
        }
    }
}

@Composable
fun HomeTopBar(
    onLogoutClick: () -> Unit = {}
) {
    Surface(
        color = EuroBlue,
        shadowElevation = 4.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Euro Tech",
                    color = EuroYellow,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Painel Educacional",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                EurofarmaLogo(size = 36.dp)
                Spacer(modifier = Modifier.width(10.dp))
                Surface(
                    color = Color.White.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier.clickable { onLogoutClick() }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                            contentDescription = "Sair",
                            tint = EuroYellow,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "Sair",
                            color = EuroYellow,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeTabContent(
    onTurmaClick: (String) -> Unit,
    onNavigateToTurmasTab: () -> Unit
) {
    // Calculados dinamicamente a partir dos dados mockados!
    val totalTurmas = mockTurmas.size
    val totalStudents = mockTurmas.sumOf { it.studentCount }
    val avgEngagement = if (mockTurmas.isNotEmpty()) {
        mockTurmas.map { it.getEngagementPercent() }.average().toInt()
    } else 0

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            // Welcome Header
            Card(
                colors = CardDefaults.cardColors(containerColor = EuroBlue),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(20.dp)
                ) {
                    Text(
                        text = "Bem-vindo(a)!",
                        color = EuroYellow,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Acompanhe suas turmas e a frequência calculada dos alunos Eurofarma.",
                        color = Color.White,
                        fontSize = 14.sp
                    )
                }
            }
        }

        item {
            // Dynamic Stat Cards Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StatCard(
                    title = "Turmas",
                    value = "$totalTurmas",
                    icon = Icons.Filled.Book,
                    onClick = onNavigateToTurmasTab,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Alunos",
                    value = "$totalStudents",
                    icon = Icons.Filled.People,
                    onClick = onNavigateToTurmasTab,
                    modifier = Modifier.weight(1f)
                )
                StatCard(
                    title = "Engajamento",
                    value = "$avgEngagement%",
                    icon = Icons.Filled.Dashboard,
                    onClick = {},
                    modifier = Modifier.weight(1f)
                )
            }
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Suas Turmas (Clique para ver alunos)",
                    color = TextDark,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        items(mockTurmas) { turma ->
            ClickableTurmaCard(
                turma = turma,
                onClick = { onTurmaClick(turma.id) }
            )
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .border(1.dp, InputBorder, RoundedCornerShape(12.dp))
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp),
        color = Color.White,
        shadowElevation = 2.dp
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = EuroBlue,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = value,
                color = TextDark,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = title,
                color = TextMuted,
                fontSize = 11.sp
            )
        }
    }
}

@Composable
private fun HomeTurmasTabContent(onTurmaClick: (String) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        item {
            Text(
                text = "Todas as Turmas de Tecnologia",
                color = TextDark,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Clique em uma turma para ver seus alunos e presenças",
                color = TextMuted,
                fontSize = 13.sp
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        items(mockTurmas) { turma ->
            ClickableTurmaCard(
                turma = turma,
                onClick = { onTurmaClick(turma.id) }
            )
        }
    }
}


@Composable
fun DashboardTabContent() {
    val totalTurmas = mockTurmas.size
    val totalStudents = mockTurmas.sumOf { it.studentCount }
    val avgEngagement = if (mockTurmas.isNotEmpty()) {
        mockTurmas.map { it.getEngagementPercent() }.average().toInt()
    } else 0
    val periodEngagements = getEngagementByPeriod()
    val topStudentsAbsences = getTopStudentsWithAbsences(limit = 10)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text(
                    text = "Dashboard Acadêmico",
                    color = TextDark,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Acompanhamento de faltas, engajamento e métricas por período",
                    color = TextMuted,
                    fontSize = 13.sp
                )
            }
        }

        // Card 1: Engajamento por Período
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, InputBorder, RoundedCornerShape(12.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Engajamento por Período",
                            color = EuroBlue,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Surface(
                            color = EuroBlue.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(
                                text = "Comparativo",
                                color = EuroBlue,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(14.dp))

                    periodEngagements.forEach { periodInfo ->
                        Column(modifier = Modifier.padding(vertical = 6.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Período ${periodInfo.period}",
                                    color = TextDark,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "${periodInfo.engagementPercent}% (${periodInfo.totalTurmas} ${if (periodInfo.totalTurmas == 1) "turma" else "turmas"})",
                                    color = EuroBlue,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            // Progress bar indicator
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(8.dp)
                                    .background(Color(0xFFE9ECEF), RoundedCornerShape(4.dp))
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth(fraction = (periodInfo.engagementPercent / 100f).coerceIn(0f, 1f))
                                        .fillMaxHeight()
                                        .background(EuroBlue, RoundedCornerShape(4.dp))
                                )
                            }
                        }
                    }
                }
            }
        }

        // Card 2: Alunos com Faltas (Top 10)
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, InputBorder, RoundedCornerShape(12.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Alunos com Faltas (Top 10)",
                            color = Color(0xFFD9534F),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Surface(
                            color = Color(0xFFD9534F).copy(alpha = 0.1f),
                            shape = RoundedCornerShape(20.dp)
                        ) {
                            Text(
                                text = "${topStudentsAbsences.size} Alunos",
                                color = Color(0xFFD9534F),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(12.dp))

                    if (topStudentsAbsences.isEmpty()) {
                        Text(
                            text = "Nenhum aluno com faltas registrado.",
                            color = TextMuted,
                            fontSize = 13.sp
                        )
                    } else {
                        topStudentsAbsences.forEachIndexed { index, studentAbsence ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Surface(
                                        color = EuroBlue.copy(alpha = 0.08f),
                                        shape = RoundedCornerShape(6.dp),
                                        modifier = Modifier.size(24.dp)
                                    ) {
                                        Box(contentAlignment = Alignment.Center) {
                                            Text(
                                                text = "${index + 1}",
                                                color = EuroBlue,
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = studentAbsence.studentName,
                                            color = TextDark,
                                            fontSize = 13.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                        Text(
                                            text = studentAbsence.turmaName,
                                            color = TextMuted,
                                            fontSize = 11.sp,
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.width(8.dp))
                                Surface(
                                    color = Color(0xFFFFF3CD),
                                    shape = RoundedCornerShape(12.dp),
                                    border = BorderStroke(1.dp, Color(0xFFFFEEBA))
                                ) {
                                    Text(
                                        text = "${studentAbsence.absencesCount} ${if (studentAbsence.absencesCount == 1) "falta" else "faltas"}",
                                        color = Color(0xFF856404),
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Card 3: Engajamento por Turma
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, InputBorder, RoundedCornerShape(12.dp))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Engajamento Calculado por Turma",
                        color = EuroBlue,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    mockTurmas.forEach { turma ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 6.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = turma.name,
                                    color = TextDark,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Medium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                                Text(
                                    text = "Período: ${turma.period}",
                                    color = TextMuted,
                                    fontSize = 11.sp
                                )
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "${turma.getEngagementPercent()}%",
                                color = EuroBlue,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = "Média Geral: $avgEngagement% ($totalStudents alunos em $totalTurmas turmas)",
                        color = TextMuted,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

