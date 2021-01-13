package io.kimmking.javacourse.mq.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.jms.annotation.JmsListener;

import javax.jms.*;
import java.util.function.Consumer;

public class ActiveMQServer {

    public static void main(String[] args) throws JMSException {
        queueProducer();

        queueConsumer();

    }

    public static void queueConsumer() throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61000");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("test1");

        MessageConsumer consumer = session.createConsumer(destination);
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("这是一条测试消息test1：" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static void queueProducer() throws JMSException {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61000");
        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("test1");

        MessageProducer producer = session.createProducer(destination);
        for (int i = 1 ; i <= 10 ; i++){
            TextMessage message = session.createTextMessage("第" + i + "条测试消息");
            producer.send(message);
            System.out.println("测试消息：" + message.getText());
        }
        connection.close();
    }
}

