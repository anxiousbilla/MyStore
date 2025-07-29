package io.github.dwivedyaakash.mystore.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.dwivedyaakash.mystore.model.Product
import io.github.dwivedyaakash.mystore.repository.StoreRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class StoreUiState(
    val products: List<Product> = emptyList(),
    val product: Product? = null,
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

class StoreViewModel : ViewModel() {
    private val repository = StoreRepository()

    private val _uiState = MutableStateFlow(StoreUiState())
    val uiState: StateFlow<StoreUiState> = _uiState.asStateFlow()

    init {
        loadStore()
    }

    fun loadStore() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            repository.getProducts()
                .onSuccess { products ->
                    _uiState.value = _uiState.value.copy(isLoading = false, products = products)
                }
                .onFailure { exception ->
                    _uiState.value =
                        _uiState.value.copy(isLoading = false, errorMessage = exception.message)
                }
        }
    }

    fun getProductById(id: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            repository.getProductById(id)
                .onSuccess { product ->
                    _uiState.value = _uiState.value.copy(isLoading = false, product = product)
                }
                .onFailure { exception ->
                    _uiState.value =
                        _uiState.value.copy(isLoading = false, errorMessage = exception.message)
                }
        }
    }

}
