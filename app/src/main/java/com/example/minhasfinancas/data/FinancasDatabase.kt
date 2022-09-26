package com.example.minhasfinancas.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.financasv2.data.*
import com.example.minhasfinancas.data.relations.ContaPagamentoCrossRef
import com.example.minhasfinancas.data.relations.PagamentoDivisaoSetorCrossRef

@Database(entities = [Contas::class,
    Setores::class,
    ContasExtras::class,
    Pagamento::class,
    ContaPagamentoCrossRef::class,
    PagamentoDivisaoSetorCrossRef::class], version = 1)
abstract class FinancasDatabase: RoomDatabase() {

    abstract val dao: ContasDAO
}