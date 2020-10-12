package com.itheima.rabbitmq.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.sun.corba.se.impl.monitoring.MonitoredAttributeInfoImpl;
import com.sun.jndi.cosnaming.CNCtx;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author 陈杰华
 * @date 2020/10/8 - 13:40
 */
public class Producer {
    //全局静态的队列名称
    static final String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws Exception {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //主机地址：默认为localhost
        connectionFactory.setHost("192.168.52.130");
        //连接端口
        connectionFactory.setPort(5672);
        //虚拟主机名称：默认为 /
        connectionFactory.setVirtualHost("/heima");
        //连接用户名：默认为guest
        connectionFactory.setUsername("heima");
        //连接密码：默认为guest
        connectionFactory.setPassword("heima");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //创建频道
        Channel channel = connection.createChannel();
        //声明（创建）队列
        /*
        参数一：队列名称
        参数二：是否定义持久队列
        参数三：是否独占本次连接
        参数四：是否在不使用的时候自动删除队列
        参数五：队列其他参数
         */
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        //要发送的消息
//        String message = "贝吉塔冲击钻大战卡卡罗特车司机";
        String message = "贝吉塔冲击钻大战卡卡罗特车司机2222222222";
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
        System.out.println("已发送的消息："+message);
        //释放资源
        channel.close();
        connection.close();
    }
}
