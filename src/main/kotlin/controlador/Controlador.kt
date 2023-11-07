package controlador

import modelo.*
import vista.*

/**
 * Clase Controlador que gestiona la lógica del menú y las interacciones del usuario.
 */
class Controlador {
    /**
     * Propiedad que almacena una instancia de la clase Vista para interactuar con la interfaz de usuario.
     */
    private val vista = Vista()

    /**
     * Propiedad que almacena el menú actual. Inicialmente, el valor es Menu.MenuPrincipal.
     */
    private var menuActual: Menu = Menu.MenuPrincipal
    // Inicialmente, estás en el menú principal

    /**
     * Enumeración que representa los diferentes menús disponibles.
     */
    enum class Menu {
        MenuPrincipal,
        ConsultarTabla,
        InsertarTablas,
        ModificarTablas,
        EliminarTablas
    }

    /**
     * Función que maneja las llamadas de menú.
     */
    fun llamadaDeMenu() {
        // Se llama a la función inicializarMenu de la vista para mostrar opciones al usuario.
        when (vista.inicializarMenu()) {
            1 -> {
                // Si el usuario selecciona 1, se cambia el menú actual al de "Consultar Tablas" y se llama a la función correspondiente.
                menuActual = Menu.ConsultarTabla
                consultarTablas()
            }

            2 -> {
                // Si el usuario selecciona 2, se cambia el menú actual al de "Insertar Tablas" y se llama a la función correspondiente.
                menuActual = Menu.InsertarTablas
                insertarEnTablas()
            }

            3 -> {
                // Si el usuario selecciona 3, se cambia el menú actual al de "Modificar Tablas" y se llama a la función correspondiente.
                menuActual = Menu.ModificarTablas
                modificarTablas()
            }

            4 -> {
                // Si el usuario selecciona 4, se cambia el menú actual al de "Eliminar Tablas" y se llama a la función correspondiente.
                menuActual = Menu.EliminarTablas
                eliminarDeTabla()
            }

            else -> {
                // Si el usuario selecciona una opción no válida, se llama a la función para salir del controlador.
                salirdelControlador()
            }
        }
    }

    /**
     * Función que gestiona la consulta de tablas.
     */
    private fun consultarTablas() {
        when (vista.segundaEleccion()) {
            1 -> {
                // Si el usuario selecciona 1, se imprime la consulta de la tabla "EMPRESAS".
                vista.imprimirDatosConsulta("EMPRESAS")
                seguirCambiaoSalir()
            }

            2 -> {
                // Si el usuario selecciona 2, se imprime la consulta de la tabla "FACTURAS".
                vista.imprimirDatosConsulta("FACTURAS")
                seguirCambiaoSalir()
            }

            else -> {
                // Si el usuario selecciona una opción no válida, se muestra un mensaje de "Consulta no disponible".
                vista.ConsultaNoDisponible()
                seguirCambiaoSalir()
            }
        }
    }

    /**
     * Función que gestiona la inserción de datos en tablas.
     */
    private fun insertarEnTablas() {
        when (vista.segundaEleccion()) {
            1 -> {
                // Si el usuario selecciona 1, se llama a la función para insertar una empresa.
                insertarEmpresa()
                seguirCambiaoSalir()
            }

            2 -> {
                // Si el usuario selecciona 2, se llama a la función para insertar una factura.
                insterFacutra()
                seguirCambiaoSalir()
            }

            else -> {
                // Si el usuario selecciona una opción no válida, se muestra un mensaje de "Consulta no disponible."
                vista.ConsultaNoDisponible()
                seguirCambiaoSalir()
            }
        }
    }

    /**
     * Función que gestiona la modificación de datos en tablas.
     */
    private fun modificarTablas() {
        when (vista.segundaEleccion()) {
            1 -> {
                // Si el usuario selecciona 1, se llama a la función para modificar datos de una empresa.
                modificarDatosEmpresa()
                seguirCambiaoSalir()
            }

            2 -> {
                // Si el usuario selecciona 2, se llama a la función para modificar datos de una factura.
                modificarDatosFacturas()
                seguirCambiaoSalir()
            }

            else -> {
                // Si el usuario selecciona una opción no válida, se muestra un mensaje de "Consulta no disponible."
                vista.ConsultaNoDisponible()
                seguirCambiaoSalir()
            }
        }
    }

    /**
     * Función que gestiona la eliminación de datos en tablas.
     */
    private fun eliminarDeTabla() {
        when (vista.segundaEleccion()) {
            1 -> {
                // Si el usuario selecciona 1, se llama a la función para eliminar datos de una empresa.
                eliminarDeEmpresa()
                seguirCambiaoSalir()
            }

            2 -> {
                // Si el usuario selecciona 2, se llama a la función para eliminar datos de una factura.
                eliminarDeFactura()
                seguirCambiaoSalir()
            }

            else -> {
                // Si el usuario selecciona una opción no válida, se muestra un mensaje de "Consulta no disponible."
                vista.ConsultaNoDisponible()
                seguirCambiaoSalir()
            }
        }
    }

    /**
     * Función que permite salir del controlador y finalizar la aplicación.
     */
    private fun salirdelControlador() {
        Vista().salirDelMenu()
    }

    /**
     * Función que decide si el usuario desea seguir, cambiar al menú principal o salir.
     */
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