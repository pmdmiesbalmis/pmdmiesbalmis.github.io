package com.vehiculos.ui.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.vehiculos.ui.features.galeriacoches.GaleriaCochesScreen
import com.vehiculos.ui.features.galeriacoches.GaleriaCochesViewModel
import com.vehiculos.ui.navigation.VehiculosNavHost
import com.vehiculos.ui.theme.VehiculosTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm by viewModels<GaleriaCochesViewModel>()
        setContent {
            VehiculosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VehiculosNavHost()
                }
            }
        }
    }
}
