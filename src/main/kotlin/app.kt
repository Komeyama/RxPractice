import operators.*

fun main() {
    setupJustExample()
    setupFromArrayOperatorExample()
    setupFromCallableOperatorExample()
    setupRangeOperatorExample()
    setupIntervalOperatorExample()
    setupTimeOperatorExample()
    setupDeferOperatorExample()
    setupEmptyOperatorExample()
    setupErrorOperatorExample()
    setupMapOperatorExample()
    setupFlatMapOperatorExample()
    setupConcatMapOperatorExample()
    setupConcatMapEagerExample()
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

private fun setupIntervalOperatorExample() {
    val intervalOperatorExample = IntervalOperatorExample()
    intervalOperatorExample.executeInterval()
}

private fun setupTimeOperatorExample() {
    val intervalOperatorExample = TimerOperatorExample()
    intervalOperatorExample.executeTimer()
}

private fun setupDeferOperatorExample() {
    val deferOperatorExample = DeferOperatorExample()
    deferOperatorExample.executeDefer()
}

private fun setupEmptyOperatorExample() {
    val emptyOperatorExample = EmptyOperatorExample()
    emptyOperatorExample.executeEmpty()
}

private fun setupErrorOperatorExample() {
    val errorOperatorExample = ErrorOperatorExample()
    errorOperatorExample.executeError()
}

private fun setupMapOperatorExample() {
    val mapOperatorExample = MapOperatorExample()
    mapOperatorExample.executeMap()
}

private fun setupFlatMapOperatorExample() {
    val flatMapOperatorExample = FlatMapOperatorExample()
    flatMapOperatorExample.executeFlatMapType1()
    flatMapOperatorExample.executeFlatMapType2()
    flatMapOperatorExample.executeFlatMapType3()
}

private fun setupConcatMapOperatorExample() {
    val concatMapOperatorExample = ConcatMapOperatorExample()
    concatMapOperatorExample.executeConcatMap()
}

private fun setupConcatMapEagerExample() {
    val concatMapEagerOperatorExample = ConcatMapEagerOperatorExample()
    concatMapEagerOperatorExample.executeConcatMapEager()
}