package org.ambrose.bouncer

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.net.Socket

@RestController
@ConditionalOnProperty("bouncer.server")
class ServerController(private val applicationProperties: ApplicationProperties) {

    private val targethost = applicationProperties.targethost
            ?: throw IllegalStateException("Propert bouncer.targethost must be specified")

    private val targetport = applicationProperties.targetport
            ?: throw IllegalStateException("Property bouncer.targetport must be specified")

    @RequestMapping("/", method = [RequestMethod.POST])
    fun hello(@RequestBody bytes: ByteArray): ByteArray {
        val socket = Socket(targethost, targetport)
        socket.getOutputStream().write(bytes)
        val result = socket.getInputStream().readAllBytes()
        socket.close()
        return result
    }

}