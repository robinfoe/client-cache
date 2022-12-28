package com.tryout.redis.clientcache;

import com.tryout.redis.clientcache.cache.CacheConfig;
import com.tryout.redis.clientcache.cache.CacheManager;
import com.tryout.redis.clientcache.helper.ContextStore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

// import redis.clients.jedis.Jedis;

@Component
public class OnStart implements ApplicationListener<ApplicationReadyEvent> {

    Logger log = LoggerFactory.getLogger(OnStart.class);



    @Autowired
    private CacheConfig cacheConfig;

    

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        log.debug("started...");

        ContextStore.getStore().setAppContext(event.getApplicationContext());
        CacheManager.initiateCacheManager(cacheConfig);
        // CacheManager.getCacheManager().populate();
        // CacheManager.getInstance().retrieve();

    }

}
