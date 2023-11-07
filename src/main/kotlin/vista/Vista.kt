package vista

import modelo.*

class Vista {
    data class DatosEmpresaModificada(val nuevoNombreEmpresa: String, val nuevoDueno: String)

    fun inicializarMenu(): Int? {

        println(
            """
            Dime que deseas hacer:
            1) Consultar tablas.
            2) Añadir a tablas.
            3) Modificar tablas.
            4) Eliminar de tabla.
            5) Salir.
            
        """.trimIndent()
        )

        return readln().toIntOrNull()
    }

    fun segundaEleccion(): Int? {
        println(
            """
               Dime que base consultar:
               1) Empresas
               2) Facturas
               
           """.trimIndent()
        )

        return readln().toIntOrNull()
    }

    fun seguirCambiarSalir(): Int? {
        println(
            """
            Dime que desea hacer ahora:
            1) Seguir en este directorio.
            2) Cambiar al menu principal.
            3) Salir.
            
        """.trimIndent()
        )
        return readln().toIntOrNull()
    }

    fun salirDelMenu() {
        println("Has elegido la opción salir. \nHasta luego.\n")
    }

    fun imprimirDatosConsulta(nombreTabla: String) {
        val datos = hacerConsulta(nombreTabla)

        if (datos.isEmpty()) {
            println("No se encontraron datos para la tabla '$nombreTabla'.\n")
        } else {
            println("Datos de la tabla '$nombreTabla':")
            println(
                """
            ---------------
        """.trimIndent()
            )
            println()
            for (fila in datos) {
                for ((columna, valor) in fila) {
                    println("$columna: $valor")
                }
                println()
            }
        }
    }

    private fun imprimirCifDeEmpresas() {
        val cifEmpresas = obtenerCIFEmpresas()
        for (cif in cifEmpresas) {
            println(cif)
        }
    }

    fun almacenarCifAModificar(): String {
        imprimirCifDeEmpresas()
        println("Dime la empresa a modificar.\n")
        return readln()
    }

    fun almacenarDatosEmpresaModificar(): DatosEmpresaModificada {
        println("Introduce el nuevo nombre de la empresa:")
        val nuevoNombreEmpresa = readlnOrNull() ?: ""

        println("Introduce el nuevo dueño de la empresa:\n")
        val nuevoDueno = readlnOrNull() ?: ""
        return DatosEmpresaModificada(nuevoNombreEmpresa, nuevoDueno)
    }

    fun modificarIdFactura(): Int {
        val idProducto = obtenerIdFacturas()
        for (id in idProducto) {
            println(id)
        }
        println("Dime el id a modificar.\n")
        return readln().toInt()
    }

    fun obtenerNuevoPrecio(): Int {
        println("Dime el nuevo precio del producto.\n ")
        return readln().toInt()
    }

}