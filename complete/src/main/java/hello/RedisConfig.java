package hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;

/**
 * Created by Dialog crm & billing. on 3/22/2018.
 */
@Configuration
public class RedisConfig {

    private String server1;
    private Integer port;
    private String password;

    public RedisConfig() {
        
        // //Local
        // this.server1 = "localhost";
        // this.port = 6379;
        // this.password = "";
        

        //Azure
        this.server1 = "cgpoc.redis.cache.windows.net";
        this.port = 6379;
        this.password = "LolhSNckKUUcFpuMS3eSLD8ghlch1S1LT9FVUFEBVgE=";
    }

    @Bean
    public JedisConnectionFactory connectionFactory() {
        JedisShardInfo jedisShardInfo = new JedisShardInfo(server1, port);
        jedisShardInfo.setPassword(password);

        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(200);
        poolConfig.setMinIdle(5);
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxWaitMillis(5000);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setShardInfo(jedisShardInfo);
        jedisConnectionFactory.setPoolConfig(poolConfig);
        return jedisConnectionFactory;
    }

    @Bean("redis-template")
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        template.setEnableTransactionSupport(true);
        template.setExposeConnection(true);
        return template;
    }
}
