package forum.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//这是测试类,可以删除
public class Sender {
    public static void main(String[] args) throws InterruptedException {
        //启动接收者
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:applicationContext-rabbitmq.xml");
        RabbitTemplate template = context.getBean(RabbitTemplate.class);
        template.convertAndSend("hello pig");

        Thread.sleep(5000);
        context.destroy();
    }
}
