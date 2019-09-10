package com.stackroute.notification.controller;

import com.stackroute.notification.domain.WebSocketNotificationReceiving;
import com.stackroute.notification.domain.WebSocketNotificationSending;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

@Controller
//STOMP messages routed to @Controller classes
@RestController
public class WebSocketNotificationController {

public String data [];

private String consumedKeyWordPhrase="";

    @KafkaListener(topics = "PosController", groupId = "group")
    public String consumerKeyWordPhrase(String message) throws IOException {

        this.consumedKeyWordPhrase=message;
        System.out.println("consumed url is:"+consumedKeyWordPhrase);
      return consumedKeyWordPhrase;
    }

    @CrossOrigin
    @GetMapping("/keyWord")
    public boolean consumerKeyWordPhrase() throws IOException {
        if(consumerKeyWordPhrase(consumedKeyWordPhrase).equals("")){
            return false;

        }
        return true;
    }

    private String consumedUrl="";

    @KafkaListener(topics = "Kafka_Example", groupId = "group")
    public String consumerLinkUrl(String message1) throws IOException {

        this.consumedUrl=message1;
        System.out.println("consumed url is:"+consumedUrl);
        return consumedUrl;
    }

    @CrossOrigin
    @GetMapping("/link")
    public boolean webLink() throws IOException {
        if(consumerLinkUrl(consumedUrl).equals("")){
            return false;

        }
        else {
            return true;
        }
    }

    private String consumedData="";
    @KafkaListener(topics = "Fetch_Webpage", groupId = "group")
    public String consumerWebPageData(String message2) throws IOException {

        this.consumedData=message2;
        System.out.println("consumed url is:"+consumedData);
        return consumedData;
    }

    @CrossOrigin
    @GetMapping("/fetchWeb")
    public boolean webData() throws IOException {
        if(consumerLinkUrl(consumedData).equals("")){
            return false;

        }
        else {
            return true;
        }
    }




    @MessageMapping("/search")
    @SendTo("/topic/result")
    @CrossOrigin
    public WebSocketNotificationSending webSocketNotificationSending(WebSocketNotificationReceiving webSocketNotificationReceiving) throws Exception{
        Thread.sleep(1000);

        System.out.println(HtmlUtils.htmlEscape(webSocketNotificationReceiving.getName()));
        return  new WebSocketNotificationSending("Hello" + HtmlUtils.htmlEscape(consumedKeyWordPhrase) + "!");
    }


}
//HtmlUtils.htmlEscape(webSocketNotificationReceiving.getName())