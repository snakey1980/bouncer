package org.ambrose.bouncer

import org.junit.Test
import java.net.Socket

class TestOrigin {

    @Test
    fun test() {
        Socket("localhost", 9999).also {
            it.getOutputStream().write("hello".toByteArray())
            it.getInputStream().readAllBytes().also { println(String(it)) }
        }
    }

}