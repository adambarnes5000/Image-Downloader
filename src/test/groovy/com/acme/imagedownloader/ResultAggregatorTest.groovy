package com.acme.imagedownloader

import org.springframework.messaging.Message
import org.springframework.messaging.support.MessageBuilder
import spock.lang.Specification


class ResultAggregatorTest extends Specification{

    def "Aggregate returns a count of the successful messages"(){
        given:
            Message<?> msg1 = MessageBuilder.withPayload(true).build()
            Message<?> msg2 = MessageBuilder.withPayload("http://toosmallimage.com").build()
            Message<?> msg3 = MessageBuilder.withPayload(true).build()
            Message<?> msg4 = MessageBuilder.withPayload(false).build()
        when:
            int result = new ResultAggregator().aggregate([msg1, msg2, msg3, msg4])
        then:
            result==2
    }
}
