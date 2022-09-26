package com.example.minhasfinancas.screens.home

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.minhasfinancas.navigation.AppScreens

//NavController(context = LocalContext.current   params para usar preview
@Composable
fun FinancasHomeScreen(navController: NavController) {
    Surface(color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()) {
        Column(verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            Row(modifier = Modifier.padding(horizontal = 3.dp),
                horizontalArrangement = Arrangement.SpaceEvenly) {
                IconMenu(imageVector = Icons.Default.List,
                    frase = "Contas",
                    onClick = {
                        navController.navigate(AppScreens.ContasScreen.name)
                    })
                IconMenu(imageVector = Icons.Default.Search,
                    frase = "Setor",
                    onClick = {
                        navController.navigate(AppScreens.SetorScreen.name)

                    })
                IconMenu(imageVector = Icons.Default.Home,
                    frase = "Pagamento",
                    onClick = {
                        navController.navigate(AppScreens.PagamentoScreen.name)
                    })
            }


        }
    }
}

val IconSizeModifier = Modifier.size(110.dp)

@Composable
fun IconMenu(
    modifier: Modifier = Modifier,
    imageVector: ImageVector,
    onClick:()->Unit,
    tint: Color = Color.Black.copy(alpha = 0.8f),
    backgroundColor: Color = MaterialTheme.colors.background,
    elevation : Dp = 4.dp,
    frase : String
    ){
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center) {


        Card(
            modifier = Modifier
                .size(90.dp)
                .padding(all = 10.dp)
                .clickable { onClick.invoke() }
                .then(IconSizeModifier),
            shape = CircleShape,
            backgroundColor = backgroundColor,
            elevation = elevation,
        ) {
            Icon(
                imageVector = imageVector,
                contentDescription = "Icone Menu",
                tint = tint,
            )

        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = frase)
    }
}