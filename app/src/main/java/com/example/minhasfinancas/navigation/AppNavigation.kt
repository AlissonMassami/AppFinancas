package com.example.minhasfinancas.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.minhasfinancas.screens.contas.FinancasContasScreen
import com.example.minhasfinancas.screens.contas.FinancasContasViewModel
import com.example.minhasfinancas.screens.extra.FinancasExtraScreen
import com.example.minhasfinancas.screens.home.FinancasHomeScreen
import com.example.minhasfinancas.screens.pagamento.FinancasPagamentoScreen
import com.example.minhasfinancas.screens.setor.FinancasSetorScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.name){
        composable(AppScreens.HomeScreen.name){
            FinancasHomeScreen(navController = navController)
        }
        composable(AppScreens.ContasScreen.name){
            val contaViewModel = hiltViewModel<FinancasContasViewModel>()
            FinancasContasScreen(navController = navController, viewModel = contaViewModel)
        }
        composable(AppScreens.ExtraScreen.name){
            FinancasExtraScreen(navController = navController)
        }
        composable(AppScreens.PagamentoScreen.name){
            FinancasPagamentoScreen(navController = navController)
        }
        composable(AppScreens.SetorScreen.name){
            FinancasSetorScreen(navController = navController)
        }
    }


}