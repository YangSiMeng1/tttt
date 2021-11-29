package com.bawei.untils;

import android.os.Looper;

import androidx.arch.core.executor.DefaultTaskExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadUntils {

    /**
     * 是否在主线程的方法
     * @return
     */

    public static boolean isMainThread(){
        if (Looper.getMainLooper().getThread()==Thread.currentThread()){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 使用线程池执行任务
     * @param task
     */
    public static void runTask(Runnable task){

        /**
         * 1.核心线程数
         * 2.线程池总数
         * 3.非核心线程的存活时间
         * 4.存活时间单位
         * 5.阻塞队列
         * 6.创建线程的工厂
         */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 15, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable);
            }
        }
        );
        threadPoolExecutor.submit(task);
    }
}
