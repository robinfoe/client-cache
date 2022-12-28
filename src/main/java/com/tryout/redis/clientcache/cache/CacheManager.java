package com.tryout.redis.clientcache.cache;

import org.redisson.Redisson;
import org.redisson.api.LocalCachedMapOptions;
import org.redisson.api.RLocalCachedMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CacheManager {

    private static Logger log = LoggerFactory.getLogger(CacheManager.class);
    private static CacheManager INSTANCE; 

    private CacheManager(){/** ENSURE SINGLETON **/}
    
    private RedissonClient cacheClient;
    private String mapName = "CHATBOT";
    private RLocalCachedMap<String, String> chatbotCache;

    private void init(CacheConfig config){
        log.info("initiating cache config" + "redis://%s:%s".formatted(config.getUrl(), config.getPort()) );

        Config reddisonConfig = new Config();
        reddisonConfig.useSingleServer()
        .setAddress("redis://%s:%s".formatted(config.getUrl(), config.getPort()));

        // To be use with cluster server in shared environment ( SIT , UAT, PROD)
        // reddisonConfig.useClusterServers()
        //     .addNodeAddress("redis://%s:%s".formatted(config.getUrl(), config.getPort())));

        this.cacheClient = Redisson.create(reddisonConfig);

        LocalCachedMapOptions <String, String> cacheOptions = LocalCachedMapOptions.<String, String> defaults()
                // .cacheSize(10000)
                // .evictionPolicy(EvictionPolicy.LRU)
                // .maxIdle(5, TimeUnit.MINUTES)
                // .timeToLive(10, TimeUnit.MINUTES)
                // .storeMode(storeMode)
                .syncStrategy(LocalCachedMapOptions.SyncStrategy.INVALIDATE);

        this.chatbotCache = this.cacheClient.getLocalCachedMap(this.mapName, org.redisson.client.codec.StringCodec.INSTANCE,  cacheOptions);


    }

    public void populate(){

        log.info("Populating cache entry.... ");
        this.chatbotCache.put("key1", "testing");
        this.chatbotCache.put("key2", "hello");

    }


    public void retrieve(){
        log.info("retrieve cache entry.... ");
        for(String key : this.chatbotCache.keySet()){
            log.info("[%s] : [%s]".formatted(key, this.chatbotCache.get(key)));

        }
    }

    public void set(String key, String value){
        try{
            this.chatbotCache.put(key, value);
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public String get(String key){
        try{
            return this.chatbotCache.get(key);
        }catch(Exception e){
            e.printStackTrace();
        }

        return "";
    }

    public static void initiateCacheManager(CacheConfig config){
        CacheManager.INSTANCE = new CacheManager();
        CacheManager.INSTANCE.init(config);
    }


    public static CacheManager getInstance(){
        if(CacheManager.INSTANCE == null){
            throw new IllegalArgumentException("Please initiate cache manager");
        }

        return CacheManager.INSTANCE;
    } 


    
}
