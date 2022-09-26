package com.example.minhasfinancas.screens.contas

import androidx.compose.runtime.MutableState

sealed class FincancasContasEvent{
    data class OnContaChange(val contaInput: String) : FincancasContasEvent()
    data class OnValorChange(val valorInput: String) : FincancasContasEvent()
    object onSaveContaClick : FincancasContasEvent()

}
