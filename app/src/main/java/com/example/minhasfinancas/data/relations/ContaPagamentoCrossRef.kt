package com.example.minhasfinancas.data.relations

import androidx.room.Entity

@Entity(primaryKeys = ["contaNome","mesPagamento"])
data class ContaPagamentoCrossRef(
    val contaNome: String,
    val mesPagamento:String,
    val valorConta: String
)
