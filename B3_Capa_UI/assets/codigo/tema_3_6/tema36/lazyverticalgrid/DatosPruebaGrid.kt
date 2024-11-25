package com.pmdm.proyectobase.ui.features.tema36.lazyverticalgrid

import androidx.compose.ui.graphics.Color
import com.pmdm.proyectobase.R

data class DatoGrid(
    val id: String,
    val texto: String,
    val color: Color,
    val idIcono: Int
) {
    companion object {
        private val idsGenerados = mutableSetOf<Int>()

        private val iconos = listOf(
            R.drawable.favorite_24px, R.drawable.ac_unit_24px,
            R.drawable.schedule_24px, R.drawable.blender_24px,
            R.drawable.cake_24px, R.drawable.cottage_24px
        )

        private val colores = listOf(
            Color(0xFFA20001), Color(0xFF9C27B0),
            Color(0xFF673AB7), Color(0xFF3F51B5),
            Color(0xFF605D7B), Color(0xFF005618)
        )

        private fun generaId(): Int {
            var id = (0..9999).random()
            while (idsGenerados.contains(id))
                id = (0..9999).random()
            idsGenerados.add(id)
            return id
        }

        fun datosGridAleatorios()
                : List<DatoGrid> {
            return (0..20).map {
                val id = generaId()
                DatoGrid(
                    id = id.toString(),
                    texto = "Dato\n$id",
                    color = colores.random(),
                    idIcono = iconos.random()
                )
            }
        }

        fun borraDatoGrid(dato: DatoGrid, datos: List<DatoGrid>): List<DatoGrid> {
            val i = datos.indexOf(dato)
            return if (i >= 0) datos.toMutableList().apply { removeAt(i) } else datos
        }

        fun addDatoGridAleatorio(datos: List<DatoGrid>): List<DatoGrid> {
            return mutableListOf<DatoGrid>().apply {
                add(
                    DatoGrid(
                        id = generaId().toString(),
                        texto = "Dato\n${generaId()}",
                        color = colores.random(),
                        idIcono = iconos.random()
                    )
                )
                datos.forEach { add(it) }
            }
        }
    }
}
