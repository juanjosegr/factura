package Controlador

import modelo.*
import vista.*

class Controlador{

    fun llamadaDeMenu(){
        val menu = Menu().inicializarMenu()

        when (menu) {
            1 -> {
                consultarTablas()
            }

            2 -> {
                insertarEnTablas()
            }

            3 -> {
                modificarTablas()
            }

            4 -> {
                eliminarDeTabla()
            }
            else -> {
                salirdelControlador()
            }
        }
    }

    fun consultarTablas(){
        println(
            """
               Dime que base consultar:
               1) Empresas
               2) Facturas
           """.trimIndent()
        )

        val segundaLecutra = readln().toInt()
        when (segundaLecutra) {
            1 -> {
                val consulta = "EMPRESAS"
                imprimirDatosTabla(consulta)
                imprimirDatosConsulta(consulta)
            }

            2 -> {
                val consulta = "FACTURAS"
                imprimirDatosTabla(consulta)
                imprimirDatosConsulta(consulta)
            }

            else -> {
                println("Consulta no disponible.")
            }
        }
    }

    fun insertarEnTablas(){

        println(
            """
               Dime que base consultar:
               1) Empresas
               2) Facturas
           """.trimIndent()
        )

        val segundaLecutra = readln().toIntOrNull()
        when (segundaLecutra) {
            1 -> {
                insertarEmpresa()
            }

            2 -> {
                insterFacutra()
            }
            else -> {
                println("Consulta no disponible.")
            }
        }
    }

    fun modificarTablas(){

        println(
            """
           Dime que base consultar:
           1) Empresas
           2) Facturas
            """.trimIndent()
        )

        val segundaLecutra = readln().toIntOrNull()
        when (segundaLecutra) {
            1 -> {

            }

            2 -> {

            }
            else -> {
                println("Consulta no disponible.")
            }
        }
    }

    fun eliminarDeTabla(){
        println(
            """
               Dime que base consultar:
               1) Empresas
               2) Facturas
           """.trimIndent()
        )

        val segundaLecutra = readln().toIntOrNull()
        when (segundaLecutra) {
            1 -> {

            }

            2 -> {

            }
            else -> {
                println("Consulta no disponible.")
            }
        }
    }

    fun salirdelControlador(){
       Menu().salirDelMenu()
    }

}