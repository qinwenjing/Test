package com.activemq;

import org.springframework.stereotype.Component;


public class SpittleAlertHanderPOJO {

    public void handleSpittleAlert(Spittle spittle){
        System.out.println("SpittleAlertHanderPOJO handleSpittleAlert......");
        System.out.println(spittle.getName());
    }
}
