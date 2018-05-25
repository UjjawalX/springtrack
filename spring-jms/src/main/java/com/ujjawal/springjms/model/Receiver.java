package com.ujjawal.springjms.model;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver {
    @JmsListener(destination = "mailBox" , containerFactory = "myFactory")
    public void recieveMessage(Email email){
        System.out.println("Received <"+email+">");
    }
}
