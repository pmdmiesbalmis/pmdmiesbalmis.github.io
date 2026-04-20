package com.vehiculos.ui.composables

import android.annotation.SuppressLint
import android.graphics.Matrix
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.asAndroidPath
import androidx.compose.ui.graphics.asComposePath
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.core.graphics.PathParser

class BadgeShape : Shape {
    @SuppressLint("RestrictedApi")
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(
            path = PathParser.createPathFromPathData(
                "m 470.491,284.1 -1.3,-2 c -8.3,-12.7 -16.8,-25.9 -25.4,-38.8 7.4,-11.2 14.8,-22.6 21.9,-33.6 l 4.1,-6.4 c 7.5,-11.7 9.6,-24.3 5.8,-35.6 -3.8,-11.3 -13.1,-20.2 -26.1,-25 -8.1,-3 -16.4,-6.1 -24.3,-9.1 -6.5,-2.5 -13.2,-5 -19.8,-7.5 -0.5,-10.3 -1,-20.6 -1.6,-30.6 -0.3,-6 -0.7,-12 -1,-18 -1.2,-22.5 -17,-38.3 -38.5,-38.3 -3.9,0 -7.9,0.5 -11.9,1.6 l -3.5,0.9 c -14.3,3.8 -29,7.7 -43.5,11.9 -6.5,-8.1 -13.1,-16.2 -19.5,-24.1 -3.7,-4.5 -7.3,-9 -11,-13.5 -8.4,-10.3 -19.5,-16 -31.3,-16 -11.8,0 -23,5.7 -31.4,16.1 l -4.8,5.9 c -8.4,10.4 -17.2,21.2 -25.6,32 -11.5,-3.2 -23,-6.3 -32.5,-8.8 -3.5,-0.9 -6.9,-1.8 -9.9,-2.6 -0.1,0 -0.2,-0.1 -0.4,-0.1 -5.4,-1.3 -10.5,-1.9 -15.3,-1.9 -22.5,0 -37.5,14.1 -39.2,36.8 -1.3,17.9 -2,35.4 -2.5,49.4 -15.9,6 -31.3,11.9 -45.9,17.7 -12.1,4.8 -20.7,13.5 -24.2,24.6 -3.6,11.1 -1.6,23.3 5.4,34.3 8.5,13.2 17.6,27.1 26.8,41 -9.5,14.4 -18.6,28.5 -27.2,42.2 -6.5,10.3 -8.2,22.5 -4.6,33.3 3.6,10.9 12.3,19.7 23.8,24.2 l 4.3,1.7 c 13.8,5.3 28,10.8 42.2,16.1 0.3,4.8 0.6,9.6 0.9,14.3 0.8,10.9 1.5,21.2 1.4,31.6 -0.1,11.1 4.2,21.9 11.8,29.5 7.3,7.4 17.1,11.4 27.6,11.4 4.5,0 9,-0.7 13.5,-2.1 9.8,-3.1 20.1,-5.8 30.1,-8.4 4.8,-1.3 9.8,-2.6 14.8,-3.9 10.3,12.9 20.7,25.6 30.5,37.6 8.5,10.3 19.6,16 31.3,16 11.8,0 22.9,-5.7 31.3,-16.1 11.3,-13.9 20.9,-25.8 30.3,-37.8 12.3,3.4 24.8,6.7 36.9,9.9 l 10.3,2.7 c 3.9,1 7.8,1.5 11.6,1.5 v 0 c 21.2,0 37.5,-15.9 38.6,-37.9 0.3,-5.5 0.6,-11 0.9,-16.6 0.6,-10.7 1.2,-21.7 1.6,-32.6 14.1,-5.3 28.2,-10.8 42,-16.2 l 3.3,-1.3 c 12.3,-4.8 21.1,-13.6 24.7,-24.8 3.6,-11.4 1.7,-23.6 -5.5,-34.6 z"
            ).asComposePath().apply {
                val pathSize = getBounds().size
                val matrix = Matrix()
                matrix.postScale(size.width / pathSize.width, size.height / pathSize.height)
                asAndroidPath().transform(matrix)
            }
        )
    }
}