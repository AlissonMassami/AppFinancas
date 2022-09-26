package com.example.minhasfinancas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Setores(
    @PrimaryKey(autoGenerate = false)
    val setorNome:String,
    val porcentagemSetor: Double,
    val saldoTotalSetor: Double
)

