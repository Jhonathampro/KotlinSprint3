package br.com.github.sprint3.model

data class Student(
    val id: String,
    val name: String,
    val email: String = "",
    val presencesCount: Int = 0,
    val totalEligibleAulas: Int? = null,
    val attendanceStatus: AttendanceStatus = AttendanceStatus.UNMARKED
) {
    fun getEffectiveTotalAulas(turmaTotalAulas: Int): Int {
        return totalEligibleAulas ?: turmaTotalAulas
    }

    fun getAttendancePercent(turmaTotalAulas: Int): Int {
        val total = getEffectiveTotalAulas(turmaTotalAulas)
        if (total <= 0) return 100
        return ((presencesCount.toDouble() / total) * 100).toInt().coerceIn(0, 100)
    }

    fun getAbsencesCount(turmaTotalAulas: Int): Int {
        val total = getEffectiveTotalAulas(turmaTotalAulas)
        if (total <= 0) return 0
        return (total - presencesCount).coerceAtLeast(0)
    }
}
