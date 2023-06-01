package ru.mirea.shumikhin.employeedb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import ru.mirea.shumikhin.employeedb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db: AppDatabase = (application as App).getDatabase() ?: return
        val employeeDao = db.employeeDao()
        var employee: Employee? = Employee()
        employee!!.id = 1
        employee!!.name = "John Smith"
        employee!!.salary = 10000
        // запись сотрудников в базу
        employeeDao!!.insert(employee)

        // Загрузка всех работников
        val employees = employeeDao!!.all

        // Получение определенного работника с id = 1
        employee = employeeDao!!.getById(1)

        // Обновление полей объекта
        employee!!.salary = 20000
        employeeDao!!.update(employee)
        Log.d(this::class.simpleName, employee!!.name + " " + employee!!.salary)
    }
}