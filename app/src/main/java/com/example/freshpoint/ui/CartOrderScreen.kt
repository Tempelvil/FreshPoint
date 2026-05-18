package com.example.freshpoint.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CartOrderScreen(
    modifier: Modifier= Modifier,
    onBackClick: () ->Unit,

){
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Назад"
                    )
                }

                Text(
                    text = "Корзина и оплата",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    ) { innerPadding ->
        
        Column(
            modifier = Modifier.padding(innerPadding)

        ) {
            OrderDeliveryOrRestCard()
            
            LazyVerticalGrid(
                modifier = Modifier.padding(innerPadding).
                fillMaxSize().padding(start = 24.dp).padding(end = 24.dp).padding(top=24.dp),
                columns = GridCells.Fixed(1),
                contentPadding = PaddingValues(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {


            }
        }

    }
}
@Composable
fun OrderDeliveryOrRestCard(

)
{
    Card(
        modifier = Modifier,

    ) {
        Column(

        ) {
            Text(text = "alsdsa")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun previewCartOrderScreen(){
    CartOrderScreen(
        onBackClick = {}
    )

}