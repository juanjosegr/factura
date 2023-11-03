package modelo

data class empresa(
    var cifEmpresa: String,
    var dueno: String,
    var nombreEmpresa: String
)

class Empresa {


    var cifEmpresa: String = ""
    var dueno: String = ""
    var nombreEmpresa: String = ""

    fun crearFacturacion(){
        println("Dime el cif de la empresa.")
        this.cifEmpresa = readln()
        println("Dime el nombre del dueño.")
        this.dueno = readln()
        println("Dime el nombre de la empresa.")
        this.nombreEmpresa = readln()
    }

}