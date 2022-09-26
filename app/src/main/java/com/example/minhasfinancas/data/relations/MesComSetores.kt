package com.example.minhasfinancas.data.relations

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.minhasfinancas.data.Contas
import com.example.minhasfinancas.data.Pagamento


data class MesComSetores(
    @Embedded val pagamento: Pagamento,
    @Relation(
        parentColumn = "mesPagamento",
        entityColumn = "setorNome",
        associateBy = Junction(ContaPagamentoCrossRef::class)
    )
    val listaContas: List<Contas>
)
