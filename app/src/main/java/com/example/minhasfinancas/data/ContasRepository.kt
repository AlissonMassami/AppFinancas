package com.example.minhasfinancas.data

import com.example.minhasfinancas.data.Contas
import com.example.minhasfinancas.data.Setores
import kotlinx.coroutines.flow.Flow

interface ContasRepository {

    suspend fun insertConta(contas: Contas)
    suspend fun deleteConta(contas: Contas)
    fun buscarTodasContas(): Flow<List<Contas>>
    fun buscarConta(conta:String): Flow<Contas>


    suspend fun insertSetor(setores: Setores)
    suspend fun deleteSetor(setores: Setores)
    fun buscarTodosSetores(): Flow<List<Setores>>
    fun buscarSetor(setor:String): Flow<Setores>

}