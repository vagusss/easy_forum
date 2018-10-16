package rabbitmqtest.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
    private final static String EXCHANGE_NAME = "test_exchange_fanout";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.222");
        factory.setPort(5672);
        factory.setVirtualHost("/forum");
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 消息内容
        String message = "Hello cat~~";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
        System.out.println(" send： '" + message + "'");

        channel.close();
        connection.close();
    }

}
