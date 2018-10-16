package rabbitmqtest.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
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

        for (int i = 0; i < 100; i++) {
            String msg = "hello world" + i;
            channel.basicPublish("","test_queue",null,msg.getBytes());
            System.out.println("send: " + msg);

        }
        channel.close();
        connection.close();
    }
}
