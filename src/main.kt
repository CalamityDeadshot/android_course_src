import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis


fun main() {
    val suspendingTime = measureTimeMillis {
        suspendingCalls()
    }
    val badConcurrencyTime = measureTimeMillis {
        badConcurrency()
    }
    val asyncAwaitTime = measureTimeMillis {
        asyncAwait()
    }
    println("Synchronized time is $suspendingTime ms")
    println("Concurrent time is $badConcurrencyTime ms")
    println("Async-await time is $asyncAwaitTime ms")
}

fun coroutines() = runBlocking {
    repeat(100_000) {
        launch {
            delay(5000)
            print("$it ")
        }
    }
}

fun threads() {
    repeat(100_000) {
        thread {
            Thread.sleep(5000)
            print("$it ")
        }
    }
}

fun suspendingCalls() = runBlocking {
    launch {
        val data1 = getSomeData1()
        val data2 = getSomeData2()
        println("Data 1: $data1, data2: $data2")
    }
}

fun badConcurrency() = runBlocking {
    var data1: String? = null
    var data2: String? = null

    val job1 = launch {
        data1 = getSomeData1()
    }
    val job2 = launch {
        data2 = getSomeData1()
    }

    job1.join()
    job2.join()
    println("Data 1: $data1, data2: $data2")
}

fun asyncAwait() = runBlocking {
    val data1 = async { getSomeData1() }
    val data2 = async { getSomeData2() }

    println("Data 1: ${data1.await()}, data2: ${data2.await()}")
}

suspend fun getSomeData1(): String {
    delay(2000)
    return "Data 1"
}
suspend fun getSomeData2(): String {
    delay(2000)
    return "Data 2"
}