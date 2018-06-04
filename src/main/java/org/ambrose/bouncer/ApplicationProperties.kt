package org.ambrose.bouncer

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@ConfigurationProperties("bouncer")
@Component
class ApplicationProperties {

    var serverurl: String? = null
    var tcpport: Int? = null
    var targethost: String? = null
    var targetport: Int? = null

}