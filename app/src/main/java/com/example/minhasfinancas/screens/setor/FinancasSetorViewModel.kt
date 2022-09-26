package com.example.minhasfinancas.screens.setor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minhasfinancas.data.Contas
import com.example.minhasfinancas.data.ContasRepository
import com.example.minhasfinancas.data.Setores
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinancasSetorViewModel @Inject constructor(
    private val repository: ContasRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {


    val setorList = repository.buscarTodosSetores()



    fun addSetor( setor : String, porcentagem : String){
        viewModelScope.launch {
            repository.insertSetor(Setores(setorNome = setor, porcentagemSetor = porcentagem.toDouble(), saldoTotalSetor = 0.0))
        }
    }
    fun deleteSetor( setores: Setores){
        viewModelScope.launch {
            repository.deleteSetor(setores)
        }
    }

}