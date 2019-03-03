package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@RestController
public class Application {

    @Autowired
    @Qualifier("redis-template")
    private RedisTemplate<String, Object> redisTemplate;
    private static final int TIMEOUT = 2000;
    private static final int MAXREDIRECTS = 5;
    private static final int MAXIDLE_AND_TOTAL = 8;
    private static final int MINIDLE = 0;

    @RequestMapping("/")
    public String home() {
        testPraneethMethod();
        testClusterMethod();
        return "Hello Docker World";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    private void testPraneethMethod(){
        try{    
            HashOperations<String, String, String> hashOperationsObj = redisTemplate.opsForHash();
            hashOperationsObj.put("ABC", "ABC", "ABC");
            hashOperationsObj.put("DEF", "DEF", "DEF");
            hashOperationsObj.put("PQR", "PQR", "PQR");
            hashOperationsObj.put("STU", "STU", "STU");
            hashOperationsObj.put("ATU", "ATU", "ATU");
            hashOperationsObj.put("XYZ", "XYZ", "XYZ");
            System.out.println("***");

            String hashValue = hashOperationsObj.get("ABC", "ABC");
            System.out.println("The Cached value for ABC is:" + hashValue);
            hashValue = hashOperationsObj.get("DEF", "DEF");
            System.out.println("The Cached value for DEF is:" + hashValue);
            hashValue = hashOperationsObj.get("PQR", "PQR");
            System.out.println("The Cached value for PQR is:" + hashValue);
            hashValue = hashOperationsObj.get("STU", "STU");
            System.out.println("The Cached value for STU is:" + hashValue);
            hashValue = hashOperationsObj.get("ATU", "ATU");
            System.out.println("The Cached value for ATU is:" + hashValue);
            hashValue = hashOperationsObj.get("XYZ", "XYZ");
            System.out.println("The Cached value for XYZ is:" + hashValue);
        }
        catch(Exception e){
            System.out.println("Praneeth method didn't work");
        }
    }

    private void testClusterMethod(){
        try{
            Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
            jedisClusterNodes.add(new HostAndPort("cgpoc.redis.cache.windows.net", 6379));
            //jedisClusterNodes.add(new HostAndPort("localhost", 6379));
            JedisCluster jedisCluster = new JedisCluster(jedisClusterNodes,
                                                TIMEOUT,
                                                TIMEOUT,
                                                MAXREDIRECTS,
                                                "LolhSNckKUUcFpuMS3eSLD8ghlch1S1LT9FVUFEBVgE=",
                                                //"",
                                                new GenericObjectPoolConfig());
            
            jedisCluster.set("ABC", "ABC");
            jedisCluster.set("MPR", "MPR");
            jedisCluster.set("STU", "STU");
            jedisCluster.set("XYZ", "XYZ");

            System.out.println("The Cached value for ABC is:" + jedisCluster.get("ABC"));
            System.out.println("The Cached value for MPR is:" + jedisCluster.get("MPR"));
            System.out.println("The Cached value for STU is:" + jedisCluster.get("STU"));
            System.out.println("The Cached value for XYZ is:" + jedisCluster.get("XYZ"));
        }
        catch(Exception ex){
            System.out.println("Redis Cluster method didn't work");
        }
    }
    

}
