package org.ambrose.bouncer

import org.junit.Test
import java.net.Socket

class TestOrigin {

    @Test
    fun test() {
        println("origin starting")
        Socket("localhost", 9999).also {
            it.getOutputStream().write("hello".toByteArray())
            println("origin sent message")
            it.getOutputStream().flush()
            println("origin flushed stream")
            it.getInputStream().readAllBytes().also { println(String(it)) }
            println("origin done")
        }
    }

}