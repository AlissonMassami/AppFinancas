package com.example.minhasfinancas.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minhasfinancas.data.Contas
import java.util.*

@Composable
fun PagamentoItem(
    modifier: Modifier = Modifier,
    contas: Contas,
    onClickEdit: () -> Unit,
){
    var selection = rememberSaveable{
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier.padding(5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = contas.contaNome.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            },
            fontWeight = FontWeight.Bold, fontSize = 20.sp
        )
        //Spacer(modifier = Modifier.width(5.dp))


        Row(
            verticalAlignment = Alignment.CenterVertically,

        ) {
            Text(
                text = "R$ ${contas.valorConta.toString()}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            //Spacer(modifier = Modifier.width(30.dp))
            IconButton(onClick = onClickEdit) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "botaoEdit")
            }

            //Spacer(modifier = Modifier.width(4.dp))



        }
    }

}