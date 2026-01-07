package com.example.retrofit_appdhara.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofit_appdhara.domain.model.Product
import com.example.retrofit_appdhara.domain.repository.ProductRepository
import com.example.retrofit_appdhara.presentation.screen.productstate.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val repository: ProductRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState : StateFlow<UiState> = _uiState.asStateFlow()

    private val _message = MutableStateFlow("")
    val message : StateFlow<String> = _message.asStateFlow()

    private val _localProducts = mutableListOf<Product>()

    private var nextLocalId = 1000

    init {
        loadProducts()
    }

    fun loadProducts() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                val products = repository.getAllProducts()
                _localProducts.clear()
                _localProducts.addAll(products)
                _uiState.value = UiState.Success(_localProducts.toList())
                _message.value = "Loaded ${products.size} product (GET)"
            } catch (e : Exception) {
                _uiState.value = UiState.Error(e.message ?: " Unknown error ")
                _message.value = "Error : ${e.message}"
            }
        }
    }

    fun createProducts(product: Product ) {
        viewModelScope.launch {
            try {
                repository.createProduct(product)
                val localProduct = product.copy(nextLocalId++)
                _localProducts.add(0, localProduct)
                _uiState.value = UiState.Success(_localProducts.toList())
                _message.value = "Created Product with ID : ${localProduct.id}(POST)"
            } catch (e : Exception ){
                _message.value = "Error : ${e.message}"
            }
        }
    }

    fun updateProduct(id : Int, product: Product) {
        viewModelScope.launch {
            try {
                repository.updateProduct(id, product)
                val index = _localProducts.indexOfFirst { it.id == id }
                if (index != -1) {
                    _localProducts[index] = product.copy(id = id)
                    _uiState.value = UiState.Success(_localProducts.toList())
                    _message.value = "Update product ID : $id (PUT)"
                }
            } catch (e : Exception) {
                _message.value = "Error : ${e.message}"
            }
        }
    }

    fun patchProduct(id : Int, title : String) {
        viewModelScope.launch {
            try {
                val updates = mapOf("title" to title)
                repository.patchProduct(id, updates)
                val index = _localProducts.indexOfFirst { it.id == id }

                if (index != -1) {
                    val updateProduct = _localProducts[index].copy(title = title)
                    _localProducts[index] = updateProduct
                    _uiState.value = UiState.Success(_localProducts.toList())
                    _message.value = "Patched product ID : $id (PATCH)"
                }
            }catch (e : Exception) {
                _message.value = "Error : ${e.message}"
            }
        }
    }

    fun deleteProduct(id : Int){
        viewModelScope.launch {
            try {
                repository.deleteProduct(id)
                _localProducts.removeAll{it.id == id}
                _uiState.value = UiState.Success(_localProducts.toList())
                _message.value = "Deleted Successfully ID : $id (DELETE)"
            } catch (e : Exception ){
                _message.value = "Error ${e.message}"
            }
        }
    }

    fun clearMessage() {
        _message.value = ""
    }

}