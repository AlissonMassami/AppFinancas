package com.example.minhasfinancas.screens.contas

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.minhasfinancas.data.Contas
import com.example.minhasfinancas.data.ContasRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinancasContasViewModel @Inject constructor(
    private val repository: ContasRepository,
    savedStateHandle: SavedStateHandle
    ): ViewModel() {

    val contasList = repository.buscarTodasContas()

    var conta by mutableStateOf<Contas?>(null)
        private set
    var novaConta = mutableStateOf("")
        private set
    var novoValor = mutableStateOf("")
        private set

    init {

    }

    fun addNovaConta( conta : String, valor : String){
        viewModelScope.launch {
            repository.insertConta(Contas(contaNome = conta, valorConta = valor.toDouble()))
        }
    }
    fun deleteConta( conta : String, valor : String){
        viewModelScope.launch {
            repository.deleteConta(Contas(contaNome = conta, valorConta = valor.toDouble()))
        }
    }


}