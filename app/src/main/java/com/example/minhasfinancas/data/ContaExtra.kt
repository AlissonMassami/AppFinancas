package com.example.minhasfinancas.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class ContasExtras(
    @PrimaryKey(autoGenerate = false)
    val descricaoConta: String,
    val setorContaExtra:String,
    val valorContaExtra:Double,
    val dataContaExtra:String

)

