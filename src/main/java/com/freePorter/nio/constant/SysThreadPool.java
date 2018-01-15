/**
 * Copyright (c) www.bugull.com
 */
package com.freePorter.nio.constant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 *
 * @author zoush(zoush@hadlinks.com)
 *
 * CreateTime 2017-03-11 00:21
 */
public class SysThreadPool {

    private ExecutorService executor;

    private SysThreadPool(){
        int threadPoolSize = Runtime.getRuntime().availableProcessors() * 2 + 1;
        executor = Executors.newFixedThreadPool(threadPoolSize);
    }

    private static class Holder {

        final static SysThreadPool instance = new SysThreadPool();
    }

    public static SysThreadPool getInstance(){
        return Holder.instance;
    }

    public ExecutorService getExecutor(){
        return executor ;
    }


}
