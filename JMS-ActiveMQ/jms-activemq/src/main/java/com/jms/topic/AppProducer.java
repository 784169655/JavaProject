package com.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息提供者       用于发送消息到中间件
 * Created by  邱伟
 * 2018/7/8 14:44
 */

public class AppProducer {

    //指定 activemq 的服务器地址    tcp默认端口 61616   http默认端口 8161
    public static final String url = "tcp://192.168.2.158:61616";

    //队列名
    public static final String topicName = "topic-test";

    public static void main(String[] args) throws JMSException {

        // 1. 创建 ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);

        // 2. 创建连接
        Connection connection = connectionFactory.createConnection();

        // 3. 启动连接
        connection.start();

        // 4. 创建会话        第一个参数: 是否要在事务中处理
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // 5. 创建目的地     把消息发送到目的地
        Destination destination = session.createTopic(topicName);

        // 6. 创建生产者
        MessageProducer producer = session.createProducer(destination);

        for (int i=0;i<100;i++) {
            // 7. 循环向生产者发送，创建消息
            TextMessage textMessage = session.createTextMessage("test: " + i);

            // 8. 发布消息
            producer.send(textMessage);

            System.out.println("发送消息: " + textMessage.getText());
        }

        // 9. 关闭连接
        connection.close();
    }
}