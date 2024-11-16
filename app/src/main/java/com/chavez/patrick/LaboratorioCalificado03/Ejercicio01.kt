package com.chavez.patrick.LaboratorioCalificado03

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.chavez.patrick.laboratoriocalficado03.R
import com.chavez.patrick.laboratoriocalficado03.databinding.ActivityEjercicio01Binding
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class Ejercicio01 : AppCompatActivity() {

    private lateinit var binding: ActivityEjercicio01Binding
    private val teacherApi: TeacherApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TeacherApi::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        getTeachers()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewTeachers.layoutManager = LinearLayoutManager(this)
    }

    private fun getTeachers() {
        lifecycleScope.launch {
            try {

                val response = teacherApi.getTeachers()
                if (response.isSuccessful) {

                    val teachers = response.body()?.teachers ?: emptyList()
                    binding.recyclerViewTeachers.adapter = TeacherAdapter(teachers)
                } else {

                    showError(getString(R.string.error_loading_teachers))
                }
            } catch (e: Exception) {

                showError(getString(R.string.error_request, e.message))
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
