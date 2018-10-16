package forum.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class BoardMqListener {
    @Autowired
    private JedisPool jedisPool;

    public void listenD(String msg){
        System.out.println("listen: " + msg);

        //清缓存
        Jedis jedis = jedisPool.getResource();
        jedis.del("board_show");

    }
    public void listenU(String msg){
        System.out.println("listen: " + msg);

        //清缓存
        Jedis jedis = jedisPool.getResource();
        jedis.del("board_show");

    }
}
