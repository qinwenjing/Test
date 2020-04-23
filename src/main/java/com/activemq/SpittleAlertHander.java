package com.activemq;

import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * MDB将 JMS目的地中的消息作为事件，并对这些事件进行响应。而与之相反的是，同步消息接收者在消息可用前会一直处于阻塞状态
 */

// 这个注解标注MDB(消息驱动bean, message-driver bean, MDB, 是EJB中的一个重要内容)
/*@MessageDriven(mappedName = "jms/spittle.alert.queue")
public class SpittleAlertHander implements MessageListener {
    @Override
    public void onMessage(Message message) {

    }

    public void handleSpittleAlert(Spittle spittle){

    }
}*/
