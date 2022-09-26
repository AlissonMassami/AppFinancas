package com.example.financasv2.data

import androidx.room.*
import com.example.minhasfinancas.data.Contas
import com.example.minhasfinancas.data.ContasExtras
import com.example.minhasfinancas.data.Pagamento
import com.example.minhasfinancas.data.Setores
import com.example.minhasfinancas.data.relations.ContaPagamentoCrossRef
import kotlinx.coroutines.flow.Flow

@Dao
interface ContasDAO {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPagamento(pagamento: Pagamento)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContaExtra(contasExtras: ContasExtras)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContaPagamentoCrossRef(crossRef: ContaPagamentoCrossRef)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertConta(contas: Contas)
    @Delete
    suspend fun deleteConta(contas: Contas)
    @Query("SELECT * FROM Contas")
    fun buscarTodasContas():Flow<List<Contas>>
    @Query("SELECT * FROM Contas WHERE contaNome= :conta")
    fun buscarConta(conta:String):Flow<Contas>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSetor(setores: Setores)
    @Delete
    suspend fun deleteSetor(setores: Setores)
    @Query("SELECT * FROM Setores")
    fun buscarTodosSetores():Flow<List<Setores>>
    @Query("SELECT * FROM Setores WHERE setorNome= :setor")
    fun buscarSetor(setor:String):Flow<Setores>

}