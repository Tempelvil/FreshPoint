package com.example.freshpoint.ui

import android.graphics.drawable.shapes.Shape
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.freshpoint.data.DataMenuItem.burgerMenuList
import com.example.freshpoint.model.BurgerMenuItem

@Composable
fun MenuOrderScreen(
    modifier: Modifier = Modifier,
    burgerMenuItems: List<BurgerMenuItem>,
    addSomeStuffToBurger: (BurgerMenuItem) -> Unit
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.fillMaxSize()
            .padding(start=24.dp,end=24.dp,top=24.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(burgerMenuItems){ burgerItem->
            menuItemCard(
                menuItem= burgerItem,
                addSomeStuffToBurger = addSomeStuffToBurger
            )
        }

    }
}

@Composable
fun menuItemCard(
    modifier: Modifier = Modifier,
    menuItem: BurgerMenuItem,
    addSomeStuffToBurger: (BurgerMenuItem) -> Unit
){
    Card(
        modifier = modifier.fillMaxWidth()
            .height(270.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Image(
                painter = painterResource(menuItem.imageRes),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = stringResource(menuItem.name),
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.height(48.dp)

            )
            Button(
                onClick = {addSomeStuffToBurger(menuItem)},
                modifier = Modifier.fillMaxWidth().padding(8.dp).height(50.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFFF9800),
                    contentColor = Color.Black
                )
            ) {

                Text(
                    text = "${menuItem.price} ₽",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewMenuOrderScreen() {
    MenuOrderScreen(
        burgerMenuItems = burgerMenuList,
        modifier = Modifier,
        addSomeStuffToBurger = {},

    )
}