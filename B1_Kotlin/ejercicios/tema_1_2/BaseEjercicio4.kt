package ejercicios.kotlin.funcional

data class Producto(
    val codArticulo: String,
    val descripcion: String,
    val categoria: String,
    val colores: List<String>,
    val dimensiones: Dimensiones,
    val precio: Double
)

class Dimensiones(val largo: Int, val ancho: Int, val espesor: Int) {
    override fun toString(): String = "L:$largo x A:$ancho x E:$espesor";
}

class Datos {
    companion object {
        val productos = listOf<Producto>(
            Producto(
                codArticulo = "A01",
                descripcion = "Uno",
                categoria = "C1",
                colores = listOf("blanco", "negro", "gris"),
                dimensiones = Dimensiones(
                    largo = 4,
                    ancho = 4,
                    espesor = 3
                ),
                precio = 15.05
            ),

            Producto(
                codArticulo = "A02",
                descripcion = "Dos",
                categoria = "C1",
                colores = listOf("blanco", "gris", "rojo"),
                dimensiones = Dimensiones(

                    largo = 4,
                    ancho = 10,
                    espesor = 2
                ),
                precio = 25.95
            ),
            Producto(
                codArticulo = "A03",
                descripcion = "Tres",
                categoria = "C1",
                colores = listOf("rojo", "gris", "verde"),
                dimensiones = Dimensiones(
                    largo = 5,
                    ancho = 5,
                    espesor = 3
                ),
                precio = 30.25
            ),
            Producto(
                codArticulo = "A04",
                descripcion = "Cuatro",
                categoria = "C2",
                colores = listOf("verde", "rojo"),
                dimensiones = Dimensiones(
                    largo = 6,
                    ancho = 8,
                    espesor = 4
                ),
                precio = 18.45
            )
        )
    }
}

fun ejercicio4() {
    val x: String = ""
    val SeparadorConsulta = "\n" + x.padEnd(80, '_') + "\n";

    println(SeparadorConsulta);
    println(
        "Consulta 1: Usando las funciones filter y map.\n" +
                "Mostrar CodArticulo, Descripcion y Precio .\n" +
                "de productos con Precio entre 10 y 30 euros\n"
    )

    val consulta1 =
    println(consulta1.joinToString(separator = "\n"))
    println(SeparadorConsulta)
    println(
        "Consulta 2: Usando las funciones map, sortedByDescending y take.\n" +
                "Muestra CodArticulo, Descripcion y Precio de los 3 productos.\n" +
                "más caros (ordenando por Precio descendente)\n"
    )
    val consulta2: List<Any> =
    println(consulta2.joinToString(separator = "\n"));

    println(SeparadorConsulta)
    println(
        "Consulta 3: Usando las funciones groupBy, map, sortedByDescending y last.\n" +
                "Muestra el precio más barato por categoría\n"
    )
    var consulta3 =
    println(consulta3?.joinToString(separator = "\n"))

    println(SeparadorConsulta)
    println(
        "Consulta 4: Usando las funciones groupBy y map.\n" +
                "¿Cuántos productos hay de cada categoría?\n"
    )
    var consulta4 =
    println(consulta4.joinToString(separator = "\n"))

    println(SeparadorConsulta);
    println(
        "Consulta 5: Usando las funciones groupBy, map y filter\n" +
                "Mostrar las categorías que tengan más de 2 productos\n"
    )
    var consulta5 =
    println(consulta5.joinToString(separator = "\n"))

    println(SeparadorConsulta)
    println(
        "Consulta 6: Usando la función map\n" +
                "Mostrar CodArticulo, Descripcion, Precio y Descuento redondeado a 2 decimales,\n" +
                "siendo Descuento el 10% del Precio\n"
    )
    var consulta6: List<Any> =
    println(consulta6.joinToString(separator = "\n"))

    println(SeparadorConsulta)
    println(
        "Consulta 7: Usando las funciones filter, contains y map.\n" +
                "Mostrar CodArticulo, Descripcion y Colores\n" +
                "de los productos de color verde o rojo\n" +
                "(es decir, que contengan alguno de los dos)\n"
    )
    var consulta7: List<Any> =
    println(consulta7.joinToString(separator = "\n"))

    println(SeparadorConsulta)
    println(
        "Consulta 8: Usando las funciones filter y map.\n" +
                "Mostrar CodArticulo, Descripcion y Colores.\n" +
                "de los productos que se fabrican en tres Colores\n"
    )
    var consulta8: List<Any> =
    println(consulta8.joinToString(separator = "\n"))

    println(SeparadorConsulta)
    println(
        "Consulta 9: Usando las funciones filter, map.\n" +
                "Mostrar CodArticulo, Descripcion y Dimensiones\n" +
                "de los productos con espesor de 3 cm\n"
    )
    var consulta9: List<Any> =
    println(consulta9.joinToString(separator = "\n"))

    println(SeparadorConsulta)
    println(
        "Consulta 10: Usando las funciones flatMap, distinct y sortedBy.\n" +
                "Mostrar los colores de productos ordenados y sin repeticiones\n"
    )

    var consulta10 =
    println(consulta10.joinToString(separator = "\n"))

    println(SeparadorConsulta);

}
