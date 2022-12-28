package com.tryout.redis.clientcache.controller;

import com.tryout.redis.clientcache.cache.CacheManager;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {


    @GetMapping("/get/{key}")
    public String getCache(@PathVariable String key) {
        return CacheManager.getInstance().get(key);
    }


    @GetMapping("/put/{key}/{value}")
    public String putCache(@PathVariable String key, @PathVariable String value) {
         CacheManager.getInstance().set(key,value);
         return "ok";
    }
    
}
