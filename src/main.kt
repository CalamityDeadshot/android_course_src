import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.ArrayList
import kotlin.concurrent.thread


fun main() {
    coroutines()
//    threads()
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