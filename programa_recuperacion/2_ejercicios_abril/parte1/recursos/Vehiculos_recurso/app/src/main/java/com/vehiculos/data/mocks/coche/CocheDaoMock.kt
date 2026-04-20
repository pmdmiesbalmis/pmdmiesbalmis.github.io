package com.vehiculos.data.mocks.coche

import kotlinx.coroutines.coroutineScope

class CocheDaoMock {
    private val coches = listOf(
        CocheMock(
            id = 1,
            fabricante = "Volkswagen",
            modelo = "Passat",
            año = 2016,
            precio = 43210f,
            porcentajeDescuento = 0,
            descripcion = "Volkswagen Passat 2.0 TDI 150CV BMT Advance DSG 4p",
            foto = "http://10.0.2.2/galeria_coches/foto_1.png"
        ),
        CocheMock(
            id = 2,
            fabricante = "Ford",
            modelo = "Mustang",
            año = 2017,
            precio = 55000f,
            porcentajeDescuento = 10,
            descripcion = "Ford Mustang 5.0 Ti-VCT V8 GT 2p",
            foto = "http://10.0.2.2/galeria_coches/foto_2.png"
        ),
        CocheMock(
            id = 3,
            fabricante = "Audi",
            modelo = "A4",
            año = 2018,
            precio = 46000f,
            porcentajeDescuento = 0,
            descripcion = "Audi A4 2.0 TDI 110kW (150CV) S line edition 4p",
            foto = "http://10.0.2.2/galeria_coches/foto_3.png"
        ),
        CocheMock(
            id = 4,
            fabricante = "BMW",
            modelo = "X5",
            año = 2019,
            precio = 86800f,
            porcentajeDescuento = 20,
            descripcion = "BMW X5 xDrive30d 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_4.png"
        ),
        CocheMock(
            id = 5,
            fabricante = "Mercedes",
            modelo = "Clase C",
            año = 2020,
            precio = 50000f,
            porcentajeDescuento = 0,
            descripcion = "Mercedes-Benz Clase C C 220d Estate 9G-Tronic 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_5.png"
        ),
        CocheMock(
            id = 6,
            fabricante = "Volkswagen",
            modelo = "Golf",
            año = 2021,
            precio = 29000f,
            porcentajeDescuento = 0,
            descripcion = "Volkswagen Golf 1.5 TSI 96kW (130CV) Sport 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_6.png"
        ),
        CocheMock(
            id = 7,
            fabricante = "Ford",
            modelo = "Focus",
            año = 2022,
            precio = 30000f,
            porcentajeDescuento = 5,
            descripcion = "Ford Focus 1.0 Ecoboost 92kW (125CV) ST-Line 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_7.png"
        ),
        CocheMock(
            id = 8,
            fabricante = "Audi",
            modelo = "A3",
            año = 2023,
            precio = 32000f,
            porcentajeDescuento = 0,
            descripcion = "Audi A3 Sportback 30 TFSI 81kW (110CV) S tronic 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_8.png"
        ),
        CocheMock(
            id = 9,
            fabricante = "BMW",
            modelo = "Serie 3",
            año = 2020,
            precio = 45200f,
            porcentajeDescuento = 0,
            descripcion = "BMW Serie 3 320d 140kW (190CV) Touring 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_9.png"
        ),
        CocheMock(
            id = 10,
            fabricante = "Mercedes",
            modelo = "Clase A",
            año = 2021,
            precio = 38200f,
            porcentajeDescuento = 0,
            descripcion = "Mercedes-Benz Clase A A 200d 7G-DCT 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_10.png"
        ),
        CocheMock(
            id = 11,
            fabricante = "Audi",
            modelo = "e-tron",
            año = 2022,
            precio = 87310f,
            porcentajeDescuento = 10,
            descripcion = "Audi e-tron 55 quattro 265kW (360CV) Advanced edition 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_11.png"
        ),
        CocheMock(
            id = 12,
            fabricante = "BMW",
            modelo = "Serie 5",
            año = 2023,
            precio = 60000f,
            porcentajeDescuento = 20,
            descripcion = "BMW Serie 5 520d 140kW (190CV) Touring 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_12.png"
        ),
        CocheMock(
            id = 13,
            fabricante = "Mercedes",
            modelo = "Clase G",
            año = 2020,
            precio = 148225f,
            porcentajeDescuento = 0,
            descripcion = "Mercedes-Benz Clase G G 350d 4Matic 180kW (245CV) 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_13.png"
        ),
        CocheMock(
            id = 14,
            fabricante = "Volkswagen",
            modelo = "Tiguan",
            año = 2021,
            precio = 39000f,
            porcentajeDescuento = 0,
            descripcion = "Volkswagen Tiguan 2.0 TDI 110kW (150CV) 4Motion DSG 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_14.png"
        ),
        CocheMock(
            id = 15,
            fabricante = "Ford",
            modelo = "Kuga",
            año = 2022,
            precio = 38250f,
            porcentajeDescuento = 0,
            descripcion = "Ford Kuga 1.5 EcoBoost 110kW (150CV) ST-Line 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_15.png"
        ),
        CocheMock(
            id = 16,
            fabricante = "Audi",
            modelo = "Q3",
            año = 2023,
            precio = 43270f,
            porcentajeDescuento = 0,
            descripcion = "Audi Q3 35 TFSI 110kW (150CV) S tronic 5p",
            foto = "http://10.0.2.2/galeria_coches/foto_16.png"
        ),
        CocheMock(
            id = 17,
            fabricante = "Ford",
            modelo = "Mondeo",
            año = 2021,
            precio = 32000f,
            porcentajeDescuento = 10,
            descripcion = "Ford Mondeo 2.0 TDCi 110kW (150CV) Titanium 5",
            foto = "http://10.0.2.2/galeria_coches/foto_17.png"
        )
    )

    suspend fun get(): List<CocheMock> = coroutineScope {
        coches
    }
    suspend fun get(id: Int): CocheMock = coroutineScope {
        coches.first { it.id == id }
    }
    suspend fun getOrdenadosPrecio(): List<CocheMock> = coroutineScope {
        coches.sortedBy { it.precio }
    }
    suspend fun getOertas(): List<CocheMock> = coroutineScope {
        coches.filter { it.porcentajeDescuento > 0 }
    }

    suspend fun getOertasOrdenadasPrecio(): List<CocheMock> = coroutineScope {
        coches.filter { it.porcentajeDescuento > 0 }.sortedBy { it.precio }
    }

    suspend fun count(): Int = coroutineScope { coches.size }

    suspend fun insert(coche: CocheMock) = coroutineScope {
        val newId = (coches.maxOfOrNull { it.id } ?: 0) + 1
        coches.toMutableList().add(coche.copy(id = newId))
    }

    suspend fun delete(coche: CocheMock) = coroutineScope {
        coches.toMutableList().removeIf { it.id == coche.id }
    }

    suspend fun update(coche: CocheMock) = coroutineScope {
        val index = coches.indexOfFirst { it.id == coche.id }
        if (index != -1) {
            coches.toMutableList()[index] = coche
        }
    }
}