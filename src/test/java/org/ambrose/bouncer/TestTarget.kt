package org.ambrose.bouncer

import org.junit.Test
import java.net.ServerSocket
import java.net.Socket

class TestTarget {

    @Test
    fun test() {
        ServerSocket(10101).accept().also {
            println("target received connection")
            it.getInputStream().readAllBytes().also { println(String(it)) }
            it.getOutputStream().write("goodbye".toByteArray())
            it.close()
        }
    }

}