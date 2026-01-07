package com.example.retrofit_appdhara.presentation.screen.productstate

import com.example.retrofit_appdhara.domain.model.Product

sealed class UiState {

    object Loading : UiState()
    data class Success(val products : List<Product>) : UiState()
    data class Error(val message : String) : UiState()
}