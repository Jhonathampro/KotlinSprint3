package br.com.github.sprint3.data

import br.com.github.sprint3.model.AttendanceStatus
import br.com.github.sprint3.model.Student
import br.com.github.sprint3.model.Turma

val mockStudentsTurmaA = listOf(
    Student(id = "std_1", name = "Silvio Toshiaki Yokoyama", presencesCount = 19, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_2", name = "Lucas Vieira Pasqualini", presencesCount = 16, attendanceStatus = AttendanceStatus.AUSENTE),
    Student(id = "std_3", name = "Pietro de Paula Nascimento", presencesCount = 18, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_4", name = "Jhonatham Jesus", presencesCount = 20, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_5", name = "Victor Baptista Andrade de Sousa", presencesCount = 15, attendanceStatus = AttendanceStatus.FALTA_JUSTIFICADA),
    Student(id = "std_6", name = "Gabriel Fernando Lima", presencesCount = 17, attendanceStatus = AttendanceStatus.PRESENTE)
)

val mockStudentsTurmaB = listOf(
    Student(id = "std_7", name = "Beatriz Oliveira Santos", presencesCount = 18, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_8", name = "Camila Rodriguez Silva", presencesCount = 14, attendanceStatus = AttendanceStatus.AUSENTE),
    Student(id = "std_9", name = "Matheus Henrique Almeida", presencesCount = 20, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_10", name = "Larissa Rocha Martins", presencesCount = 17, attendanceStatus = AttendanceStatus.FALTA_JUSTIFICADA),
    Student(id = "std_11", name = "Felipe Augusto Barbosa", presencesCount = 19, attendanceStatus = AttendanceStatus.PRESENTE)
)

val mockStudentsTurmaC = listOf(
    Student(id = "std_12", name = "Isabela Cristina Carvalho", presencesCount = 18, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_13", name = "Thiago Emmanuel Costa", presencesCount = 15, attendanceStatus = AttendanceStatus.AUSENTE),
    Student(id = "std_14", name = "Fernanda Ramos Pereira", presencesCount = 17, attendanceStatus = AttendanceStatus.UNMARKED),
    Student(id = "std_15", name = "Rodrigo Mendes Duarte", presencesCount = 16, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_16", name = "Aline Ribeiro Fernandes", presencesCount = 19, attendanceStatus = AttendanceStatus.PRESENTE)
)

val mockStudentsTurmaD = listOf(
    Student(id = "std_17", name = "Bruno Henrique Souza", presencesCount = 22, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_18", name = "Carolina Nunes Castro", presencesCount = 20, attendanceStatus = AttendanceStatus.FALTA_JUSTIFICADA),
    Student(id = "std_19", name = "Diego Armando Silva", presencesCount = 25, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_20", name = "Eduardo Vinicius Lima", presencesCount = 21, attendanceStatus = AttendanceStatus.PRESENTE)
)

val mockStudentsTurmaE = listOf(
    Student(id = "std_21", name = "Gabriela Martins Santos", presencesCount = 18, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_22", name = "Hugo Leonardo Oliveira", presencesCount = 15, attendanceStatus = AttendanceStatus.AUSENTE),
    Student(id = "std_23", name = "Luana Cristina Cardozo", presencesCount = 14, attendanceStatus = AttendanceStatus.UNMARKED),
    Student(id = "std_24", name = "Marcos Vinicius Pereira", presencesCount = 19, attendanceStatus = AttendanceStatus.PRESENTE),
    Student(id = "std_25", name = "Natalia Ramos Ferreira", presencesCount = 17, attendanceStatus = AttendanceStatus.PRESENTE)
)

val mockTurmas = listOf(
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
