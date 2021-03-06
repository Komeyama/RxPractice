import coroutines.basic.*
import coroutines.flow.FlowExample
import coroutines.flow.ShardFlowExample
import coroutines.flow.StateFlowExample
import operators.aggregate.*
import operators.combine.*
import operators.conversion.*
import operators.generation.*
import operators.restriction.*
import operators.state.*
import operators.utility.*

fun main() {
    setupCoroutines()
    //setupRx()
}

/**
 * Coroutines
 */
private fun setupCoroutines() {
    //setupRunBlocking()
    //setupCoroutinesScope()
    //setupJobExample()
    //setupAsyncExample()
    //setupSuspendExample()
    //setupFlowExample()
    //setupShardFlowExample()
    setupStateFlowExample()
}

private fun setupRunBlocking() {
    val runBlockingExample = RunBlockingExample()
    runBlockingExample.execRunBlocking()
}

private fun setupCoroutinesScope() {
    val coroutinesScopeExample = CoroutinesScopeExample()
    coroutinesScopeExample.execCoroutinesScope()
}

private fun setupJobExample() {
    val jobExample = JobExample()
    jobExample.execJob1()
    jobExample.execJob2()
}

private fun setupAsyncExample() {
    val asyncExample = AsyncExample()
    asyncExample.execAsync()
}

private fun setupSuspendExample() {
    val suspendExample = SuspendExample()
    suspendExample.execSuspend()
}

private fun setupFlowExample() {
    val flowExample = FlowExample()
//    flowExample.execFlow1()
//    flowExample.execFlow2()
//    flowExample.execFlowMap()
//    flowExample.execFlowFilter()
//    flowExample.execFlowDistinctUntilChanged()
//    flowExample.execFlowDrop()
//    flowExample.execFlowTake()
//    flowExample.execFlowTransform()
//    flowExample.execFlowScan()
//    flowExample.execFlowNotBuffer()
//    flowExample.execFlowBuffer()
//    flowExample.execFlowBufferOption()
//    flowExample.execCombinedFlows()
    flowExample.expLaunchIn()
}

private fun setupShardFlowExample() {
    val shardFlowExample = ShardFlowExample()
    //shardFlowExample.execSharedFlow()
    shardFlowExample.execSharedFlowRelay()
}

private fun setupStateFlowExample() {
    val stateFlowExample = StateFlowExample()
    //stateFlowExample.execStateFlowNotExistDelay()
    stateFlowExample.execStateFlowExistDelay()
}

/**
 * Rx
 */
private fun setupRx() {
    // ?????????????????????????????????
    setupJustExample()
    setupFromArrayOperatorExample()
    setupFromCallableOperatorExample()
    setupRangeOperatorExample()
    setupIntervalOperatorExample()
    setupTimeOperatorExample()
    setupDeferOperatorExample()
    setupEmptyOperatorExample()
    setupErrorOperatorExample()

    // ?????????????????????????????????
    setupMapOperatorExample()
    setupFlatMapOperatorExample()
    setupConcatMapOperatorExample()
    setupConcatMapEagerExample()
    setupConcatMapEagerDelayErrorExample()
    setupBufferOperatorExample()
    setupToListOperatorExample()
    setupToMapOperatorExample()
    setupToMultiMapOperatorExample()

    // ?????????????????????????????????
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

    // Flowable/Observable????????????????????????
    setupMergeOperatorExample()
    setupConcatOperatorExample()
    setupConcatEagerOperatorExample()
    setupStartWithOperatorExample()
    setupZipOperatorExample()
    setupCombineLatestOperatorExample()

    // Flowable/Observable?????????????????????????????????
    setupIsEmptyOperatoryExample()
    setupContainsOperatorExample()
    setupAllOperatorExample()
    setupSequenceEqualOperatorExample()
    setupCountOperatorExample()

    //????????????????????????????????????
    setupReduceOperatorExample()
    setupScanOperatorExample()

    //??????????????????????????????????????????
    setupRepeatOperatorExample()
    setupRepeatUntilOperatorExample()
    setupRepeatWhenOperatorExample()
    setupDelayOperatorExample()
    setupDelaySubscriptionOperatorExample()
    seupTimeoutOperatorExample()
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
    //???Call 'executeFromCallable' twice to confirm time data gets generate every time.
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

private fun setupIsEmptyOperatoryExample() {
    val isEmptyOperatoryExample = IsEmptyOperatoryExample()
    isEmptyOperatoryExample.executeEmpty()
}

private fun setupContainsOperatorExample() {
    val containsOperatorExample = ContainsOperatorExample()
    containsOperatorExample.executeContains()
}

private fun setupAllOperatorExample() {
    val allOperatorExample = AllOperatorExample()
    allOperatorExample.executeAll()
}

private fun setupSequenceEqualOperatorExample() {
    val sequenceEqualOperatorExample = SequenceEqualOperatorExample()
    sequenceEqualOperatorExample.executeSequenceEqual()
}

private fun setupCountOperatorExample() {
    val countOperatorExample = CountOperatorExample()
    countOperatorExample.executeCount()
}

private fun setupReduceOperatorExample() {
    val reduceOperatorExample = ReduceOperatorExample()
    reduceOperatorExample.executeReduce()
}

private fun setupScanOperatorExample() {
    val scanOperatorExample = ScanOperatorExample()
    scanOperatorExample.executeScan()
}

private fun setupRepeatOperatorExample() {
    val repeatOperatorExample = RepeatOperatorExample()
    repeatOperatorExample.executeRepeat()
}

private fun setupRepeatUntilOperatorExample() {
    val repeatUntilOperatorExample = RepeatUntilOperatorExample()
    repeatUntilOperatorExample.executeRepeatUntil()
}

private fun setupRepeatWhenOperatorExample() {
    val repeatWhenOperatorExample = RepeatWhenOperatorExample()
    repeatWhenOperatorExample.executeRepeatWhen()
}

private fun setupDelayOperatorExample() {
    val delayOperatorExample = DelayOperatorExample()
    delayOperatorExample.executeDelay()
}

private fun setupDelaySubscriptionOperatorExample() {
    val delaySubscriptionOperatorExample = DelaySubscriptionOperatorExample()
    delaySubscriptionOperatorExample.executeDelaySubscription()
}

private fun seupTimeoutOperatorExample() {
    val timeoutOperatorExample = TimeoutOperatorExample()
    timeoutOperatorExample.executeTimeout()
}