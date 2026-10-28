class Operacion(var num1: Double,var num2: Double,var operador:String)
fun comprobaar0(v: String): Boolean{
    if (v=="Error no se puede dividir entre 0"){
        return true
    }
    return false
}
fun comprobarOp(v: String): Boolean{
    if(v.endsWith("+")||v.endsWith("-")||v.endsWith("X")||v.endsWith("/")||!v.isNotEmpty()){
        return true
    }
    return false
}
fun resultadoOperacion(o:String): String{
    var num1=0.0
    var num2=0.0
    var l=""
    var n=0
    var operacion= Operacion(num1=num1,num2=num2, operador = l)
    if (o.startsWith("-")||o.startsWith("+")){n=1}
    for (i in n..o.length-1){
        if (o[i]==('-')||o[i]==('+')||o[i]==('X')||o[i]==('/')){
            num1=o.substring(0,i).toDouble()
            num2=o.substring(i+1).toDouble()
            l=o.get(i).toString()
            operacion=Operacion(num1 = num1, num2 = num2, operador = l)
            break
        }
    }
    var resultado=resolverOp(operacion)
    if(resultado.isInfinite()||resultado.isNaN()){
        return "Error no se puede dividir entre 0"
    }
    if(resultado.toString().endsWith("0")){
        return resultado.toInt().toString()
    }else{return resultado.toString()}

}
fun resolverOp(operacion: Operacion): Double{
    return when(operacion.operador){
        "X"->operacion.num1*operacion.num2
        "/"-> operacion.num1/operacion.num2
        "+"->operacion.num1+operacion.num2
        "-"->operacion.num1-operacion.num2
        else -> Double.NaN
    }
}