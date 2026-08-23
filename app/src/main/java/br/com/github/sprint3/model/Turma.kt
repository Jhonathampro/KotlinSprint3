package br.com.github.sprint3.model

data class Turma(
    val id: String,
    val name: String,
    val course: String,
    val subject: String,
    val teacherName: String,
    val period: String,
    val totalAulas: Int = 20,
    val students: List<Student> = emptyList()
) {
    val studentCount: Int
        get() = students.size

    fun getEngagementPercent(): Int {
        if (students.isEmpty() || totalAulas <= 0) return 0
        val sumPercents = students.sumOf { it.getAttendancePercent(totalAulas) }
        return sumPercents / students.size
    }
}
