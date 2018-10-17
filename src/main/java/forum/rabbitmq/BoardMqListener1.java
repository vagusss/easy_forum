package forum.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
//测试用
public class BoardMqListener1 {
    @Autowired
    private JedisPool jedisPool;

    public void listenD(String msg){
        System.out.println("listen: " + msg);



    }
    public void listenU(String msg){
        System.out.println("listen1: " + msg);



    }
}
