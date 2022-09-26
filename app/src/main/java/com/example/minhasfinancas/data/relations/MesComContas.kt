package com.example.minhasfinancas.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.minhasfinancas.data.Pagamento
import com.example.minhasfinancas.data.Setores



data class MesComContas(
    @Embedded val pagamento: Pagamento,
    @Relation(
        parentColumn = "mesPagamento",
        entityColumn = "contaNome",
        associateBy = Junction(PagamentoDivisaoSetorCrossRef::class)
    )
    val listaSetores: List<Setores>
)
