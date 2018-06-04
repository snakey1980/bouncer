package org.ambrose.bouncer

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.stereotype.Component
import java.net.ServerSocket

@Component
@ConditionalOnProperty("bouncer.client")
class ClientController(private val applicationProperties: ApplicationProperties, restTemplateBuilder: RestTemplateBuilder) {

    private val restTemplate = restTemplateBuilder.build()

    private val tcpport = applicationProperties.tcpport
            ?: throw IllegalStateException("Property bouncer.tcpport must be specified")

    private val serverurl = applicationProperties.serverurl
            ?: throw IllegalStateException("Property bouncer.serverurl must be specified")

    init {
        Thread(Runnable {
            val server = ServerSocket(tcpport)
            while (true) {
                val socket = server.accept()
                println("heard something")
                val bytes = socket.getInputStream().readAllBytes()
                val response = restTemplate.postForObject(serverurl, bytes, ByteArray::class.java)
                socket.getOutputStream().write(response)
                socket.close()
            }

        }, "bouncer-client").apply { start() }

    }

}