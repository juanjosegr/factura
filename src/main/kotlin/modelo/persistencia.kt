package modelo

import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet

val objetoFactura = Factura()
val objetoEmpresa = Empresa()
var connection:Connection = establecerConexion()
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

fun imprimirDatosTabla(nombreTabla: String) {
    val columnas = consultarCabezeraTabla(nombreTabla)

    if (columnas.isEmpty()) {
        println("No se encontraron columnas para la tabla '$nombreTabla'.")
    } else {
        println("Columnas de la tabla '$nombreTabla':")
        println("""
            ---------------
        """.trimIndent())
        for (columna in columnas) {
            println(columna)
        }
        println()
    }
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

fun imprimirDatosConsulta(nombreTabla: String) {
    val datos = hacerConsulta(nombreTabla)

    if (datos.isEmpty()) {
        println("No se encontraron datos para la tabla '$nombreTabla'.")
    } else {
        println("Datos de la tabla '$nombreTabla':")
        println("""
            ---------------
        """.trimIndent())
        println()
        for (fila in datos) {
            for ((columna, valor) in fila) {
                println("$columna: $valor")
            }
            println()
        }
    }
}


fun insterFacutra() {
    objetoFactura.crearFactura()
    connection = establecerConexion()

    val stmt = connection.prepareStatement("INSERT INTO Facturas (ID, cifEmpresa, FechaFactura, ProductoVendido, PrecioProducto, CantidadVendida, PrecioTotal) VALUES (Facturas_ID_Seq.NEXTVAL, ?, ?, ?, ?, ?, ?)")
    stmt.setString(1, objetoFactura.cifEmpresa)
    stmt.setString(2, objetoFactura.FechaFactura)
    stmt.setString(3, objetoFactura.ProductoVendido)
    stmt.setDouble(4, objetoFactura.PrecioProducto)
    stmt.setInt(5, objetoFactura.CantidadVendida)
    stmt.setDouble(6, objetoFactura.PrecioTotal)
    stmt.executeUpdate()
}


fun insertarEmpresa(){
    objetoEmpresa.crearFacturacion()
    connection = establecerConexion()

    val stmt = connection.prepareStatement("INSERT INTO EMPRESAS (cifEmpresa, nombreEmpresa, dueno) VALUES (?, ?, ?)")
    stmt.setString(1, objetoEmpresa.cifEmpresa)
    stmt.setString(2, objetoEmpresa.nombreEmpresa)
    stmt.setString(3, objetoEmpresa.dueno)
    stmt.executeUpdate()
}

