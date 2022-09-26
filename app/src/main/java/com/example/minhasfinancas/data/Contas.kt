package com.example.minhasfinancas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contas(
    @PrimaryKey(autoGenerate = false)
    var contaNome:String,
    var valorConta:Double,
    var isDone:Boolean = false
)

