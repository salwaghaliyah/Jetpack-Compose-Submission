package com.dicoding.composesubmission.ui.navigation

sealed class Screen(val route: String) {
    object Home: Screen("home")
    object Profile : Screen("profile")
    object Detail: Screen("home/{id}") {
        fun createRoute(id: Int) = "home/$id"
    }
}
