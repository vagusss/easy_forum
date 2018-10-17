package jedis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisTest {
    @Test
    public  void teest01() {
        Jedis jedis = new Jedis("192.168.0.222",6379);
        jedis.set("jk2","jv2");
        String result = jedis.get("jk2");
        System.out.println(result);


    }
}
