package modelo

import vista.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet

val objetoFactura = Factura()
val objetoEmpresa = Empresa()
var connection: Connection = establecerConexion()
val vista = Vista()

/**
 * Función que establece una conexión con la base de datos utilizando JDBC.
 *
 * @return Una instancia de `Connection` que representa la conexión a la base de datos.
 */
fun establecerConexion(): Connection {
    // URL de conexión a la base de datos Oracle
    val jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"

    // Se carga el controlador JDBC para Oracle
    Class.forName("oracle.jdbc.driver.OracleDriver")

    // Se muestra un mensaje indicando que la conexión se ha establecido
    println("Conexión establecida")

    // Se devuelve la conexión a la base de datos
    return DriverManager.getConnection(jdbcUrl, "ADA", "ADA")
}

/**
 * Función que consulta y devuelve una lista de nombres de columnas de una tabla en la base de datos.
 *
 * @param nombreTabla El nombre de la tabla cuyas columnas se desean consultar.
 * @return Una lista de cadenas que representan los nombres de las columnas de la tabla.
 */
fun consultarCabezeraTabla(nombreTabla: String): List<String> {
    // Se establece una conexión a la base de datos.
    connection = establecerConexion()

    // Lista para almacenar los nombres de las columnas.
    val columnas = mutableListOf<String>()

    // Consulta SQL para obtener los nombres de las columnas de la tabla especificada.
    val query = "SELECT column_name FROM all_tab_columns WHERE table_name = ?"

    // Se crea un PreparedStatement para ejecutar la consulta.
    val preparedStatement = connection.prepareStatement(query)
    preparedStatement.setString(1, nombreTabla)

    // Se ejecuta la consulta y se obtiene el resultado.
    val resultadoTabla: ResultSet = preparedStatement.executeQuery()

    // Se recorre el resultado y se agregan los nombres de las columnas a la lista.
    while (resultadoTabla.next()) {
        val columnName = resultadoTabla.getString("column_name")
        columnas.add(columnName)
    }

    // Se devuelve la lista de nombres de columnas.
    return columnas
}

/**
 * Función que consulta y devuelve una lista de CIF de empresas desde la base de datos.
 *
 * @return Una lista de cadenas que representan los CIF de empresas obtenidos de la base de datos.
 */
fun obtenerCIFEmpresas(): List<String> {
    // Se establece una conexión a la base de datos.
    val connection = establecerConexion()

    // Lista para almacenar los CIF de las empresas.
    val cifEmpresas = mutableListOf<String>()

    // Consulta SQL para obtener los CIF de empresas desde la tabla EMPRESAS.
    val query = connection.prepareStatement("SELECT cifEmpresa FROM EMPRESAS")
    val result: ResultSet = query.executeQuery()

    // Se recorre el resultado y se agregan los CIF de empresas a la lista.
    while (result.next()) {
        val cif = result.getString("cifEmpresa")
        cifEmpresas.add(cif)
    }

    // Se devuelve la lista de CIF de empresas.
    return cifEmpresas
}

/**
 * Función que consulta y devuelve una lista de ID de facturas, productos vendidos y precios desde la base de datos.
 *
 * @return Una lista de cadenas que representan los ID de facturas y detalles de productos obtenidos de la base de datos.
 */
fun obtenerIdFacturas(): List<String> {
    // Se establece una conexión a la base de datos.
    val connection = establecerConexion()

    // Lista para almacenar los ID de facturas y detalles de productos.
    val idFactura = mutableListOf<String>()

    // Consulta SQL para obtener los ID, productos vendidos y precios desde la tabla FACTURAS.
    val query = connection.prepareStatement("Select ID, ProductoVendido, PrecioProducto FROM FACTURAS")
    val result: ResultSet = query.executeQuery()

    // Se recorre el resultado y se agregan los ID de facturas y detalles de productos a la lista.
    while (result.next()) {
        val id = result.getString("ID")
        val producVendi = result.getString("ProductoVendido")
        val precio = result.getInt("PrecioProducto")
        idFactura.add(producVendi)
        idFactura.add("ID: $id")
        idFactura.add("Precio: $precio€")
        idFactura.add("")
    }

    // Se devuelve la lista de ID de facturas y detalles de productos.
    return idFactura
}

/**
 * Función que realiza una consulta a una tabla de la base de datos y devuelve los datos resultantes.
 *
 * @param nombreTabla El nombre de la tabla en la base de datos a consultar.
 * @return Una lista de mapas donde cada mapa representa una fila de datos de la tabla, con nombres de columnas y valores asociados.
 */
