package com.uam.aplicationdemo.tools

import java.math.RoundingMode
import java.util.Stack
import kotlin.math.round

fun evaluate(expresion:String):Double {

    var values = Stack<Double>()
    var ops = Stack<Char>()
    var i = 0

    while(i < expresion.length){
        when (val c = expresion[i]) {
            ' ' -> {
              i++
              continue
            }
            in '0'..'9' -> {
                var sb = StringBuilder()
                while(i < expresion.length && (expresion[i].isDigit()
                            || expresion[i] == '.')) {
                    sb.append(expresion[i])
                    i++
                }
                values.push(sb.toString().toDouble())
                continue
            }
            '(' -> ops.push(c)
            ')' -> {
                while(ops.peek() != '(') {
                    values.push(applyOp(ops.pop()
                        ,values.pop()
                        ,values.pop()))
                }
                ops.pop()
            }
            '+','-','*','÷' -> {
                while(ops.isNotEmpty() && hasPrecedence(c,ops.peek())) {
                    values.push(applyOp(ops.pop(),values.pop(),values.pop()))
                }
                ops.push(c)
            }
        }
        i++
    }
    while(ops.isNotEmpty()) {
        values.push(applyOp(ops.pop(),values.pop(),values.pop()))
    }
    return values.pop()
}

fun hasPrecedence(op1 : Char,op2 :Char) : Boolean {
    if (op2 == '(' || op2 == ')') return false
    if ((op1 == '*' || op1 == '÷')
        && (op2 == '+' || op2 == '-')) return false
    return true
}

fun applyOp(op: Char, b: Double, a: Double): Double {
    return when(op) {
        '+' -> a + b
        '-' -> a - b
        '*' -> a * b
        '÷' -> {
            if (b == 0.0) {
                throw ArithmeticException("Division por cero")
            }
            (a/b).toBigDecimal().setScale(4,RoundingMode.UP).toDouble()
        }
        else -> 0.0
    }
}
