package br.com.github.sprint3.data

import androidx.compose.runtime.mutableStateListOf
import br.com.github.sprint3.model.AttendanceStatus
import br.com.github.sprint3.model.Student
import br.com.github.sprint3.model.Turma

data class StudentAbsenceInfo(
    val studentName: String,
    val absencesCount: Int,
    val turmaName: String
)

data class PeriodEngagementInfo(
    val period: String,
    val engagementPercent: Int,
    val totalTurmas: Int
)

val mockStudentsTurma1 = listOf(
    Student(id = "std_1", name = "Silvio Toshiaki Yokoyama", email = "silviotoshiakiyokoyama@eurofarma.com.br", presencesCount = 12, attendanceStatus = AttendanceStatus.AUSENTE), // 8 faltas
    Student(id = "std_2", name = "Lucas Vieira Pasqualini", email = "lucasvieirapasqualini@eurofarma.com.br", presencesCount = 14, attendanceStatus = AttendanceStatus.AUSENTE), // 6 faltas
    Student(id = "std_3", name = "Pietro de Paula Nascimento", email = "pietrodepaulanascimento@eurofarma.com.br", presencesCount = 18, attendanceStatus = AttendanceStatus.PRESENTE), // 2 faltas
    Student(id = "std_4", name = "Jhonatham Jesus", email = "jhonathamjesus@eurofarma.com.br", presencesCount = 20, attendanceStatus = AttendanceStatus.PRESENTE), // 0 faltas
    Student(id = "std_5", name = "Victor Baptista Andrade", email = "victorbaptistaandrade@eurofarma.com.br", presencesCount = 15, attendanceStatus = AttendanceStatus.FALTA_JUSTIFICADA) // 5 faltas
)

val mockStudentsTurma2 = listOf(
    Student(id = "std_6", name = "Beatriz Oliveira Santos", email = "beatrizoliveirasantos@eurofarma.com.br", presencesCount = 13, attendanceStatus = AttendanceStatus.AUSENTE), // 7 faltas
    Student(id = "std_7", name = "Camila Rodriguez Silva", email = "camilarodriguezsilva@eurofarma.com.br", presencesCount = 14, attendanceStatus = AttendanceStatus.FALTA_JUSTIFICADA), // 6 faltas
    Student(id = "std_8", name = "Matheus Henrique Almeida", email = "matheushenriquealmeida@eurofarma.com.br", presencesCount = 19, attendanceStatus = AttendanceStatus.PRESENTE), // 1 falta
    Student(id = "std_9", name = "Larissa Rocha Martins", email = "larissarochamartins@eurofarma.com.br", presencesCount = 16, attendanceStatus = AttendanceStatus.AUSENTE) // 4 faltas
)

val mockStudentsTurma3 = listOf(
    Student(id = "std_10", name = "Thiago Emmanuel Costa", email = "thiagoemmanuelcosta@eurofarma.com.br", presencesCount = 11, attendanceStatus = AttendanceStatus.AUSENTE), // 9 faltas
    Student(id = "std_11", name = "Isabela Cristina Carvalho", email = "isabelacristinacarvalho@eurofarma.com.br", presencesCount = 17, attendanceStatus = AttendanceStatus.PRESENTE), // 3 faltas
    Student(id = "std_12", name = "Fernanda Ramos Pereira", email = "fernandaramospereira@eurofarma.com.br", presencesCount = 15, attendanceStatus = AttendanceStatus.FALTA_JUSTIFICADA), // 5 faltas
    Student(id = "std_13", name = "Rodrigo Mendes Duarte", email = "rodrigomendesduarte@eurofarma.com.br", presencesCount = 18, attendanceStatus = AttendanceStatus.PRESENTE) // 2 faltas
)