fun hacerConsulta(nombreTabla: String): List<Map<String, Any>> {
    // Se establece una conexión a la base de datos.
    connection = establecerConexion()

    // Lista para almacenar los datos resultantes de la consulta.
    val datos = mutableListOf<Map<String, Any>>()

    // Consulta SQL para obtener todos los datos de la tabla especificada.
    val query = connection.prepareStatement("SELECT * FROM $nombreTabla")
    val result: ResultSet = query.executeQuery()

    // Se obtiene una lista de nombres de columnas de la tabla.
    val nombreColumnas = consultarCabezeraTabla(nombreTabla).toList()

    // Se recorre el resultado y se crea un mapa para cada fila de datos.
    while (result.next()) {
        val fila = mutableMapOf<String, Any>()

        // Se asigna el valor de cada columna a su respectivo nombre de columna en el mapa.
        for (nombreColumna in nombreColumnas) {
            val valor = result.getObject(nombreColumna)
            fila[nombreColumna] = valor
        }

        // Se agrega el mapa (fila de datos) a la lista de datos.
        datos.add(fila)
    }

    // Se devuelve la lista de datos resultantes de la consulta.
    return datos
}

/**
 * Función que inserta una factura en la base de datos.
 *
 * Esta función crea una factura utilizando la instancia de la clase `Factura`, establece una conexión a la base de datos,
 * y luego ejecuta una consulta SQL para insertar los detalles de la factura en la tabla Facturas.
 */
fun insterFacutra() {
    // Se crea una nueva factura utilizando la instancia de la clase Factura.
    objetoFactura.crearFactura()

    // Se establece una conexión a la base de datos.
    connection = establecerConexion()

    // Se prepara una consulta SQL para insertar los detalles de la factura en la tabla Facturas.
    val stmt =
        connection.prepareStatement("INSERT INTO Facturas (ID, cifEmpresa, FechaFactura, ProductoVendido, PrecioProducto, CantidadVendida, PrecioTotal) VALUES (Facturas_ID_Seq.NEXTVAL, ?, ?, ?, ?, ?, ?)")
    stmt.setString(1, objetoFactura.cifEmpresa)
    stmt.setString(2, objetoFactura.fechaFactura)
    stmt.setString(3, objetoFactura.productoVendido)
    stmt.setDouble(4, objetoFactura.precioProducto)
    stmt.setInt(5, objetoFactura.cantidadVendida)
    stmt.setDouble(6, objetoFactura.precioTotal)

    // Se ejecuta la consulta para insertar la factura en la base de datos.
    stmt.executeUpdate()

    // Se cierra el PreparedStatement.
    stmt.close()
}


/**
 * Función que inserta una empresa en la base de datos.
 *
 * Esta función crea una empresa utilizando la instancia de la clase `Empresa`, establece una conexión a la base de datos,
 * y luego ejecuta una consulta SQL para insertar los detalles de la empresa en la tabla EMPRESAS.
 */
fun insertarEmpresa() {
    // Se crea una nueva empresa utilizando la instancia de la clase Empresa.
    objetoEmpresa.crearFacturacion()

    // Se establece una conexión a la base de datos.
    connection = establecerConexion()

    // Se prepara una consulta SQL para insertar los detalles de la empresa en la tabla EMPRESAS.
    val stmt = connection.prepareStatement("INSERT INTO EMPRESAS (cifEmpresa, nombreEmpresa, dueno) VALUES (?, ?, ?)")
    stmt.setString(1, objetoEmpresa.cifEmpresa)
    stmt.setString(2, objetoEmpresa.nombreEmpresa)
    stmt.setString(3, objetoEmpresa.dueno)

    // Se ejecuta la consulta para insertar la empresa en la base de datos.
    stmt.executeUpdate()

    // Se cierra el PreparedStatement.
    stmt.close()
}


/**
 * Función que modifica los datos de una empresa en la base de datos.
 *
 * Esta función obtiene el CIF de la empresa y los nuevos datos de la empresa desde la vista, establece una conexión a la base de datos,
 * y luego ejecuta una consulta SQL para actualizar el nombre y el dueño de la empresa en la tabla EMPRESAS.
 */
fun modificarDatosEmpresa() {
    // Se obtiene el CIF de la empresa a modificar desde la vista.
    val cifDeEmpresa = vista.almacenarCifAModificar()

    // Se obtienen los nuevos datos de la empresa desde la vista.
    val nuevosDatos = vista.almacenarDatosEmpresaModificar()

    // Se establece una conexión a la base de datos.
    connection = establecerConexion()

    // Se prepara una consulta SQL para actualizar el nombre y el dueño de la empresa en la tabla EMPRESAS.
    val query = "UPDATE EMPRESAS SET nombreEmpresa = ?, dueno = ? WHERE cifEmpresa = ?"
    val stmt = connection.prepareStatement(query)

    // Se establecen los nuevos valores para nombre y dueño de la empresa en la consulta.
    stmt.setString(1, nuevosDatos.nuevoNombreEmpresa)
    stmt.setString(2, nuevosDatos.nuevoDueno)
    stmt.setString(3, cifDeEmpresa)

    // Se ejecuta la consulta para actualizar los datos de la empresa en la base de datos.
    stmt.executeUpdate()

    // Se cierra el PreparedStatement.
    stmt.close()

    // Se muestra un mensaje indicando que los datos de la empresa han sido actualizados.
    println("Los datos de la empresa con CIF $cifDeEmpresa han sido actualizados.")
}


