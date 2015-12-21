package com.acme.imagedownloader;

import org.springframework.messaging.Message;

import java.util.List;

public class ResultAggregator {

    public int aggregate(List<Message<?>> results){
        int count=0;
        for (Message<?> result : results) {
            if(Boolean.TRUE.equals(result.getPayload())){
                count++;
            }
        }
        return count;
    }
}
