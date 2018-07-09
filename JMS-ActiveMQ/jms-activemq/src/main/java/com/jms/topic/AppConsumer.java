package com.jms.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 消息消费者
 * Created by  邱伟
 * 2018/7/8 15:29
 */

public class AppConsumer {


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

        // 6. 创建消费者
        MessageConsumer consumer = session.createConsumer(destination);

        // 7. 创建一个监听器
        consumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("接收消息: " + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // 8. 关闭连接（错误）      因为接收消息是异步的 在没有接收到消息就关掉了，所以没接收到
//        connection.close();
    }
}