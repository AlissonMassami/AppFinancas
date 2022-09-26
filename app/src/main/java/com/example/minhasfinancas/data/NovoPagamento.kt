package com.example.minhasfinancas.data
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Pagamento(
    @PrimaryKey(autoGenerate = false)
    val mesPagamento : String,
    val rendaMensal: Double,
    val restanteParaDivisao:Double
)
