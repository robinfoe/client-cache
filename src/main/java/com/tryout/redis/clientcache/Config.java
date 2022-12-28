package com.tryout.redis.clientcache;

import com.tryout.redis.clientcache.cache.CacheConfig;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
// import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

// import redis.clients.jedis.JedisPool;

@Configuration
public class Config {

    @Value( "${redis.server.url}" )
    private String redisUrl;

    @Value( "${redis.server.port}" )
    private String redisPort;


    @Bean
    public CacheConfig cacheConfig(){

        CacheConfig config = new CacheConfig();
        config.setUrl(this.redisUrl);
        config.setPort(this.redisPort);
        return config;

    }

    // @Bean 
    // public JedisPool jedisPool(){
    //     JedisPool item = new JedisPool();
    //     // item.se


    //     return item;
    // }

//   @Bean
//   public JedisConnectionFactory redisConnectionFactory() {
//     RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("localhost", 6379);
//     return new JedisConnectionFactory(config);
//   }
    
}


