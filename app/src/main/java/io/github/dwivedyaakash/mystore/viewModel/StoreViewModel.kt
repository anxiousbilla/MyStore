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
    val errorMessage: String? = null,
    val favourites: MutableList<Int> = mutableListOf(),
    val favouriteProducts: List<Product> = emptyList(),
    val cartItemCounts: MutableMap<Int, Int> = mutableMapOf(),
    val cartProducts: List<Product> = emptyList()
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

    fun getFavouriteProducts() {
        val favouriteProducts = mutableListOf<Product>()

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            uiState.value.favourites.map {
                repository.getProductById(it.toString())
                    .onSuccess { product ->
                        favouriteProducts.add(product)
                    }
                    .onFailure { exception ->
                        _uiState.value =
                            _uiState.value.copy(isLoading = false, errorMessage = exception.message)
                    }
            }

            _uiState.value =
                _uiState.value.copy(isLoading = false, favouriteProducts = favouriteProducts)
        }
    }

    fun addFavourite(id: Int) {
        _uiState.value =
            _uiState.value.copy(favourites = (_uiState.value.favourites + id) as MutableList<Int>)
    }

    fun removeFavourite(id: Int) {
        _uiState.value =
            _uiState.value.copy(favourites = (_uiState.value.favourites - id) as MutableList<Int>)
    }

    fun getCartProducts() {
        val cartProducts = mutableListOf<Product>()

        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

            uiState.value.cartItemCounts.keys.map {
                repository.getProductById(it.toString())
                    .onSuccess { product ->
                        cartProducts.add(product)
                    }
                    .onFailure { exception ->
                        _uiState.value =
                            _uiState.value.copy(isLoading = false, errorMessage = exception.message)
                    }
            }

            _uiState.value =
                _uiState.value.copy(isLoading = false, cartProducts = cartProducts)
        }
    }

    fun addToCart(id: Int) {
        val currentUiState = _uiState.value
        val currentCartItemCounts = currentUiState.cartItemCounts.toMutableMap()
        val currentCount = currentCartItemCounts[id] ?: 0

        if (currentCount > 0) currentCartItemCounts[id] = currentCount + 1
        else currentCartItemCounts[id] = 1

        _uiState.value = currentUiState.copy(cartItemCounts = currentCartItemCounts)
    }

    fun removeItemFromCart(id: Int) {
        val currentUiState = _uiState.value
        val currentCartItemCounts = currentUiState.cartItemCounts.toMutableMap()
        val currentCount = currentCartItemCounts[id] ?: 0

        if (currentCount > 1) currentCartItemCounts[id] = currentCount - 1
        else currentCartItemCounts.remove(id)

        _uiState.value = currentUiState.copy(cartItemCounts = currentCartItemCounts)
    }

    fun deleteAllItemsFromCart(id: Int) {
        val currentUiState = _uiState.value
        val currentCartItemCounts = currentUiState.cartItemCounts.toMutableMap()

        currentCartItemCounts.remove(id)
        _uiState.value = currentUiState.copy(cartItemCounts = currentCartItemCounts)
    }

}
