package coroutines.flow

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

class FlowExample {

    val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("mm:ss.SSS")

    /**
     * start time:03:28.832
     * collect1:1, 03:29.185, threadName: main
     * emit:1, 03:29.186, threadName: main
     * collect2:1, 03:29.188, threadName: main
     * emit:1, 03:29.188, threadName: main
     * collect1:10, 03:30.188, threadName: main
     * emit:10, 03:30.189, threadName: main
     * finish collect1!: 03:31.194
     */
    fun execFlow1() {
        println("start time:${LocalTime.now().format(formatter)}")

        val flow = flow {
            val threadName = Thread.currentThread().name
            val values = listOf(1, 10)
            values.forEach {
                emit(it)
                println("emit:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                delay(1000L)
            }
        }

        runBlocking {
            launch {
                flow.collect {
                    val threadName = Thread.currentThread().name
                    println("collect1:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                }
                println("finish collect1!: ${LocalTime.now().format(formatter)}")
            }

            val job = launch {
                flow.collect {
                    val threadName = Thread.currentThread().name
                    println("collect2:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                }
                println("finish collect2!: ${LocalTime.now().format(formatter)}")
            }
            delay(1000L)
            job.cancel()
        }
    }

    /**
     * collect:1, 10:39.329, threadName: main
     * emit:1, 10:39.331, threadName: main
     * catch:java.lang.Exception: error!, 10:40.340, threadName: main
     */
    fun execFlow2() {
        val flow = flow {
            val threadName = Thread.currentThread().name
            val values = listOf(1, 10)
            values.forEach {
                emit(it)
                println("emit:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                delay(1000L)
                throw Exception("error!")
            }
        }

        runBlocking {
            val threadName = Thread.currentThread().name
            flow.catch {
                println("catch:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }
    }

    /**
     * collect:2, 06:43.711, threadName: main
     * collect:4, 06:44.719, threadName: main
     * collect:6, 06:45.729, threadName: main
     * collect:8, 06:46.740, threadName: main
     * collect:10, 06:47.748, threadName: main
     */
    fun execFlowMap() {
        val flow = flowOf(1, 2, 3, 4, 5).map {
            delay(1000L)
            it * 2
        }

        runBlocking {
            val threadName = Thread.currentThread().name
            flow.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }
    }

    /**
     * collect:2, 25:09.060, threadName: main
     * collect:4, 25:11.067, threadName: main
     */
    fun execFlowFilter() {
        val flow = flowOf(1, 2, 3, 4, 5).filter {
            delay(1000L)
            it % 2 == 0
        }

        runBlocking {
            val threadName = Thread.currentThread().name
            flow.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
        }
    }

    /**
     * collect:1, 34:02.531, threadName: main
     * collect:2, 34:03.537, threadName: main
     * collect:3, 34:04.539, threadName: main
     * collect:4, 34:05.541, threadName: main
     * collect:5, 34:06.547, threadName: main
     * finish!:  34:06.547
     */
    fun execFlowDistinctUntilChanged() {
        val flow = flowOf(1, 1, 2, 2, 3, 3, 3, 4, 4, 5, 5, 5, 5, 5).distinctUntilChanged().filter {
            delay(1000L)
            true
        }

        runBlocking {
            val threadName = Thread.currentThread().name
            flow.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            println("finish!:  ${LocalTime.now().format(formatter)}")
        }
    }

    /**
     * collect:6, 45:48.884, threadName: main
     * collect:7, 45:48.887, threadName: main
     * collect:8, 45:48.887, threadName: main
     * collect:9, 45:48.887, threadName: main
     * collect:10, 45:48.887, threadName: main
     * finish!:  45:48.887
     */
    fun execFlowDrop() {
        val flow = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).drop(5)
        runBlocking {
            val threadName = Thread.currentThread().name
            flow.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            println("finish!:  ${LocalTime.now().format(formatter)}")
        }
    }

    /**
     * collect:1, 48:48.210, threadName: main
     * collect:2, 48:48.212, threadName: main
     * collect:3, 48:48.212, threadName: main
     * collect:4, 48:48.212, threadName: main
     * collect:5, 48:48.213, threadName: main
     * finish!:  48:48.213
     */
    fun execFlowTake() {
        val flow = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).take(5)
        runBlocking {
            val threadName = Thread.currentThread().name
            flow.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            println("finish!:  ${LocalTime.now().format(formatter)}")
        }
    }

    /**
     * collect:2 <= element:1 * 2, 57:47.553, threadName: main
     * collect:4 <= element:2 * 2, 57:47.555, threadName: main
     * collect:6 <= element:3 * 2, 57:47.555, threadName: main
     * collect:8 <= element:4 * 2, 57:47.555, threadName: main
     * collect:10 <= element:5 * 2, 57:47.555, threadName: main
     */
    fun execFlowTransform() {
        val flow = flowOf(1, 2, 3, 4, 5).transform {
            emit("${it * 2} <= element:$it * 2")
        }
        runBlocking {
            val threadName = Thread.currentThread().name
            flow.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            println("finish!:  ${LocalTime.now().format(formatter)}")
        }
    }

    /**
     * collect:0, 04:40.233, threadName: main
     * collect:1, 04:40.236, threadName: main
     * collect:3, 04:40.236, threadName: main
     * collect:6, 04:40.236, threadName: main
     * collect:10, 04:40.236, threadName: main
     * collect:15, 04:40.236, threadName: main
     * collect:21, 04:40.236, threadName: main
     * collect:28, 04:40.236, threadName: main
     * collect:36, 04:40.236, threadName: main
     * collect:45, 04:40.236, threadName: main
     * collect:55, 04:40.237, threadName: main
     */
    fun execFlowScan() {
        val flow = flowOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).scan(0) { sum, v -> sum + v }
        runBlocking {
            val threadName = Thread.currentThread().name
            flow.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            println("finish!:  ${LocalTime.now().format(formatter)}")
        }
    }

    /**
     * start!:  09:33.048
     * emit:1, 09:33.055, threadName: main
     * onEach:1, 09:33.067, threadName: main
     * collect:1, 09:33.067, threadName: main
     * emit:2, 09:36.080, threadName: main <= emitもcollectのdelay(2000)に影響を受ける（計約3000s待機してからemitすることになる）
     * onEach:2, 09:36.080, threadName: main
     * collect:2, 09:36.080, threadName: main
     * emit:3, 09:39.083, threadName: main
     * onEach:3, 09:39.083, threadName: main
     * collect:3, 09:39.083, threadName: main
     * emit:4, 09:42.090, threadName: main
     * onEach:4, 09:42.090, threadName: main
     * collect:4, 09:42.090, threadName: main
     * emit:5, 09:45.100, threadName: main
     * onEach:5, 09:45.100, threadName: main
     * collect:5, 09:45.100, threadName: main
     * finish!:  09:48.107
     */
    fun execFlowNotBuffer() {
        val flow = flow {
            println("start!:  ${LocalTime.now().format(formatter)}")
            (1..5).forEach {
                val threadName = Thread.currentThread().name
                println("emit:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                emit(it)
                delay(1000L)
            }
        }

        runBlocking {
            val threadName = Thread.currentThread().name
            flow.onEach {
                println("onEach:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }.collect {
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                delay(2000L)
            }
            println("finish!:  ${LocalTime.now().format(formatter)}")
        }
    }

    /**
     * start!:  16:41.009
     * emit:1, 16:41.016, threadName: main
     * onEach:1, 16:41.024, threadName: main
     * collect:1, 16:41.031, threadName: main
     * emit:2, 16:42.033, threadName: main <= emitはcollectのdelay(2000)に影響を受けない（約1000sに1回emitする）
     * onEach:2, 16:42.033, threadName: main <= onEachはcollectのdelay(2000)に影響を受けない（約1000sに1回onEachする）
     * collect:2, 16:43.032, threadName: main <= collectは約2000sに1回データを受け取る
     * emit:3, 16:43.034, threadName: main
     * onEach:3, 16:43.034, threadName: main
     * emit:4, 16:44.037, threadName: main
     * onEach:4, 16:44.037, threadName: main
     * collect:3, 16:45.036, threadName: main
     * emit:5, 16:45.038, threadName: main
     * onEach:5, 16:45.039, threadName: main
     * collect:4, 16:47.037, threadName: main
     * collect:5, 16:49.042, threadName: main
     * finish!:  16:51.050
     */
    fun execFlowBuffer() {
        val flow = flow {
            println("start!:  ${LocalTime.now().format(formatter)}")
            (1..5).forEach {
                val threadName = Thread.currentThread().name
                println("emit:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                emit(it)
                delay(1000L)
            }
        }

        runBlocking {
            val threadName = Thread.currentThread().name
            flow
                .onEach {
                    println("onEach:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                }.buffer(5)
                .collect {
                    println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                    delay(2000L)
                }
            println("finish!:  ${LocalTime.now().format(formatter)}")
        }
    }

    /**
     * start!:  24:38.692
     * emit:1, 24:38.705, threadName: main
     * start!:  24:38.716
     * emit:1, 24:38.716, threadName: main
     * collect(DROP_OLDEST):1, 24:38.717, threadName: main
     * collect(DROP_LATEST):1, 24:38.718, threadName: main
     * emit:2, 24:39.717, threadName: main
     * emit:2, 24:39.717, threadName: main
     * emit:3, 24:40.723, threadName: main
     * collect(DROP_OLDEST):3, 24:40.723, threadName: main <= collectは前回のcollectから最新のemitのデータを取得する。（古いデータから消していく）
     * emit:3, 24:40.723, threadName: main
     * collect(DROP_LATEST):2, 24:40.724, threadName: main <= collectは前回のcollectから最も古いemitのデータを取得する。（新しいデータを消していく）
     * emit:4, 24:41.725, threadName: main
     * emit:4, 24:41.726, threadName: main
     * collect(DROP_OLDEST):4, 24:42.724, threadName: main
     * collect(DROP_LATEST):4, 24:42.725, threadName: main
     * emit:5, 24:42.726, threadName: main
     * emit:5, 24:42.727, threadName: main
     * collect(DROP_OLDEST):5, 24:44.729, threadName: main
     * collect(DROP_LATEST):5, 24:44.729, threadName: main
     * finish!(DROP_OLDEST):  24:46.735
     * finish!(DROP_LATEST:  24:46.735
     */
    fun execFlowBufferOption() {
        val flow = flow {
            println("start!:  ${LocalTime.now().format(formatter)}")
            (1..5).forEach {
                val threadName = Thread.currentThread().name
                println("emit:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
                emit(it)
                delay(1000L)
            }
        }

        runBlocking {
            val threadName = Thread.currentThread().name
            launch {
                flow
                    .buffer(1, BufferOverflow.DROP_OLDEST)
                    .collect {
                        println(
                            "collect(DROP_OLDEST):$it, ${
                                LocalTime.now().format(formatter)
                            }, threadName: $threadName"
                        )
                        delay(2000L)
                    }
                println("finish!(DROP_OLDEST):  ${LocalTime.now().format(formatter)}")
            }
            launch {
                flow
                    .buffer(1, BufferOverflow.DROP_LATEST)
                    .collect {
                        println(
                            "collect(DROP_LATEST):$it, ${
                                LocalTime.now().format(formatter)
                            }, threadName: $threadName"
                        )
                        delay(2000L)
                    }
                println("finish!(DROP_LATEST:  ${LocalTime.now().format(formatter)}")
            }
        }
    }

    /**
     * emit:1, 24:04.438, threadName: main
     * emit:3, 24:04.954, threadName: main
     * combined: 1(f1) + 3(f2), 24:04.954, threadName: main
     * collect:4, 24:04.954, threadName: main
     * emit:2, 24:05.458, threadName: main
     * combined: 2(f1) + 3(f2), 24:05.459, threadName: main
     * collect:5, 24:05.459, threadName: main
     * emit:4, 24:06.457, threadName: main
     * combined: 2(f1) + 4(f2), 24:06.458, threadName: main
     * collect:6, 24:06.458, threadName: main
     * combined: 2(f1) + 5(f2), 24:07.464, threadName: main
     * collect:7, 24:07.464, threadName: main
     * combined: 2(f1) + 6(f2), 24:08.466, threadName: main
     * collect:8, 24:08.466, threadName: main
     * finish!:  24:09.474
     */
    fun execCombinedFlows() {
        val flow1 = flow {
            val threadName = Thread.currentThread().name
            println("emit:1, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            emit(1)
            delay(1000L)
            println("emit:2, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            emit(2)
        }

        val flow2 = flow {
            val threadName = Thread.currentThread().name
            delay(500L)
            println("emit:3, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            emit(3)
            delay(1500L)
            println("emit:4, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            emit(4)
            delay(1000L)
            emit(5)
            delay(1000L)
            emit(6)
            delay(1000L)
        }

        val combined = combine(flow1, flow2) { f1, f2 ->
            val threadName = Thread.currentThread().name
            println("combined: $f1(f1) + $f2(f2), ${LocalTime.now().format(formatter)}, threadName: $threadName")
            f1 + f2
        }

        runBlocking {
            combined.collect {
                val threadName = Thread.currentThread().name
                println("collect:$it, ${LocalTime.now().format(formatter)}, threadName: $threadName")
            }
            println("finish!:  ${LocalTime.now().format(formatter)}")
        }
    }
}