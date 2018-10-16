package rabbitmqtest;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Rceper {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.222");
        factory.setPort(5672);
        factory.setVirtualHost("/forum");
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare("test_queue",false,false,false,null);
        System.out.println("按ctrl+c关闭程序");

        //接收消息
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body,"utf-8");
                System.out.println("得到: " + msg);



            }
        };

        channel.basicConsume("test_queue",true,consumer);
    }
}
