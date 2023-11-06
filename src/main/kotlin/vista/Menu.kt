package vista

class Menu {
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

    fun salirDelMenu(){
        println("Has elegido la opción salir. \nHasta luego.")
    }


}