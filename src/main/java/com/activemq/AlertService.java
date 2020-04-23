package com.activemq;

public interface AlertService {
    void sendSpittleAlert(Spittle spittle);

    public Spittle receiveSpittleAlert();
}
