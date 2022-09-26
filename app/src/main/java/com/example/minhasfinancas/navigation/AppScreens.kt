package com.example.minhasfinancas.navigation

import com.example.minhasfinancas.screens.contas.FinancasContasScreen

enum class AppScreens {
    ContasScreen,
    ExtraScreen,
    HomeScreen,
    PagamentoScreen,
    SetorScreen;
    companion object {
        fun fromRoute(route:String?): AppScreens
        = when (route?.substringBefore(delimiter = "/")){
            ContasScreen.name -> ContasScreen
            ExtraScreen.name -> ExtraScreen
            HomeScreen.name -> HomeScreen
            PagamentoScreen.name -> PagamentoScreen
            SetorScreen.name -> SetorScreen
            null -> HomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")

        }

    }

}