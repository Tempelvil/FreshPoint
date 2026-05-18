package com.example.freshpoint.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.freshpoint.model.FreshPointViewModel
import com.example.freshpoint.ui.theme.FreshPointTheme
import com.example.freshpoint.ui.theme.StartOrderScreen

enum class BottomScreen(
    val title: String
) {
    Home( "Главная"),
    Menu( "Меню"),
    Order("Окно Заказа"),
    Cart( "Корзина")
}

@Composable
fun FreshPointApp() {
    val navController = rememberNavController()
    val viewModel: FreshPointViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        bottomBar = {
            FreshPointBottomBar(
                currentRoute = navController.currentBackStackEntryAsState()
                    .value
                    ?.destination
                    ?.route,
                onNavigate = { route ->
                    navController.navigate(route) {
                        launchSingleTop = true
                    }
                }
            )
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = BottomScreen.Home.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomScreen.Home.name) {
                StartOrderScreen(
                    uiState = uiState,
                    restaurants = viewModel.restaurants,
                    onDeliveryClick = viewModel::selectDelivery,
                    onRestaurantClick = viewModel::selectRestaurant,
                    onDeliveryAddressChange = viewModel::updateDeliveryAddress,
                    onRestaurantSelected = viewModel::updateSelectedRestaurant,
                    onDeliveryAddressSaved = {viewModel.saveDeliveryAddress()},
                    saveRestAddress = {viewModel.saveRestAddress()}
                )
            }

            composable(BottomScreen.Menu.name) {
                MenuOrderScreen(
                    burgerMenuItems = uiState.burgerMenuItems,
                    addSomeStuffToBurger = {selectBurger ->
                        viewModel.selectBurgerItem(selectBurger)
                        navController.navigate(BottomScreen.Order.name)
                    }
                )
            }
            composable ( BottomScreen.Order.name ){
                AddToShoppingCart(
                    souseMenuItems = uiState.souseMenuItem,
                    drinkMenuItems = uiState.drinkMenuItem,
                    burgerMenuItem = uiState.selectedBurgerItem,
                    addToCart = {
                        fullOrder -> viewModel.addFullOrder(fullOrder)
                        navController.popBackStack()
                    },
                    onBackClick = {
                        navController.popBackStack()
                    },
                )
            }

            composable(BottomScreen.Cart.name) {
                //CartScreen()
            }
        }
    }
}

@Composable
fun FreshPointBottomBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    NavigationBar {
        NavigationBarItem(
            selected = currentRoute == BottomScreen.Home.name,
            onClick = { onNavigate(BottomScreen.Home.name) },
            icon = {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = null
                )
            },
            label = { Text(BottomScreen.Home.title) }
        )

        NavigationBarItem(
            selected = currentRoute == BottomScreen.Menu.name,
            onClick = { onNavigate(BottomScreen.Menu.name) },
            icon = {
                Icon(
                    imageVector = Icons.Default.RestaurantMenu,
                    contentDescription = null
                )
            },
            label = { Text(BottomScreen.Menu.title) }
        )

        NavigationBarItem(
            selected = currentRoute == BottomScreen.Cart.name,
            onClick = { onNavigate(BottomScreen.Cart.name) },
            icon = {
                Icon(
                    imageVector = Icons.Default.ShoppingBasket,
                    contentDescription = null
                )
            },
            label = { Text(BottomScreen.Cart.title) }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FreshPointBottomBarPreview() {
    FreshPointTheme {
        FreshPointBottomBar(
            currentRoute = BottomScreen.Home.name,
            onNavigate = {}
        )
    }
}