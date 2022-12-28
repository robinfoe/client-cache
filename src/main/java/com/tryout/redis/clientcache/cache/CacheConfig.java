package com.tryout.redis.clientcache.cache;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
public class CacheConfig {

    @Getter @Setter
    private String url;

    @Getter @Setter
    private String port;
    
}
