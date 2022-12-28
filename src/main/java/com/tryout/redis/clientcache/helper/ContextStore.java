package com.tryout.redis.clientcache.helper;

import org.springframework.context.ApplicationContext;

/**
 * ContextStore
 */
public class ContextStore {

    private static ContextStore INSTANCE;

    private ApplicationContext appContext;
    public ApplicationContext getAppContext() {return appContext;}
    public void setAppContext(ApplicationContext appContext) {this.appContext = appContext;}

    private ContextStore() {/** ENSURE SINGLETON **/}

    public static ContextStore getStore() {
        if (ContextStore.INSTANCE == null) {
            ContextStore.INSTANCE = new ContextStore();
        }
        return ContextStore.INSTANCE;
    }

}