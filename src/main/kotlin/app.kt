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
    setupTakeOperatorExample()
    setupTakeUntilOperatorExample()
    setupTakeWhileOperatorExample()
    setupTakeLastOperatorExample()
    setupSkipOperatorExample()
    setupSkipUntilOperatorExample()
    setupSkipWhileOperatorExample()
    setupSkipLastOperatorExample()
    setupThrottleFirstOperatorExample()
    setupThrottleLastOperatorExample()
    setupThrottleWithTimeoutOperatorExample()
    setupElementAtOperatorExample()
    setupMergeOperatorExample()
    setupConcatOperatorExample()
    setupConcatEagerOperatorExample()
    setupStartWithOperatorExample()
    setupZipOperatorExample()
    setupCombineLatestOperatorExample()
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
    distinctUntilChangedOperatorExample.executeDistinctUntilChangedType1()
    distinctUntilChangedOperatorExample.executeDistinctUntilChangedType2()
}

private fun setupTakeOperatorExample() {
    val takeOperatorExample = TakeOperatorExample()
    takeOperatorExample.executeTake()
}

private fun setupTakeUntilOperatorExample() {
    val takeUntilOperatorExample = TakeUntilOperatorExample()
    takeUntilOperatorExample.executeTakeUntilType1()
    takeUntilOperatorExample.executeTakeUntilType2()
}

private fun setupTakeWhileOperatorExample() {
    val takeWhileOperatorExample = TakeWhileOperatorExample()
    takeWhileOperatorExample.executeTakeWhile()
}

private fun setupTakeLastOperatorExample() {
    val takeLastOperatorExample = TakeLastOperatorExample()
    takeLastOperatorExample.executeTakeLastType1()
    takeLastOperatorExample.executeTakeLastType2()
}

private fun setupSkipOperatorExample() {
    val skipOperatorExample = SkipOperatorExample()
    skipOperatorExample.executeSkip()
}

private fun setupSkipUntilOperatorExample() {
    val skipUntilOperatorExample = SkipUntilOperatorExample()
    skipUntilOperatorExample.executeSkipUntil()
}

private fun setupSkipWhileOperatorExample() {
    val skipWhileOperatorExample = SkipWhileOperatorExample()
    skipWhileOperatorExample.executeSkipWhile()
}

private fun setupSkipLastOperatorExample() {
    val skipLastOperatorExample = SkipLastOperatorExample()
    skipLastOperatorExample.executeSkipLast()
}

private fun setupThrottleFirstOperatorExample() {
    val throttleFirstOperatorExample = ThrottleFirstOperatorExample()
    throttleFirstOperatorExample.executeThrottleFirst()
}

private fun setupThrottleLastOperatorExample() {
    val throttleLastOperatorExample = ThrottleLastOperatorExample()
    throttleLastOperatorExample.executeThrottleLast()
}

private fun setupThrottleWithTimeoutOperatorExample() {
    val throttleWithTimeoutOperatorExample = ThrottleWithTimeoutOperatorExample()
    throttleWithTimeoutOperatorExample.executeThrottleWithTimeout()
}

private fun setupElementAtOperatorExample() {
    val elementAtOperatorExample = ElementAtOperatorExample()
    elementAtOperatorExample.executeElementAt()
}

private fun setupMergeOperatorExample() {
    val mergeOperatorExample = MergeOperatorExample()
    mergeOperatorExample.executeMerge()
}

private fun setupConcatOperatorExample() {
    val concatOperatorExample = ConcatOperatorExample()
    concatOperatorExample.executeConcat()
}

private fun setupConcatEagerOperatorExample() {
    val concatEagerOperatorExample = ConcatEagerOperatorExample()
    concatEagerOperatorExample.executeConcatEager()
}

private fun setupStartWithOperatorExample() {
    val startWithOperatorExample = StartWithOperatorExample()
    startWithOperatorExample.executeStartWith()
}

private fun setupZipOperatorExample() {
    val zipOperatorExample = ZipOperatorExample()
    zipOperatorExample.executeZipType1()
    zipOperatorExample.executeZipType2()
    zipOperatorExample.executeZipType3()
}

private fun setupCombineLatestOperatorExample() {
    val combineLatestOperatorExample = CombineLatestOperatorExample()
    combineLatestOperatorExample.executeCombineLatestType1()
    combineLatestOperatorExample.executeCombineLatestType2()
    combineLatestOperatorExample.executeCombineLatestType3()
}