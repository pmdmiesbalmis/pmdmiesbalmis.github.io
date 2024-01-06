package com.pmdm.navegacion.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.pmdm.navegacion.ui.features.PantallaAScreen
import com.pmdm.navegacion.ui.features.PantallaBScreen
import com.pmdm.navegacion.ui.navigation.NavHostEjemploBasico
import com.pmdm.navegacion.ui.theme.EjemploNavegacionTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploNavegacionTheme {
                EjemploNavegacionTheme {
                    Surface(modifier = Modifier.fillMaxSize()) {
                        val navController = rememberNavController()
                        NavHostEjemploBasicoInicial(navController = navController)
                    }
                }
            }
        }
    }
}

@Composable
private fun NavHostEjemploBasicoInicial(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "pantalla_A"
    ) {
        composable(route = "pantalla_A") { backStackEntry ->
            PantallaAScreen {
                navController.navigate("pantalla_B")
            }
        }
        composable(route = "pantalla_B") { backStackEntry ->
            PantallaBScreen {
                navController.navigateUp()
            }
        }
    }
}
