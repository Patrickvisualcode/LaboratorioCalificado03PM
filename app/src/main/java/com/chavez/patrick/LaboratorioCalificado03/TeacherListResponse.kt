package com.chavez.patrick.LaboratorioCalificado03

data class TeacherListResponse(
    val count: Int,
    val next: String?,
    val teachers: List<TeacherResponse>
)
