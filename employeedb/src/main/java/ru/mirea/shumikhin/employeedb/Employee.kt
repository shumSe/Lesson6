package ru.mirea.shumikhin.employeedb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Employee (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String? = null,
    var salary: Int = 0
)