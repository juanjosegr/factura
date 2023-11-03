package modelo

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

val faactura = Factura()
val facturacion = Empresa()
var connection = establecerConexion()
fun establecerConexion(): Connection {
    val jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe"
    Class.forName("oracle.jdbc.driver.OracleDriver")
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

    val query = connection.prepareStatement("SELECT cifEmpresa FROM Empresas")
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
    faactura.crearFactura()
    connection = establecerConexion()

    val stmt = connection.prepareStatement("INSERT INTO Facturas (ID, cifEmpresa, FechaFactura, ProductoVendido, PrecioProducto, CantidadVendida, PrecioTotal) VALUES (Facturas_ID_Seq.NEXTVAL, ?, ?, ?, ?, ?, ?)")
    stmt.setString(1, faactura.cifEmpresa)
    stmt.setString(2, faactura.FechaFactura)
    stmt.setString(3, faactura.ProductoVendido)
    stmt.setDouble(4, faactura.PrecioProducto)
    stmt.setInt(5, faactura.CantidadVendida)
    stmt.setDouble(6, faactura.PrecioTotal)
    stmt.executeUpdate()
}


fun insertarFacturacion(){
    facturacion.crearFacturacion()
    connection = establecerConexion()

    val stmt = connection.prepareStatement("INSERT INTO EMPRESAS (cifEmpresa, nombreEmpresa, dueno) VALUES (?, ?, ?)")
    stmt.setString(1, facturacion.cifEmpresa)
    stmt.setString(2, facturacion.nombreEmpresa)
    stmt.setString(3, facturacion.dueno)
    stmt.executeUpdate()
}

