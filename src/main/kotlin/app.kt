import operators.FromArrayOperatorExample
import operators.FromCallableOperatorExample
import operators.JustOperatorExample
import operators.RangeOperatorExample

fun main() {
    setupJustExample()
    setupFromArrayOperatorExample()
    setupFromCallableOperatorExample()
    setupRangeOperatorExample()
}

private fun setupJustExample() {
    val justExample = JustOperatorExample()
    justExample.executeJust()
}

private fun setupFromArrayOperatorExample() {
    val fromArrayOperatorExample = FromArrayOperatorExample()
    fromArrayOperatorExample.executeFromArray()
}

private fun setupFromCallableOperatorExample() {
    val fromCallableOperatorExample = FromCallableOperatorExample()
    //　Call 'executeFromCallable' twice to confirm time data gets generate every time.
    fromCallableOperatorExample.executeFromCallable()
    Thread.sleep(100L)
    fromCallableOperatorExample.executeFromCallable()
}

private fun setupRangeOperatorExample() {
    val rangeOperatorExample = RangeOperatorExample()
    rangeOperatorExample.executeRange()
}