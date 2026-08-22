package br.com.github.sprint3.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.github.sprint3.ui.screens.login.LoginScreen
import br.com.github.sprint3.ui.screens.splash.SplashScreen

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = SplashRoute
    ) {
        composable<SplashRoute> {
            SplashScreen(
                onNavigateToLogin = {
                    navController.navigate(LoginRoute)
                }
            )
        }

        composable<LoginRoute> {
            LoginScreen()
        }
    }
}
