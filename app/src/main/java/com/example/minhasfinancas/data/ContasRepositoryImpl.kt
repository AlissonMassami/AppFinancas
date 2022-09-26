package com.example.minhasfinancas.data

import com.example.financasv2.data.ContasDAO
import kotlinx.coroutines.flow.Flow

class ContasRepositoryImpl(
    private val dao: ContasDAO
) : ContasRepository {
    override suspend fun insertConta(contas: Contas) {
        dao.insertConta(contas)
    }

    override suspend fun deleteConta(contas: Contas) {
        dao.deleteConta(contas)
    }

    override suspend fun deleteSetor(setores: Setores) {
        dao.deleteSetor(setores)
    }

    override fun buscarTodasContas(): Flow<List<Contas>> {
        return dao.buscarTodasContas()
    }

    override fun buscarConta(conta: String): Flow<Contas> {
        return dao.buscarConta(conta)
    }

    override suspend fun insertSetor(setores: Setores) {
        dao.insertSetor(setores)
    }

    override fun buscarTodosSetores(): Flow<List<Setores>> {
        return dao.buscarTodosSetores()
    }

    override fun buscarSetor(setor: String): Flow<Setores> {
        return dao.buscarSetor(setor)
    }
}