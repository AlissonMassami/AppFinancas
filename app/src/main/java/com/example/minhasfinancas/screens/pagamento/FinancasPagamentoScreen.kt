package com.example.minhasfinancas.screens.pagamento

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.minhasfinancas.screens.contas.FinancasContasViewModel
import com.example.minhasfinancas.screens.contas.InputField
import java.util.*

val meses = listOf("JAN", "FEV", "MAR", "ABR", "MAI","JUN","JUL","AGO","SET","OUT","NOV","DEZ")
val anos = listOf("17","18","19","20","21","22","23","24","25","26")
//NavController(context = LocalContext.current   params para usar preview
@Composable
fun FinancasPagamentoScreen(navController: NavController,
                            viewModel: FinancasContasViewModel = hiltViewModel()
){

    val novaRenda = rememberSaveable{mutableStateOf("")}
    var somaTotal = rememberSaveable{mutableStateOf(0.0)}
    var restante = rememberSaveable{mutableStateOf(0.0)}
    var contas = viewModel.contasList.collectAsState(initial = emptyList())
    Surface(color = MaterialTheme.colors.background, modifier = Modifier.fillMaxSize()    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Data:",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp))
                var mes = dropdownMenu(meses, "MES")
                var anos = dropdownMenu(anos, "ANO")



            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Renda Mensal: R$ ${restante.value}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp))
                InputField(
                    valueState = novaRenda,
                    labelId = "Renda Mensal",
                    enabled = true,
                )

            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "Contas: R$ ${somaTotal.value}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp))
                Text(text = "Restante: R$ ${restante.value}",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(8.dp))
            }
            if (novaRenda.value != ""){
                restante.value = novaRenda.value.toDouble() - somaTotal.value
            }

            LazyColumn(modifier = Modifier.fillMaxWidth()){
                items(items = contas.value) { conta ->
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly) {
                        Text(
                            text = conta.contaNome.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                            },
                            fontWeight = FontWeight.Bold, fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.size(20.dp))
                        Text(
                            text = "R$ ${conta.valorConta}",
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        //Spacer(modifier = Modifier.width(30.dp))
                        IconButton(onClick = { } ) {
                            Icon(imageVector = Icons.Default.Edit, contentDescription = "botaoEdit")
                        }
                        val isChecked = rememberSaveable { mutableStateOf(false) }
                        Checkbox(
                            checked = isChecked.value,
                            onCheckedChange = { isChecked.value = it
                                if(it){
                                    somaTotal.value += conta.valorConta
                                }else{
                                    somaTotal.value -= conta.valorConta
                                }
                            },
                            colors = CheckboxDefaults.colors(Color.Green),
                            modifier = Modifier.padding(5.dp))

                    }







                }
            }
            Row(horizontalArrangement = Arrangement.End) {
                Button(modifier = Modifier
                    .padding(3.dp),

                    shape = CircleShape,
                    onClick = {

                    }
                ) {
                    Text(text = "Finalizar Pagamento", modifier = Modifier.padding(4.dp), style = MaterialTheme.typography.body1 )

                }
            }


        }
    }
}

@Composable
fun dropdownMenu(lista: List<String>, label: String): String {
    // the expanded state of the Text Field
    var mExpanded by remember { mutableStateOf(false) }
    // Create a string value to store the selected city
    var mSelectedText by remember { mutableStateOf("") }

    var mTextFieldSize by remember { mutableStateOf(Size.Zero)}

    // Up Icon when expanded and down icon when collapsed
    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(Modifier.padding(20.dp)) {

        // Create an Outlined Text Field
        // with icon and not expanded
        
        OutlinedTextField(
            readOnly = true,
            value = mSelectedText,
            onValueChange = { mSelectedText = it },
            modifier = Modifier
                .width(100.dp)
                .onGloballyPositioned { coordinates ->
                    // This value is used to assign to
                    // the DropDown the same width
                    mTextFieldSize = coordinates.size.toSize()
                },
            label = {Text(label)},

            trailingIcon = {
                Icon(icon,"contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            }
        )

        // Create a drop-down menu with list of cities,
        // when clicked, set the Text Field text as the city selected
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },

            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
                .requiredHeight(180.dp)

        ) {
            lista.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label
                    mExpanded = false
                }) {
                    Text(text = label)
                }
            }
        }

    }

    return mSelectedText



}