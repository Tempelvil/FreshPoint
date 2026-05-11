package com.example.freshpoint.model

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class FreshPointViewModel: ViewModel() {
    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState

    fun selectDelivery(){
        _uiState.update { currentState ->
            currentState.copy(
                orderType = OrderType.DELIVERY,
                selectedRestaurantAddress = ""
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

    fun updateSelectedRestaurant(address: String) {
        _uiState.update { currentState ->
            currentState.copy(selectedRestaurantAddress = address)
        }
    }
}