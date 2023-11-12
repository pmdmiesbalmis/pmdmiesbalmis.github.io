package com.pmdm.agenda.ui.features.vercontactos

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FamilyRestroom
import androidx.compose.material.icons.filled.MedicalServices
import androidx.compose.material.icons.filled.SportsEsports
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.vector.ImageVector
import com.pmdm.agenda.models.Contacto
import java.util.EnumSet

data class CatergoriaUiState(
    val amigos: Boolean = false,
    val amigosIcon : ImageVector = Icons.Filled.SportsEsports,
    val trabajo: Boolean = false,
    val trabajoIcon : ImageVector = Icons.Filled.Work,
    val familia: Boolean = false,
    val familiaIcon : ImageVector = Icons.Filled.FamilyRestroom,
    val emergencias: Boolean = false,
    val emergenciasIcon : ImageVector = Icons.Filled.MedicalServices
)

fun CatergoriaUiState.toEnum() = EnumSet.noneOf(Contacto.Categorias::class.java).apply {
    if (amigos) add(Contacto.Categorias.Amigos)
    if (trabajo) add(Contacto.Categorias.Trabajo)
    if (familia) add(Contacto.Categorias.Familia)
    if (emergencias) add(Contacto.Categorias.Emergencias)
}