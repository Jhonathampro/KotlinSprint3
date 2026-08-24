package br.com.github.sprint3.data

import androidx.compose.runtime.mutableStateListOf
import br.com.github.sprint3.model.AttendanceStatus
import br.com.github.sprint3.model.Student
import br.com.github.sprint3.model.Turma

val mockStudentsTurmaA = listOf(
    Student(id = "std_1", name = "Silvio Toshiaki Yokoyama", email = "silviotoshiakiyokoyama@eurofarma.com.br", presencesCount = 19, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_2", name = "Lucas Vieira Pasqualini", email = "lucasvieirapasqualini@eurofarma.com.br", presencesCount = 16, attendanceStatus = AttendanceStatus.AUSENTE),
    Student(id = "std_3", name = "Pietro de Paula Nascimento", email = "pietrodepaulanascimento@eurofarma.com.br", presencesCount = 18, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_4", name = "Jhonatham Jesus", email = "jhonathamjesus@eurofarma.com.br", presencesCount = 20, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_5", name = "Victor Baptista Andrade de Sousa", email = "victorbaptistaandradedesousa@eurofarma.com.br", presencesCount = 15, attendanceStatus = AttendanceStatus.FALTA_JUSTIFICADA),
    Student(id = "std_6", name = "Gabriel Fernando Lima", email = "gabrielfernandolima@eurofarma.com.br", presencesCount = 17, attendanceStatus = AttendanceStatus.PRESENTE)
)

val mockStudentsTurmaB = listOf(
    Student(id = "std_7", name = "Beatriz Oliveira Santos", email = "beatrizoliveirasantos@eurofarma.com.br", presencesCount = 18, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_8", name = "Camila Rodriguez Silva", email = "camilarodriguezsilva@eurofarma.com.br", presencesCount = 14, attendanceStatus = AttendanceStatus.AUSENTE),
    Student(id = "std_9", name = "Matheus Henrique Almeida", email = "matheushenriquealmeida@eurofarma.com.br", presencesCount = 20, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_10", name = "Larissa Rocha Martins", email = "larissarochamartins@eurofarma.com.br", presencesCount = 17, attendanceStatus = AttendanceStatus.FALTA_JUSTIFICADA),
    Student(id = "std_11", name = "Felipe Augusto Barbosa", email = "felipeaugustobarbosa@eurofarma.com.br", presencesCount = 19, attendanceStatus = AttendanceStatus.PRESENTE)
)

val mockStudentsTurmaC = listOf(
    Student(id = "std_12", name = "Isabela Cristina Carvalho", email = "isabelacristinacarvalho@eurofarma.com.br", presencesCount = 18, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_13", name = "Thiago Emmanuel Costa", email = "thiagoemmanuelcosta@eurofarma.com.br", presencesCount = 15, attendanceStatus = AttendanceStatus.AUSENTE),
    Student(id = "std_14", name = "Fernanda Ramos Pereira", email = "fernandaramospereira@eurofarma.com.br", presencesCount = 17, attendanceStatus = AttendanceStatus.UNMARKED),
    Student(id = "std_15", name = "Rodrigo Mendes Duarte", email = "rodrigomendesduarte@eurofarma.com.br", presencesCount = 16, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_16", name = "Aline Ribeiro Fernandes", email = "alineribeirofernandes@eurofarma.com.br", presencesCount = 19, attendanceStatus = AttendanceStatus.PRESENTE)
)

val mockStudentsTurmaD = listOf(
    Student(id = "std_17", name = "Bruno Henrique Souza", email = "brunohenriquesouza@eurofarma.com.br", presencesCount = 22, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_18", name = "Carolina Nunes Castro", email = "carolinanunescastro@eurofarma.com.br", presencesCount = 20, attendanceStatus = AttendanceStatus.FALTA_JUSTIFICADA),
    Student(id = "std_19", name = "Diego Armando Silva", email = "diegoarmandosilva@eurofarma.com.br", presencesCount = 25, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_20", name = "Eduardo Vinicius Lima", email = "eduardoviniciuslima@eurofarma.com.br", presencesCount = 21, attendanceStatus = AttendanceStatus.PRESENTE)
)

val mockStudentsTurmaE = listOf(
    Student(id = "std_21", name = "Gabriela Martins Santos", email = "gabrielamartinssantos@eurofarma.com.br", presencesCount = 18, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_22", name = "Hugo Leonardo Oliveira", email = "hugoleonardooliveira@eurofarma.com.br", presencesCount = 15, attendanceStatus = AttendanceStatus.AUSENTE),
    Student(id = "std_23", name = "Luana Cristina Cardozo", email = "luanacristinacardozo@eurofarma.com.br", presencesCount = 14, attendanceStatus = AttendanceStatus.UNMARKED),
    Student(id = "std_24", name = "Marcos Vinicius Pereira", email = "marcosviniciuspereira@eurofarma.com.br", presencesCount = 19, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_25", name = "Natalia Ramos Ferreira", email = "nataliaramosferreira@eurofarma.com.br", presencesCount = 17, attendanceStatus = AttendanceStatus.PRESENTE)
)

val mockTurmas = mutableStateListOf(
    Turma(
        id = "turma_a",
        name = "Turma A",
        course = "Análise e Desenvolvimento de Sistemas — 3º Semestre",
        subject = "Desenvolvimento Mobile & APIs",
        teacherName = "Carlos Eduardo Mendes",
        period = "Turmas da tarde",
        totalAulas = 20,
        students = mockStudentsTurmaA
    ),
    Turma(
        id = "turma_b",
        name = "Turma B",
        course = "Engenharia de Software — 2º Semestre",
        subject = "Arquitetura de Software & Clean Code",
        teacherName = "Mariana Alves Costa",
        period = "Turmas da tarde",
        totalAulas = 20,
        students = mockStudentsTurmaB
    ),
    Turma(
        id = "turma_c",
        name = "Turma C",
        course = "Desenvolvimento Web — 1º Semestre",
        subject = "Programação Orientada a Objetos",
        teacherName = "Rafael Henrique Souza",
        period = "Turmas da tarde",
        totalAulas = 20,
        students = mockStudentsTurmaC
    ),
    Turma(
        id = "turma_d",
        name = "Turma D",
        course = "Ciência de Dados — 4º Semestre",
        subject = "Inteligência Artificial & Machine Learning",
        teacherName = "Carlos Eduardo Mendes",
        period = "Turmas da manhã",
        totalAulas = 25,
        students = mockStudentsTurmaD
    ),
    Turma(
        id = "turma_e",
        name = "Turma E",
        course = "Banco de Dados — 2º Semestre",
        subject = "Modelagem Relacional & NoSQL",
        teacherName = "Mariana Alves Costa",
        period = "Turmas da noite",
        totalAulas = 20,
        students = mockStudentsTurmaE
    )
)

fun addStudentToTurma(turmaId: String, studentName: String, email: String = ""): Boolean {
    val index = mockTurmas.indexOfFirst { it.id == turmaId }
    if (index == -1) return false
    val targetTurma = mockTurmas[index]
    val newStudent = Student(
        id = "std_${System.currentTimeMillis()}",
        name = studentName,
        email = email,
        presencesCount = 0,
        attendanceStatus = AttendanceStatus.UNMARKED
    )
    val updatedStudents = targetTurma.students + newStudent
    mockTurmas[index] = targetTurma.copy(students = updatedStudents)
    return true
}

