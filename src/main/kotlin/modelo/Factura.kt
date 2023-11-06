package modelo

/*
data class dtFactura(var ID:Int,
                   var cifEmpresa:String,
                   var FechaFactura:String,
                   var ProductoVendido:String,
                   var PrecioProducto:Double,
                   var CantidadVendida:Int,
                   var PrecioTotal:Double )*/

class Factura {

    companion object {
        private var contId = 1
    }

    var ID = contId++
    var cifEmpresa:String = ""
    var FechaFactura:String = ""
    var ProductoVendido:String = ""
    var PrecioProducto:Double = 0.0
    var CantidadVendida:Int = 0
    var PrecioTotal:Double = (PrecioProducto * CantidadVendida)


    fun crearFactura(){

        val cifEmpresas = obtenerCIFEmpresas()
        println("Mostrando los CIF de las empresas existente:")
        for ( cif in cifEmpresas){
            println(cif)
        }

        println("ESCRIBE el CIF de la empresa.")
        this.cifEmpresa = readln()

        println("Dime la fecha de la factura.")
        this.FechaFactura= readln()

        println("Dime el producto vendido.")
        this.ProductoVendido= readln()

        println("Dime el precio del producto")
        this.PrecioProducto= readln().toDouble()

        println("Dime la cantidad vendida.")
        this.CantidadVendida = readln().toInt()

        this.PrecioTotal = (PrecioProducto * CantidadVendida)

    }

}