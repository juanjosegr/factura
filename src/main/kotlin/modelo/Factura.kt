package modelo

class Factura {

    companion object {
        private var contId = 1
    }

    var id = contId++
    var cifEmpresa:String = ""
    var fechaFactura:String = ""
    var productoVendido:String = ""
    var precioProducto:Double = 0.0
    var cantidadVendida:Int = 0
    var precioTotal:Double = (precioProducto * cantidadVendida)


    fun crearFactura(){

        val cifEmpresas = obtenerCIFEmpresas()
        println("Mostrando los CIF de las empresas existente:")
        for ( cif in cifEmpresas){
            println(cif)
        }

        println("ESCRIBE el CIF de la empresa.")
        this.cifEmpresa = readln()

        println("Dime la fecha de la factura.")
        this.fechaFactura= readln()

        println("Dime el producto vendido.")
        this.productoVendido= readln()

        println("Dime el precio del producto")
        this.precioProducto= readln().toDouble()

        println("Dime la cantidad vendida.")
        this.cantidadVendida = readln().toInt()

        this.precioTotal = (precioProducto * cantidadVendida)

    }

}