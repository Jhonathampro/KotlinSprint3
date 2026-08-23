package br.com.github.sprint3.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import br.com.github.sprint3.ui.components.BottomTab
import br.com.github.sprint3.ui.screens.home.HomeScreen
import br.com.github.sprint3.ui.screens.login.LoginScreen
import br.com.github.sprint3.ui.screens.splash.SplashScreen
import br.com.github.sprint3.ui.screens.students.StudentsScreen
import br.com.github.sprint3.ui.screens.turmas.TurmasScreen

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    fun navigateToTab(tab: BottomTab) {
        when (tab) {
            BottomTab.HOME -> {
                navController.navigate(HomeRoute) {
                    popUpTo(SplashRoute)
                    launchSingleTop = true
                }
            }
            BottomTab.TURMAS -> {
                navController.navigate(TurmasRoute) {
                    popUpTo(SplashRoute)
                    launchSingleTop = true
                }
            }
            BottomTab.PLATAFORMAS -> {
                navController.navigate(HomeRoute) {
                    popUpTo(SplashRoute)
                    launchSingleTop = true
                }
            }
            BottomTab.DASHBOARD -> {
                navController.navigate(HomeRoute) {
                    popUpTo(SplashRoute)
                    launchSingleTop = true
                }
            }
        }
    }

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
            LoginScreen(
                onLoginClick = {
                    navController.navigate(HomeRoute) {
                        popUpTo(LoginRoute) { inclusive = true }
                    }
                }
            )
        }

        composable<HomeRoute> {
            HomeScreen(
                onTurmaClick = { turmaId ->
                    navController.navigate(StudentsRoute(turmaId))
                },
                onTabSelected = { tab ->
                    navigateToTab(tab)
                }
            )
        }

        composable<TurmasRoute> {
            TurmasScreen(
                onTurmaClick = { turmaId ->
                    navController.navigate(StudentsRoute(turmaId))
                },
                onTabSelected = { tab ->
                    navigateToTab(tab)
                }
            )
        }

        composable<StudentsRoute> { backStackEntry ->
            val route: StudentsRoute = backStackEntry.toRoute()
            StudentsScreen(
                turmaId = route.turmaId,
                onBackClick = {
                    navController.popBackStack()
                },
                onTabSelected = { tab ->
                    navigateToTab(tab)
                }
            )
        }
    }
}
