package com.example.tinymall.core.utils;

import com.example.tinymall.common.Exceptions.BusinessException;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.function.Supplier;

/**
 * @ClassName AsyncUtils
 * @Description 异步工具
 * @Author jzf
 * @Date 2020-5-14 21:29
 */
public class AsyncUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncUtils.class);
    private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
            .setNameFormat("home-pool-%d").build();

    private static ExecutorService executorService = new ThreadPoolExecutor(5, 200,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());

    private AsyncUtils() {
        // private
    }

    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
        return supplyAsync(supplier, ForkJoinPool.commonPool());
    }

    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier, Executor executor) {
        Map<String, String> contextMap = MDC.getCopyOfContextMap();
        return CompletableFuture.supplyAsync(() -> {
            MDC.setContextMap(contextMap != null ? contextMap : Collections.emptyMap());
            try {
                return supplier.get();
            } catch (BusinessException be) {
                throw be;
            } catch (Exception e) {
                LOGGER.error("执行异步方法出错.", e);
                throw new BusinessException("系统异常，请稍后再试");
            } finally {
                MDC.clear();
            }
        }, executor);
    }

    public static List getList(Callable<List> callable){
        try{
            FutureTask<List> task = new FutureTask<>(callable);
            executorService.submit(task);
            return task.get();
        }catch (Exception e){
            LOGGER.error("执行异步方法出错.", e);
            throw new BusinessException("系统异常，请稍后再试");
        }finally {
            executorService.shutdown();
        }
    }
}