/**
 * Función que modifica los datos de una factura en la base de datos.
 *
 * Esta función obtiene el ID de la factura y el nuevo precio desde la vista, establece una conexión a la base de datos,
 * y luego ejecuta una consulta SQL para actualizar el precio del producto y el precio total de la factura en la tabla FACTURAS.
 */
fun modificarDatosFacturas() {
    // Se obtiene el ID de la factura a modificar desde la vista.
    val idDeFactura = vista.modificarIdFactura()

    // Se obtiene el nuevo precio del producto desde la vista.
    val nuevoPrecio = vista.obtenerNuevoPrecio()

    // Se establece una conexión a la base de datos.
    connection = establecerConexion()

    // Se prepara una consulta SQL para actualizar el precio del producto y el precio total de la factura en la tabla FACTURAS.
    val query = "UPDATE FACTURAS SET PrecioProducto = ?, PrecioTotal = CantidadVendida * ? WHERE ID = ?"
    val stmt = connection.prepareStatement(query)

    // Se establecen los nuevos valores de precio del producto y precio total en la consulta.
    stmt.setInt(1, nuevoPrecio)
    stmt.setInt(2, nuevoPrecio)
    stmt.setInt(3, idDeFactura)

    // Se ejecuta la consulta para actualizar los datos de la factura en la base de datos.
    stmt.executeUpdate()

    // Se cierra el PreparedStatement.
    stmt.close()
}

/**
 * Función que elimina una empresa de la base de datos.
 *
 * Esta función obtiene el CIF de la empresa a eliminar desde la vista, establece una conexión a la base de datos y realiza las siguientes acciones:
 * 1. Comprueba si existen facturas relacionadas con la empresa.
 * 2. Si existen facturas relacionadas, muestra un mensaje indicando que la empresa no se puede eliminar debido a las facturas relacionadas.
 * 3. Si no existen facturas relacionadas, elimina la empresa y sus facturas asociadas de la base de datos.
 */
fun eliminarDeEmpresa() {
    // Se establece una conexión a la base de datos.
    connection = establecerConexion()

    // Se obtiene el CIF de la empresa a eliminar desde la vista.
    val cifEmpresaBorrar = vista.almacenarCifAModificar()

    // Se prepara una consulta para contar el número de facturas relacionadas con la empresa.
    val countQuery = "SELECT COUNT(*) FROM FACTURAS WHERE cifEmpresa = ?"
    val countStmt: PreparedStatement = connection.prepareStatement(countQuery)
    countStmt.setString(1, cifEmpresaBorrar)
    val resultSet = countStmt.executeQuery()

    // Se verifica si existen facturas relacionadas con la empresa.
    if (resultSet.next()) {
        val facturaCount = resultSet.getInt(1)

        // Si existen facturas relacionadas, se muestra un mensaje y se evita la eliminación de la empresa.
        if (facturaCount > 0) {
            println("No se puede eliminar la empresa. Tiene $facturaCount facturas relacionadas.\n")
            return
        }
    }

    // Se prepara una consulta SQL para eliminar la empresa de la base de datos.
    val query = "DELETE FROM EMPRESAS WHERE cifEmpresa = ?"
    val stmt: PreparedStatement = connection.prepareStatement(query)
    stmt.setString(1, cifEmpresaBorrar)

    // Se ejecuta la consulta para eliminar la empresa y sus facturas asociadas de la base de datos.
    stmt.executeUpdate()

    // Se cierra el PreparedStatement.
    stmt.close()
}


/**
 * Función que elimina una factura de la base de datos.
 *
 * Esta función obtiene el ID de la factura a eliminar desde la vista, establece una conexión a la base de datos y ejecuta una consulta SQL para eliminar la factura de la tabla FACTURAS.
 */
fun eliminarDeFactura() {
    // Se establece una conexión a la base de datos.
    connection = establecerConexion()

    // Se obtiene el ID de la factura a eliminar desde la vista.
    val idDeFactura = vista.modificarIdFactura()

    // Se prepara una consulta SQL para eliminar la factura de la base de datos.
    val query = "DELETE FROM FACTURAS WHERE ID = ?"
    val stmt: PreparedStatement = connection.prepareStatement(query)
    stmt.setInt(1, idDeFactura)

    // Se ejecuta la consulta para eliminar la factura de la base de datos.
    stmt.executeUpdate()

    // Se cierra el PreparedStatement.
    stmt.close()
}
