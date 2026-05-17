package com.example.freshpoint.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
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
import com.example.freshpoint.data.DataMenuItem.drinkMenuList
import com.example.freshpoint.data.DataMenuItem.souseMenuList
import com.example.freshpoint.model.BurgerMenuItem
import com.example.freshpoint.model.DrinkMenuItem
import com.example.freshpoint.model.SouseMenuItem

@Composable
fun AddToShoppingCart(
    modifier: Modifier = Modifier,
    souseMenuItems: List<SouseMenuItem>,
    drinkMenuItems: List<DrinkMenuItem>,
    burgerMenuItem: BurgerMenuItem?
){
    Scaffold() {}
    Spacer(modifier= Modifier.height(100.dp))
    Column(
        modifier = Modifier.fillMaxSize().padding(14.dp)
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                if (burgerMenuItem != null) {
                    Image(
                        painter = painterResource(burgerMenuItem.imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(12.dp),
                        contentScale = ContentScale.Fit
                    )

                    Text(
                        text = stringResource(burgerMenuItem.name),
                        modifier = Modifier.padding(horizontal = 16.dp)
                            .align(alignment = Alignment.CenterHorizontally)
                    )
                } else {
                    Text(text = "Бургер не выбран")
                }
            }
        }

        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(150.dp)
        ) {
            items(souseMenuItems) { souse ->
                oneItemCard(
                    imageRes = souse.imageRes,
                    nameRes = souse.name,
                    price = souse.price
                )
            }
        }
        LazyHorizontalGrid(
            rows = GridCells.Fixed(1),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.height(150.dp)
        ) {
            items(drinkMenuItems) { souse ->
                oneItemCard(
                    imageRes = souse.imageRes,
                    nameRes = souse.name,
                    price = souse.price
                )
            }
        }

    }


}
@Composable
fun oneItemCard(
    imageRes: Int,
    nameRes: Int,
    price: Float? = null,
){
    Card(
        modifier = Modifier.height(120.dp).width(130.dp)
    ) {
       Column(
           modifier = Modifier.padding(12.dp),
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           Image(
               painter = painterResource(imageRes),
               contentDescription = null,
               modifier = Modifier.fillMaxWidth()
                   .height(80.dp).width(80.dp),
               contentScale = ContentScale.Fit
           )
           Text(
               text = stringResource(nameRes),
               maxLines = 2,
               overflow = TextOverflow.Ellipsis
           )
           if(price!=null){
               Button(
                   onClick = {addSomeStuffToBurger(menuItem)},
                   modifier = Modifier.fillMaxWidth().padding(8.dp).height(50.dp),
                   shape = RoundedCornerShape(14.dp),
                   colors = ButtonDefaults.buttonColors(
                       containerColor = Color(0xFFFF9800),
                       contentColor = Color.Black
                   )
               ){
                   Text(text = "$price")
               }
           }
       }
    }
}

@Preview(showBackground = true)
@Composable
fun previewAddToShoppingCart(){
    AddToShoppingCart(
        modifier = Modifier,
        souseMenuItems = souseMenuList,
        drinkMenuItems = drinkMenuList,
        burgerMenuItem = burgerMenuList[0],

    )
}