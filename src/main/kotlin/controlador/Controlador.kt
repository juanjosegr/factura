package controlador

import modelo.*
import vista.*

class Controlador {

    private val vista = Vista()
    private var menuActual: Menu = Menu.MenuPrincipal
    // Inicialmente, estás en el menú principal
    enum class Menu {
        MenuPrincipal,
        ConsultarTabla,
        InsertarTablas,
        ModificarTablas,
        EliminarTablas
    }
    fun llamadaDeMenu() {

        when (vista.inicializarMenu()) {
            1 -> {
                menuActual = Menu.ConsultarTabla
                consultarTablas()
            }

            2 -> {
                menuActual = Menu.InsertarTablas
                insertarEnTablas()
            }

            3 -> {
                menuActual = Menu.ModificarTablas
                modificarTablas()
            }

            4 -> {
                menuActual = Menu.EliminarTablas
                eliminarDeTabla()
            }

            else -> {
                salirdelControlador()
            }
        }
    }

    private fun consultarTablas() {
        when (vista.segundaEleccion()) {
            1 -> {
                vista.imprimirDatosConsulta("EMPRESAS")
                seguirCambiaoSalir()
            }

            2 -> {
                vista.imprimirDatosConsulta("FACTURAS")
                seguirCambiaoSalir()
            }

            else -> {
                println("Consulta no disponible.")
                seguirCambiaoSalir()
            }
        }
    }

    private fun insertarEnTablas() {
        when (vista.segundaEleccion()) {
            1 -> {
                insertarEmpresa()
                seguirCambiaoSalir()
            }

            2 -> {
                insterFacutra()
                seguirCambiaoSalir()
            }

            else -> {
                println("Consulta no disponible.")
                seguirCambiaoSalir()
            }
        }
    }

    private fun modificarTablas() {
        when (vista.segundaEleccion()) {
            1 -> {
                modificarDatosEmpresa()
                seguirCambiaoSalir()
            }

            2 -> {
                modificarDatosFacturas()
                seguirCambiaoSalir()
            }

            else -> {
                println("Consulta no disponible.")
                seguirCambiaoSalir()
            }
        }
    }

    private fun eliminarDeTabla() {
        when (vista.segundaEleccion()) {
            1 -> {
                eliminarDeEmpresa()
                seguirCambiaoSalir()
            }

            2 -> {
                eliminarDeFactura()
                seguirCambiaoSalir()
            }

            else -> {
                println("Consulta no disponible.")
                seguirCambiaoSalir()
            }
        }
    }

    private fun salirdelControlador() {
        Vista().salirDelMenu()
    }

    private fun seguirCambiaoSalir() {
        when (vista.seguirCambiarSalir()) {
            1 -> {
                when (menuActual) {
                    Menu.ConsultarTabla -> consultarTablas()
                    Menu.InsertarTablas -> insertarEnTablas()
                    Menu.ModificarTablas -> modificarTablas()
                    Menu.EliminarTablas -> eliminarDeTabla()
                    else -> salirdelControlador()
                }
            }

            2 -> {
                llamadaDeMenu()
            }

            else -> {
                salirdelControlador()
            }
        }

    }

}