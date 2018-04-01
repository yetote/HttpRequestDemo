package com.demo.yetote.httprequestdemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * com.demo.yetote.httprequestdemo
 * 管理整个框架的运作
 *
 * @author Swg
 * @date 2018/4/1 21:07
 */
public class ThreadPoolManager {
    private static ThreadPoolManager instance = new ThreadPoolManager();

    public static ThreadPoolManager getInstance() {
        return instance;
    }


    /**
     * 请求队列
     */
    private LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue();

    public void excute(Runnable runnable) {
        try {
            queue.put(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private ThreadPoolExecutor threadPoolExecutor;

    private ThreadPoolManager() {
        RejectedExecutionHandler rejectedExecutionHandler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                try {
                    queue.put(r);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        threadPoolExecutor = new ThreadPoolExecutor(4, 20, 15, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(4), rejectedExecutionHandler);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Runnable runnable = null;
                    try {
                        runnable = queue.take();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (runnable != null) {
                        threadPoolExecutor.execute(runnable);
                    }
                }
            }
        };
        threadPoolExecutor.execute(runnable);
    }

}

