class Operacion(var num1: Double,var num2: Double,var operador:String)
fun resultadoOperacion(o:String): String{
    var num1=0.0
    var num2=0.0
    var l=""
    var operacion= Operacion(num1=num1,num2=num2, operador = l)
    for (i in 0..o.length){
        if (o.get(i).equals('-')||o.get(i).equals('+')||o.get(i).equals('X')||o.get(i).equals('/')){
            num1=o.substring(0,i-1).toDouble()
            num2=o.substring(i+1,o.lastIndex).toDouble()
            l=o.get(i).toString()
            operacion=Operacion(num1 = num1, num2 = num2, operador = l)
        }
    }
    return resolverOp(operacion).toString()
}
fun resolverOp(operacion: Operacion): Double{
    return when(operacion.operador){
        "X"->operacion.num1*operacion.num2
        "/"->operacion.num1/operacion.num2
        "+"->operacion.num1+operacion.num2
        "-"->operacion.num1-operacion.num2
        else -> Double.NaN
    }
}