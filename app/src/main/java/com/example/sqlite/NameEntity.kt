package com.example.sqlite

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val password: String

)
