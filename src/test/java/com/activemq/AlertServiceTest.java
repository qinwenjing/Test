package com.activemq;

import java.util.concurrent.ThreadPoolExecutor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/application.xml"})
public class AlertServiceTest {
    @Autowired
    private AlertService alertService;

    @Test
    public void sendSpittleAlert() {
        Spittle spittle = new Spittle();
        spittle.setName("qinwenjing");
        alertService.sendSpittleAlert(spittle);
       /* try {
            Thread.sleep(300000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Test
    public void receiveSpittleAlert() {
        alertService.receiveSpittleAlert();
    }
}