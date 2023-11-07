package modelo

import vista.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

val objetoFactura = Factura()
val objetoEmpresa = Empresa()
var connection:Connection = establecerConexion()
val vista = Vista()
fun establecerConexion(): Connection {
    val jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"
    Class.forName("oracle.jdbc.driver.OracleDriver")
    println("Conexión establecida")
    return DriverManager.getConnection(jdbcUrl, "ADA", "ADA")
}

fun consultarCabezeraTabla(nombreTabla: String): List<String> {
    connection = establecerConexion()

    val columnas = mutableListOf<String>()

    val query = "SELECT column_name FROM all_tab_columns WHERE table_name = ?"

    val preparedStatement = connection.prepareStatement(query)
    preparedStatement.setString(1, nombreTabla)

    val resultadoTabla: ResultSet = preparedStatement.executeQuery()

    while (resultadoTabla.next()) {
        val columnName = resultadoTabla.getString("column_name")
        columnas.add(columnName)
    }

    return columnas

}

fun obtenerCIFEmpresas(): List<String> {
    val connection = establecerConexion()
    val cifEmpresas = mutableListOf<String>()

    val query = connection.prepareStatement("SELECT cifEmpresa FROM EMPRESAS")
    val result: ResultSet = query.executeQuery()

    while (result.next()) {
        val cif = result.getString("cifEmpresa")
        cifEmpresas.add(cif)
    }

    return cifEmpresas
}

fun hacerConsulta(nombreTabla: String): List<Map<String, Any>> {
    connection = establecerConexion()

    val datos = mutableListOf<Map<String, Any>>()

    val query = connection.prepareStatement("SELECT * FROM $nombreTabla")
    val result: ResultSet = query.executeQuery()

    val nombreColumnas = consultarCabezeraTabla(nombreTabla).toList()

    while (result.next()) {
        val filas = mutableMapOf<String, Any>()
        for (nombreColumna in nombreColumnas) {
            val valor = result.getObject(nombreColumna)
            filas[nombreColumna] = valor
        }
        datos.add(filas)
    }
    return datos
}

fun insterFacutra() {
    objetoFactura.crearFactura()
    connection = establecerConexion()

    val stmt = connection.prepareStatement("INSERT INTO Facturas (ID, cifEmpresa, FechaFactura, ProductoVendido, PrecioProducto, CantidadVendida, PrecioTotal) VALUES (Facturas_ID_Seq.NEXTVAL, ?, ?, ?, ?, ?, ?)")
    stmt.setString(1, objetoFactura.cifEmpresa)
    stmt.setString(2, objetoFactura.fechaFactura)
    stmt.setString(3, objetoFactura.productoVendido)
    stmt.setDouble(4, objetoFactura.precioProducto)
    stmt.setInt(5, objetoFactura.cantidadVendida)
    stmt.setDouble(6, objetoFactura.precioTotal)
    stmt.executeUpdate()
    stmt.close()
}

fun insertarEmpresa(){
    objetoEmpresa.crearFacturacion()
    connection = establecerConexion()

    val stmt = connection.prepareStatement("INSERT INTO EMPRESAS (cifEmpresa, nombreEmpresa, dueno) VALUES (?, ?, ?)")
    stmt.setString(1, objetoEmpresa.cifEmpresa)
    stmt.setString(2, objetoEmpresa.nombreEmpresa)
    stmt.setString(3, objetoEmpresa.dueno)
    stmt.executeUpdate()
    stmt.close()
}

fun modificarDatosEmpresa(){
    val cifDeEmpresa = vista.almacenarCifAModificar()
    val nuevosDatos = vista.almacenarDatosEmpresaModificar()

    connection = establecerConexion()

    val query = "UPDATE EMPRESAS SET nombreEmpresa = ?, dueno = ? WHERE cifEmpresa = ?"
    val stmt = connection.prepareStatement(query)

    stmt.setString(1, nuevosDatos.nuevoNombreEmpresa)
    stmt.setString(2, nuevosDatos.nuevoDueno)
    stmt.setString(3, cifDeEmpresa)

    stmt.executeUpdate()
    stmt.close()

    println("Los datos de la empresa con CIF $cifDeEmpresa han sido actualizados.")
}

fun modificarDatosFacturas(){


}

fun eliminarDeEmpresa(){

}

fun eliminarDeFactura(){

}