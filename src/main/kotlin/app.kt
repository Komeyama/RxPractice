import operators.FromArrayOperatorExample
import operators.JustOperatorExample

fun main() {
    initJustExample()
    initFromArrayOperatorExample()
}

private fun initJustExample() {
    val justExample = JustOperatorExample()
    justExample.executeJust()
}

private fun initFromArrayOperatorExample() {
    val fromArrayOperatorExample = FromArrayOperatorExample()
    fromArrayOperatorExample.executeFromArray()
}