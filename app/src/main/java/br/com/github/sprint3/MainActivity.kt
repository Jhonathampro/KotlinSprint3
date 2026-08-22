package br.com.github.sprint3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import br.com.github.sprint3.navegation.AppNavigation
import br.com.github.sprint3.ui.theme.Sprint3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Sprint3Theme {
                val navController = rememberNavController()

                AppNavigation(navController)
            }
        }
    }
}