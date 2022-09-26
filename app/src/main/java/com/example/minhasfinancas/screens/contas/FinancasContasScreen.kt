package com.example.minhasfinancas.screens.contas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.minhasfinancas.components.ContasItem

//NavController(context = LocalContext.current   params para usar preview
@OptIn(ExperimentalComposeUiApi::class)

@Composable
fun FinancasContasScreen(
        navController: NavController,
        viewModel: FinancasContasViewModel = hiltViewModel(),
                ) {
    //lista FLOW precisa ser coletada como STATE em compose
    var contas = viewModel.contasList.collectAsState(initial = emptyList())
    val scaffoldState = rememberScaffoldState()
    val novaConta = rememberSaveable{mutableStateOf("")}
    val novoValor = rememberSaveable{mutableStateOf("")}

    val editConta = rememberSaveable{mutableStateOf("")}
    val editValor = rememberSaveable{mutableStateOf("")}

    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(novaConta.value, novoValor.value){
        novaConta.value.trim().isNotEmpty() && novoValor.value.trim().isNotEmpty()
    }
    var inputDialogState = remember {
        mutableStateOf(false)
    }

    val modifier = Modifier
        .height(250.dp)
        .background(MaterialTheme.colors.background)
        //.verticalScroll(rememberScrollState())
    if(inputDialogState.value) {
        ShowEditDialog(
            conta = editConta.value,
            valor = editValor.value,
            inputDialog = inputDialogState,
            viewmodel = viewModel)
    }




    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()    ){
        Column(modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            ) {
            Text(text = "CONTAS", style = MaterialTheme.typography.h4, color = Color.Black, fontWeight = FontWeight.Bold)
            InputField(
                valueState = novaConta,
                labelId = "Nome Conta",
                enabled = true,
                )
            InputField(
                valueState = novoValor,
                labelId = "Valor Conta",
                enabled = true,
            )
            Button(modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(),
                shape = CircleShape,
                onClick = {
                    if(valid){
                        viewModel.addNovaConta(novaConta.value, novoValor.value)
                        novaConta.value = ""
                        novoValor.value = ""
                    }
                 }) {
                Text(text = "Add Conta", modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.body1 )

            }
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
            ) {

                items(items = contas.value) { conta ->
                    ContasItem(contas = conta, modifier = Modifier.fillMaxWidth(),
                        onClickDelete = {
                            viewModel.deleteConta(conta = conta.contaNome,  valor = conta.valorConta.toString())
                        },
                        onClickEdit = {
                            editConta.value = conta.contaNome
                            editValor.value = conta.valorConta.toString()
                            inputDialogState.value = true

                        })


                    //Text(text = conta.contaNome)
                }
            }
        }
    }
}

@Composable
fun ShowEditDialog(
    conta: String,
    inputDialog: MutableState<Boolean>,
    viewmodel: FinancasContasViewModel,
    valor: String,
    ){
    val editConta = rememberSaveable{mutableStateOf("")}
    val editValor = rememberSaveable{mutableStateOf("")}
    editConta.value = conta
    editValor.value = valor
    if(inputDialog.value){
        AlertDialog(

            onDismissRequest = { inputDialog.value = false },
            title = { Text(text = "Editar Conta") },
            text = {
                Column(horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center) {
                    TextField(value = editConta.value,
                        onValueChange = { editConta.value = it },
                    )
                    TextField(value = editValor.value,
                        onValueChange = { editValor.value = it },
                    )
                }

            },
            buttons = {
                Row(modifier = Modifier.padding(all=8.dp),
                    horizontalArrangement = Arrangement.Center) {
                    TextButton(onClick = {
                        viewmodel.addNovaConta(conta = editConta.value, valor = editValor.value)
                        inputDialog.value = false
                    }) {
                        Text(text = "Salvar")
                    }
                    TextButton(onClick = { inputDialog.value = false}) {
                        Text(text = "Cancelar")
                    }
                }
            },
        )

    }

}


@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean,
    isSingleLine:Boolean = true,
    keyboardType:KeyboardType = KeyboardType.Text,
    imeAction:ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default

){

    OutlinedTextField(value = valueState.value,
        onValueChange = {valueState.value = it},
        label = { Text(text = labelId)},
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        modifier = Modifier
            .padding(bottom = 10.dp, start = 10.dp, end = 10.dp)
            .fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        enabled = enabled,
        keyboardActions = onAction,

    )


}