package com.chavez.patrick.LaboratorioCalificado03


import retrofit2.Response
import retrofit2.http.GET

interface TeacherApi {

    @GET("list/teacher")
    suspend fun getTeachers(): Response<TeacherListResponse>
}
