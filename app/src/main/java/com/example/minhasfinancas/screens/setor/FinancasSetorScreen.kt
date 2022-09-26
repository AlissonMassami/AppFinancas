package com.example.minhasfinancas.screens.setor

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.minhasfinancas.components.SetoresItem
import com.example.minhasfinancas.data.Setores
import com.example.minhasfinancas.screens.contas.InputField

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FinancasSetorScreen(navController: NavController,
    viewModel : FinancasSetorViewModel= hiltViewModel()
    ){
    var setores = viewModel.setorList.collectAsState(initial = emptyList())

    val novoSetor = rememberSaveable{ mutableStateOf("") }
    val novaDivisao = rememberSaveable{ mutableStateOf("") }





    val valid = remember(novoSetor.value, novaDivisao.value){
        novoSetor.value.trim().isNotEmpty() && novaDivisao.value.trim().isNotEmpty()
    }
    
    val modifier = Modifier
        .height(250.dp)
        .background(MaterialTheme.colors.background)
    //.verticalScroll(rememberScrollState())
    
    
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()    ){
        Column(modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {
            Text(text = "SETORES", style = MaterialTheme.typography.h4, color = Color.Black, fontWeight = FontWeight.Bold)
            InputField(
                valueState = novoSetor,
                labelId = "Nome Setor",
                enabled = true,
            )
            InputField(
                valueState = novaDivisao,
                labelId = "Porcentagem",
                enabled = true,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                val valid2 : Double = CalcularSaldo(setores)
                Button(modifier = Modifier
                    .padding(3.dp),
                    shape = CircleShape,
                    onClick = {
                        if(valid && (novaDivisao.value.toDouble() <= valid2)){

                            viewModel.addSetor(novoSetor.value, novaDivisao.value)
                            novoSetor.value = ""
                            novaDivisao.value = ""
                        }else{
                            //TODO SHOW SNACK BAR DADOS INVALIDO
                        }

                    }) {
                    Text(text = "Add Conta", modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.body1 )

                }

                Text(text = "Não Alocado: ${CalcularSaldo(setores)}%")




                
            }
            
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {

                items(items = setores.value) {setor ->

                    SetoresItem(setores = setor, modifier = Modifier.fillMaxWidth(),
                        onClickDelete = {
                            viewModel.deleteSetor(setor)
                        },
                        onClickEdit = {

                        })


                    //Text(text = conta.contaNome)
                }
            }
        }
    }

}
@Composable
fun CalcularSaldo(setores: State<List<Setores>>):Double{
    var porcentagemTotal = 100.0
    setores.value.forEach{
        porcentagemTotal = porcentagemTotal - it.porcentagemSetor
    }
    return porcentagemTotal
}