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
    setupConcatMapEagerDelayErrorExample()
    setupBufferOperatorExample()
    setupToListOperatorExample()
    setupToMapOperatorExample()
    setupToMultiMapOperatorExample()
    setupFilterOperatorExample()
    setupDistinctOperatorExample()
    setupDistinctUntilChangedOperatorExample()
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

private fun setupConcatMapEagerDelayErrorExample() {
    val concatMapEagerDelayErrorOperator = ConcatMapEagerDelayErrorOperator()
    concatMapEagerDelayErrorOperator.executeConcatMapEagerDelayError()
}

private fun setupBufferOperatorExample() {
    val bufferOperatorExample = BufferOperatorExample()
    bufferOperatorExample.executeBufferType1()
    bufferOperatorExample.executeBufferType2()
}

private fun setupToListOperatorExample() {
    val toListOperatorExample = ToListOperatorExample()
    toListOperatorExample.executeToList()
}

private fun setupToMapOperatorExample() {
    val toMapOperationException = ToMapOperatorExample()
    toMapOperationException.executeToMap()
}

private fun setupToMultiMapOperatorExample() {
    val toMultiMapOperatorExample = ToMultiMapOperatorExample()
    toMultiMapOperatorExample.executeToMultiMapOperator()
}

private fun setupFilterOperatorExample() {
    val filterOperatorExample = FilterOperatorExample()
    filterOperatorExample.executeFilter()
}

private fun setupDistinctOperatorExample() {
    val distinctOperatorExample = DistinctOperatorExample()
    distinctOperatorExample.executeDistinct()
}

private fun setupDistinctUntilChangedOperatorExample() {
    val distinctUntilChangedOperatorExample = DistinctUntilChangedOperatorExample()
    distinctUntilChangedOperatorExample.executeDistinctUntilChanged()
}