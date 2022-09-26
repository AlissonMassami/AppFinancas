package com.example.minhasfinancas.data.relations

import androidx.room.Entity

@Entity(primaryKeys = ["mesPagamento", "setorNome"])
data class PagamentoDivisaoSetorCrossRef(
    val mesPagamento:String,
    val setorNome: String,
    val valorRestaste: String
)
