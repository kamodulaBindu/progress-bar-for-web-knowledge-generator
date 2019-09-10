package com.stackroute.notification.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WebSocketNotificationReceiving {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebSocketNotificationReceiving(String name) {
        this.name = name;
    }
    public WebSocketNotificationReceiving() {
    }
}
