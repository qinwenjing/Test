package com.activemq;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

/**
 * JmsTemplate代表发送者来负责处理发送消息的复杂过程， 当调用send()方法时，JmsTemplate将负责获得JMS连接、会话并代表发送者发送消息
 */
@Service("alertService")
public class AlertServiceImpl implements AlertService {
    // 注入JMS
    @Resource
    private JmsOperations jmsOperations;

    @Override
    // 注意这个地方spittle用final修饰，而且它必须是可序列化的，否则编译不过
    public void sendSpittleAlert(final Spittle spittle) {
        System.out.println("sendSpittleAlert.........");

        // 1、没有指定默认的队列
       /* jmsOperations.send("spittle.alert.queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(spittle);
            }
        });*/

        // 2、指定的默认的队列
      /*  jmsOperations.send(new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createObjectMessage(spittle);
            }
        });*/

        // 3、使用默认的消息转化器发送消息，而不用手动创建MessageCreator
        // 默认情况下，JmsTemplate在convertAndSend()方法中会使用SimpleMessageConverter
         jmsOperations.convertAndSend(spittle);
       // jmsOperations.convertAndSend("shouxie name ");

    }

    /**
     * 下面try...catch的解释：
     * JmsTemplate可以很好地处理抛出 的JmsException检查型异常，然后把异常转换为Spring非检查型异常JmsException并重新抛出。但是它只对调用JmsTemplate的方法
     * 时才适用。JmsTemplate无法处理调用ObjectMessage的getObject()方法时所抛出的JMSException异常。
     *
     * @return
     */
    public Spittle receiveSpittleAlert() {
        System.out.println("receiveSpittleAlert.........");
        // 消息到达后转型为ObjectMessage
        Message message = jmsOperations.receive();
        ObjectMessage receiveMessage = (ObjectMessage) jmsOperations.receive();
        try {
            // 把ObjectMessage转换为Spittle对象并返回此对象
            return (Spittle) receiveMessage.getObject();
        } catch (JMSException e) {
            e.printStackTrace();
        }
        return null;

        // 这种方式就不用处理异常了
        /* Spittle spittle = (Spittle) jmsOperations.receiveAndConvert();
         return spittle;*/
    }

}
















