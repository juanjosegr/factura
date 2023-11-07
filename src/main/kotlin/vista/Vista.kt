package vista

import modelo.*

/**
 * Clase Vista que gestiona la parte visual del menú y las interacciones del usuario.
 */
class Vista {

    /**
     * Clase interna que representa datos de una empresa modificada.
     *
     * @property nuevoNombreEmpresa El nuevo nombre de la empresa.
     * @property nuevoDueno El nuevo dueño de la empresa.
     */
    data class DatosEmpresaModificada(val nuevoNombreEmpresa: String, val nuevoDueno: String)

    /**
     * Función que muestra un menú de opciones y permite al usuario seleccionar una de ellas.
     *
     * @return Un entero que representa la opción seleccionada por el usuario o nulo si no es un número válido.
     */
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

    /**
     * Función que muestra un menú de segunda elección y permite al usuario seleccionar una opción.
     *
     * @return Un entero que representa la opción seleccionada por el usuario o nulo si no es un número válido.
     */
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

    /**
     * Función que muestra un mensaje indicando que la consulta no está disponible.
     */
    fun ConsultaNoDisponible() {
        println("Consulta no disponible.")
    }

    /**
     * Función que muestra un menú de opciones para seguir, cambiar al menú principal o salir.
     *
     * @return Un entero que representa la opción seleccionada por el usuario o nulo si no es un número válido.
     */
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

    /**
     * Función que muestra un mensaje de despedida cuando el usuario elige salir del menú.
     */
    fun salirDelMenu() {
        println("Has elegido la opción salir. \nHasta luego.\n")
    }

    /**
     * Función que imprime datos de una tabla especificada por su nombre.
     *
     * @param nombreTabla El nombre de la tabla a consultar.
     */
    fun imprimirDatosConsulta(nombreTabla: String) {
        // Se obtienen los datos de la tabla utilizando la función hacerConsulta.
        val datos = hacerConsulta(nombreTabla)

        if (datos.isEmpty()) {
            // Si no se encuentran datos, se muestra un mensaje indicando que no se encontraron datos.
            println("No se encontraron datos para la tabla '$nombreTabla'.\n")
        } else {
            // Si se encuentran datos, se imprime el encabezado y los datos de la tabla.
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

    /**
     * Función que imprime los CIF de las empresas obtenidos a partir de la base de datos.
     */
    private fun imprimirCifDeEmpresas() {
        // Se obtienen los CIF de las empresas utilizando la función obtenerCIFEmpresas.
        val cifEmpresas = obtenerCIFEmpresas()
        // Se imprime cada CIF de las empresas.
        for (cif in cifEmpresas) {
            println(cif)
        }
    }

    /**
     * Función que muestra los CIF de las empresas y solicita al usuario que introduzca el CIF de la empresa a modificar.
     *
     * @return El CIF de la empresa a modificar ingresado por el usuario.
     */
    fun almacenarCifAModificar(): String {
        imprimirCifDeEmpresas()
        println("Dime la empresa a modificar.\n")
        return readln()
    }

    /**
     * Función que solicita al usuario introducir el nuevo nombre de la empresa y el nuevo dueño de la empresa.
     *
     * @return Una instancia de DatosEmpresaModificada que contiene el nuevo nombre de la empresa y el nuevo dueño.
     */
    fun almacenarDatosEmpresaModificar(): DatosEmpresaModificada {
        println("Introduce el nuevo nombre de la empresa:")
        val nuevoNombreEmpresa = readlnOrNull() ?: ""

        println("Introduce el nuevo dueño de la empresa:\n")
        val nuevoDueno = readlnOrNull() ?: ""
        return DatosEmpresaModificada(nuevoNombreEmpresa, nuevoDueno)
    }

    /**
     * Función que muestra los ID de las facturas y solicita al usuario que introduzca el ID de la factura a modificar.
     *
     * @return El ID de la factura a modificar ingresado por el usuario.
     */
    fun modificarIdFactura(): Int {
        val idProducto = obtenerIdFacturas()
        for (id in idProducto) {
            println(id)
        }
        println("Dime el id a modificar.\n")
        return readln().toInt()
    }

    /**
     * Función que solicita al usuario introducir el nuevo precio del producto.
     *
     * @return El nuevo precio del producto ingresado por el usuario.
     */
    fun obtenerNuevoPrecio(): Int {
        println("Dime el nuevo precio del producto.\n ")
        return readln().toInt()
    }

}