package br.com.github.sprint3.ui.screens.students

import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.github.sprint3.data.mockTurmas
import br.com.github.sprint3.model.Student
import br.com.github.sprint3.ui.components.AppBottomNavigation
import br.com.github.sprint3.ui.components.BottomTab
import br.com.github.sprint3.ui.theme.CardBackground
import br.com.github.sprint3.ui.theme.EuroBlue
import br.com.github.sprint3.ui.theme.EuroYellow
import br.com.github.sprint3.ui.theme.InputBorder
import br.com.github.sprint3.ui.theme.TextDark
import br.com.github.sprint3.ui.theme.TextMuted

@Composable
fun StudentsScreen(
    turmaId: String,
    onBackClick: () -> Unit = {},
    onTabSelected: (BottomTab) -> Unit = {},
    onLogoutClick: () -> Unit = {}
) {
    val turma = mockTurmas.find { it.id == turmaId } ?: mockTurmas.first()

    Scaffold(
        topBar = {
            StudentsTopBar(
                title = turma.name,
                subtitle = turma.course,
                onBackClick = onBackClick,
                onLogoutClick = onLogoutClick
            )
        },
        bottomBar = {
            AppBottomNavigation(
                currentTab = BottomTab.TURMAS,
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
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    // Turma Overview Banner
                    Card(
                        colors = CardDefaults.cardColors(containerColor = EuroBlue),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Disciplina: ${turma.subject}",
                                color = EuroYellow,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Professor: ${turma.teacherName} • ${turma.period}",
                                color = Color.White.copy(alpha = 0.9f),
                                fontSize = 13.sp
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Aulas previstas: ${turma.totalAulas}",
                                    color = Color.White,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "Engajamento médio: ${turma.getEngagementPercent()}%",
                                    color = EuroYellow,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }

                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Alunos da Turma (${turma.studentCount})",
                            color = TextDark,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                items(turma.students) { student ->
                    StudentItemCard(
                        student = student,
                        totalAulas = turma.totalAulas
                    )
                }
            }
        }
    }
}

@Composable
private fun StudentsTopBar(
    title: String,
    subtitle: String,
    onBackClick: () -> Unit,
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
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Voltar",
                        tint = EuroYellow
                    )
                }

                Spacer(modifier = Modifier.width(4.dp))

                Column {
                    Text(
                        text = title,
                        color = EuroYellow,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = subtitle,
                        color = Color.White.copy(alpha = 0.8f),
                        fontSize = 11.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
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

@Composable
private fun StudentItemCard(
    student: Student,
    totalAulas: Int
) {
    val attendancePercent = student.getAttendancePercent(totalAulas)
    val faltas = (totalAulas - student.presencesCount).coerceAtLeast(0)

    Card(
        colors = CardDefaults.cardColors(containerColor = CardBackground),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, InputBorder, RoundedCornerShape(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Surface(
                    shape = CircleShape,
                    color = EuroBlue.copy(alpha = 0.1f),
                    modifier = Modifier.size(40.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Filled.Person,
                            contentDescription = null,
                            tint = EuroBlue,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Text(
                        text = student.name,
                        color = TextDark,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )
                    if (student.email.isNotBlank()) {
                        Text(
                            text = student.email,
                            color = EuroBlue,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${student.presencesCount} de $totalAulas presenças ($faltas faltas)",
                        color = TextMuted,
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            Surface(
                color = when {
                    attendancePercent >= 85 -> Color(0xFFE8F5E9)
                    attendancePercent >= 75 -> Color(0xFFFFF8E1)
                    else -> Color(0xFFFFEBEE)
                },
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "$attendancePercent%",
                    color = when {
                        attendancePercent >= 85 -> Color(0xFF2E7D32)
                        attendancePercent >= 75 -> Color(0xFFF57F17)
                        else -> Color(0xFFC62828)
                    },
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StudentsScreenPreview() {
    StudentsScreen(turmaId = "turma_a")
}
