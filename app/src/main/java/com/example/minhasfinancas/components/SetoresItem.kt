package com.example.minhasfinancas.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.minhasfinancas.data.Setores
import java.util.*

@Composable
fun SetoresItem(
    modifier: Modifier = Modifier,
    setores: Setores,
    onClickEdit: () -> Unit,
    onClickDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        //horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,

    ) {
        Text(
                text = setores.setorNome.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
                },
                fontWeight = FontWeight.Bold, fontSize = 20.sp
            )


        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${setores.porcentagemSetor}%",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = "R$${setores.saldoTotalSetor}",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(15.dp))
            IconButton(onClick = onClickEdit) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "botaoEdit")
            }

            IconButton(onClick = onClickDelete) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "botaoDelete")
            }
            //Spacer(modifier = Modifier.width(4.dp))

        }
    }
}