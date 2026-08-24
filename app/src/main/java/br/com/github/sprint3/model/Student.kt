package br.com.github.sprint3.model

data class Student(
    val id: String,
    val name: String,
    val email: String = "",
    val presencesCount: Int = 0,
    val attendanceStatus: AttendanceStatus = AttendanceStatus.UNMARKED
) {
    fun getAttendancePercent(totalAulas: Int): Int {
        if (totalAulas <= 0) return 0
        return ((presencesCount.toDouble() / totalAulas) * 100).toInt()
    }
}