val mockStudentsTurma4 = listOf(
    Student(id = "std_14", name = "Hugo Leonardo Oliveira", email = "hugoleonardooliveira@eurofarma.com.br", presencesCount = 10, attendanceStatus = AttendanceStatus.AUSENTE), // 10 faltas
    Student(id = "std_15", name = "Bruno Henrique Souza", email = "brunohenriquesouza@eurofarma.com.br", presencesCount = 16, attendanceStatus = AttendanceStatus.PRESENTE), // 4 faltas
    Student(id = "std_16", name = "Carolina Nunes Castro", email = "carolinanunescastro@eurofarma.com.br", presencesCount = 15, attendanceStatus = AttendanceStatus.FALTA_JUSTIFICADA), // 5 faltas
    Student(id = "std_17", name = "Diego Armando Silva", email = "diegoarmandosilva@eurofarma.com.br", presencesCount = 19, attendanceStatus = AttendanceStatus.PRESENTE) // 1 falta
)

val mockStudentsTurma5 = listOf(
    Student(id = "std_18", name = "Luana Cristina Cardozo", email = "luanacristinacardozo@eurofarma.com.br", presencesCount = 13, attendanceStatus = AttendanceStatus.AUSENTE), // 7 faltas
    Student(id = "std_19", name = "Gabriela Martins Santos", email = "gabrielamartinssantos@eurofarma.com.br", presencesCount = 18, attendanceStatus = AttendanceStatus.PRESENTE), // 2 faltas
    Student(id = "std_20", name = "Marcos Vinicius Pereira", email = "marcosviniciuspereira@eurofarma.com.br", presencesCount = 15, attendanceStatus = AttendanceStatus.FALTA_JUSTIFICADA), // 5 faltas
    Student(id = "std_21", name = "Natalia Ramos Ferreira", email = "nataliaramosferreira@eurofarma.com.br", presencesCount = 17, attendanceStatus = AttendanceStatus.PRESENTE) // 3 faltas
)

val mockStudentsTurma6 = listOf(
    Student(id = "std_22", name = "Gabriel Fernando Lima", email = "gabrielfernandolima@eurofarma.com.br", presencesCount = 12, attendanceStatus = AttendanceStatus.AUSENTE), // 8 faltas
    Student(id = "std_23", name = "Aline Ribeiro Fernandes", email = "alineribeirofernandes@eurofarma.com.br", presencesCount = 16, attendanceStatus = AttendanceStatus.PRESENTE), // 4 faltas
    Student(id = "std_24", name = "Felipe Augusto Barbosa", email = "felipeaugustobarbosa@eurofarma.com.br", presencesCount = 19, attendanceStatus = AttendanceStatus.PRESENTE) // 1 falta
)

val mockStudentsTurma7 = listOf(
    Student(id = "std_25", name = "Eduardo Vinicius Lima", email = "eduardoviniciuslima@eurofarma.com.br", presencesCount = 14, attendanceStatus = AttendanceStatus.AUSENTE), // 6 faltas
    Student(id = "std_26", name = "Renata Cristina Paiva", email = "renatacristinapaiva@eurofarma.com.br", presencesCount = 18, attendanceStatus = AttendanceStatus.PRESENTE) // 2 faltas
)

