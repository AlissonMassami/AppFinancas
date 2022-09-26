package com.example.minhasfinancas.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.minhasfinancas.data.Contas
import java.util.*


@Composable
fun ContasItem(
    modifier: Modifier = Modifier,
    contas: Contas,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        //horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = contas.contaNome.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            },
            fontWeight = FontWeight.Bold, fontSize = 20.sp
        )
        Spacer(modifier = Modifier.width(20.dp))


        Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "R$ ${contas.valorConta.toString()}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Spacer(modifier = Modifier.width(30.dp))
            IconButton(onClick = onClickEdit) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "botaoEdit")
            }

            IconButton(onClick = onClickDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "botaoDelete")
            }

        }
    }
}