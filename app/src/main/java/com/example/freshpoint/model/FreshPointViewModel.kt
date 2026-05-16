package com.example.freshpoint.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FreshPointViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState

    val restaurants = listOf(
        "ул. Ленина, 10",
        "ул. Пушкина, 25",
        "ТЦ Центральный, 2 этаж"
    )
    fun selectDelivery(){
        _uiState.update { currentState ->
            currentState.copy(
                orderType = OrderType.DELIVERY,

            )
        }
    }
    fun selectRestaurant(){
        _uiState.update { currentState ->
            currentState.copy(
                orderType = OrderType.RESTAURANT,
                deliveryAddress = ""
            )
        }
    }
    fun updateDeliveryAddress(address: String) {
        _uiState.update { currentState ->
            currentState.copy(deliveryAddress = address)
        }
    }
    fun saveDeliveryAddress() {
        _uiState.update { currentState ->
            currentState.copy(
                showDeliveryAddress = currentState.deliveryAddress,
                isDeliveryAddressSaved = currentState.deliveryAddress.isNotBlank()
            )
        }
    }

    fun updateSelectedRestaurant(address: String) {
        _uiState.update { currentState ->
            currentState.copy(selectedRestaurantAddress = address)
        }
    }

    fun saveRestAddress(){
        _uiState.update { currentState ->
            currentState.copy(
                isRestaurantAddressSaved = currentState.selectedRestaurantAddress.isNotBlank()
            )
        }
    }

    fun selectBurgerItem(burgerItem: BurgerMenuItem){
        _uiState.update { currentState ->
            currentState.copy(
                selectedBurgerItem = burgerItem
            )
        }

    }
}