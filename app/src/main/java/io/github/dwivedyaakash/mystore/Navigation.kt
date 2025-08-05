package io.github.dwivedyaakash.mystore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import io.github.dwivedyaakash.mystore.ui.screens.CartScreen
import io.github.dwivedyaakash.mystore.ui.screens.HomeScreen
import io.github.dwivedyaakash.mystore.ui.screens.ProfileScreen
import io.github.dwivedyaakash.mystore.ui.screens.WishlistScreen
import io.github.dwivedyaakash.mystore.viewModel.StoreUiState
import io.github.dwivedyaakash.mystore.viewModel.StoreViewModel

enum class Screens(val title: String, val route: String, val icon: ImageVector) {
    Home("Home", "home", Icons.Default.Home),
    Wishlist("Wishlist", "wishlist", Icons.Default.Favorite),
    Cart("Cart", "cart", Icons.Default.ShoppingCart),
    Profile("Profile", "profile", Icons.Default.AccountCircle)
}

@Composable
fun Navigation(
    navController: NavHostController,
    innerPadding: PaddingValues,
    viewModel: StoreViewModel,
    uiState: StoreUiState
) {
    NavHost(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5DC))
            .padding(innerPadding),
        navController = navController,
        startDestination = Screens.Home.route
    ) {
        composable(route = Screens.Home.route) {
            HomeScreen(viewModel, uiState)
        }
        composable(route = Screens.Wishlist.route) {
            WishlistScreen(viewModel, uiState)
        }
        composable(route = Screens.Cart.route) {
            CartScreen()
        }
        composable(route = Screens.Profile.route) {
            ProfileScreen()
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var selectedIndex by remember { mutableIntStateOf(0) }
    val screens = listOf(Screens.Home, Screens.Wishlist, Screens.Cart, Screens.Profile)

    NavigationBar {
        screens.forEachIndexed { index, screen ->
            NavigationBarItem(
                selected = selectedIndex == index,
                label = { Text(screen.title) },
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.title) },
                onClick = {
                    selectedIndex = index
                    navController.navigate(screen.route)
                }
            )
        }
    }
}
