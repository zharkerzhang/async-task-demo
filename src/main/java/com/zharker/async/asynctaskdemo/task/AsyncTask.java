package com.zharker.async.asynctaskdemo.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

@Slf4j
@Component
public class AsyncTask {

    private static final long TH = 1000L;

    @Async("taskExecutor")
    public Future<Long> eating(int maxCostSecond) throws InterruptedException {
        long costMillseconds = Math.round(maxCostSecond*Math.random()*TH);
        log.info("eating cost:{} millseconds",costMillseconds);
        Thread.sleep(costMillseconds);
        return new AsyncResult<>(costMillseconds);
    }

    @Async("taskExecutor")
    public Future<Long> watching(int maxCostSecond) throws InterruptedException {
        long costMillseconds = Math.round(maxCostSecond*Math.random()*TH);
        log.info("watching cost:{} millseconds",costMillseconds);
        Thread.sleep(costMillseconds);
        return new AsyncResult<>(costMillseconds);
    }

    @Async("taskExecutor")
    public Future<Long> listening(int maxCostSecond) throws InterruptedException {
        long costMillseconds = Math.round(maxCostSecond*Math.random()*TH);
        log.info("listening cost:{} millseconds",costMillseconds);
        Thread.sleep(costMillseconds);
        return new AsyncResult<>(costMillseconds);
    }

    @Async("taskExecutor")
    public Future<Long> illing(int maxCostSecond) throws InterruptedException {
        long costMillseconds = Math.round(maxCostSecond*Math.random()*TH);
        log.info("illing cost:{} millseconds",costMillseconds);
        Thread.sleep(costMillseconds);
        if(true){
            throw new IllegalArgumentException("ill error");
        }
        return new AsyncResult<>(costMillseconds);
    }
}