val mockTurmas = mutableStateListOf(
    Turma(
        id = "turma_1",
        name = "Programming and Database Management",
        course = "Programming and Database Management",
        subject = "Database Administration & SQL",
        teacherName = "Carlos Eduardo Mendes",
        period = "Manhã",
        totalAulas = 20,
        students = mockStudentsTurma1
    ),
    Turma(
        id = "turma_2",
        name = "Android Kotlin Developer",
        course = "Android Kotlin Developer",
        subject = "Jetpack Compose & Mobile Architecture",
        teacherName = "Mariana Alves Costa",
        period = "Manhã",
        totalAulas = 20,
        students = mockStudentsTurma2
    ),
    Turma(
        id = "turma_3",
        name = "Desenvolvimento Cross Platform",
        course = "Desenvolvimento Cross Platform",
        subject = "Flutter & Multiplatform Apps",
        teacherName = "Rafael Henrique Souza",
        period = "Tarde",
        totalAulas = 20,
        students = mockStudentsTurma3
    ),
    Turma(
        id = "turma_4",
        name = "MICROSERVICE AND WEB ENGINEERING & IT SERVICES",
        course = "MICROSERVICE AND WEB ENGINEERING & IT SERVICES",
        subject = "Spring Boot & Distributed Systems",
        teacherName = "Carlos Eduardo Mendes",
        period = "Tarde",
        totalAulas = 20,
        students = mockStudentsTurma4
    ),
    Turma(
        id = "turma_5",
        name = "Network Management and Monitoring",
        course = "Network Management and Monitoring",
        subject = "Cloud Networks & Telemetry",
        teacherName = "Mariana Alves Costa",
        period = "Noite",
        totalAulas = 20,
        students = mockStudentsTurma5
    ),
    Turma(
        id = "turma_6",
        name = "Operating System Tuning and Cognation",
        course = "Operating System Tuning and Cognation",
        subject = "Linux Kernel & System Optimization",
        teacherName = "Rafael Henrique Souza",
        period = "Noite",
        totalAulas = 20,
        students = mockStudentsTurma6
    ),
    Turma(
        id = "turma_7",
        name = "SOFTWARE PROJECTS QUALITY ASSURANCE",
        course = "SOFTWARE PROJECTS QUALITY ASSURANCE",
        subject = "Automated Testing & QA Delivery",
        teacherName = "Carlos Eduardo Mendes",
        period = "Manhã",
        totalAulas = 20,
        students = mockStudentsTurma7
    )
)

fun getTopStudentsWithAbsences(limit: Int = 10): List<StudentAbsenceInfo> {
    return mockTurmas.flatMap { turma ->
        turma.students.map { student ->
            StudentAbsenceInfo(
                studentName = student.name,
                absencesCount = student.getAbsencesCount(turma.totalAulas),
                turmaName = turma.name
            )
        }
    }
    .filter { it.absencesCount > 0 }
    .sortedByDescending { it.absencesCount }
    .take(limit)
}

fun getEngagementByPeriod(): List<PeriodEngagementInfo> {
    val periods = listOf("Manhã", "Tarde", "Noite")
    return periods.map { periodName ->
        val turmasInPeriod = mockTurmas.filter { it.period.contains(periodName, ignoreCase = true) }
        val avgEng = if (turmasInPeriod.isNotEmpty()) {
            turmasInPeriod.map { it.getEngagementPercent() }.average().toInt()
        } else 0
        PeriodEngagementInfo(
            period = periodName,
            engagementPercent = avgEng,
            totalTurmas = turmasInPeriod.size
        )
    }
}

fun addStudentToTurma(turmaId: String, studentName: String, email: String = ""): Boolean {
    val index = mockTurmas.indexOfFirst { it.id == turmaId }
    if (index == -1) return false
    val targetTurma = mockTurmas[index]
    val newStudent = Student(
        id = "std_${System.currentTimeMillis()}",
        name = studentName,
        email = email,
        presencesCount = 0,
        totalEligibleAulas = 0,
        attendanceStatus = AttendanceStatus.UNMARKED
    )
    val updatedStudents = targetTurma.students + newStudent
    mockTurmas[index] = targetTurma.copy(students = updatedStudents)
    return true
}

fun addTurma(
    name: String,
    period: String,
    subject: String = "",
    teacherName: String = ""
): Turma {
    val newId = "turma_${System.currentTimeMillis()}"
    val effectiveSubject = subject.ifBlank { "Desenvolvimento & Tecnologia" }
    val effectiveTeacher = teacherName.ifBlank { "Professor Responsável" }
    val newTurma = Turma(
        id = newId,
        name = name,
        course = name,
        subject = effectiveSubject,
        teacherName = effectiveTeacher,
        period = period,
        totalAulas = 20,
        students = emptyList()
    )
    mockTurmas.add(newTurma)
    return newTurma
}

