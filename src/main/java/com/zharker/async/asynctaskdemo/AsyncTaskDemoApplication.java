package com.zharker.async.asynctaskdemo;

import com.zharker.async.asynctaskdemo.task.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Slf4j
@SpringBootApplication
@EnableAsync
public class AsyncTaskDemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(AsyncTaskDemoApplication.class, args);
    }

    @Autowired
    private AsyncTask task;

    @Override
    public void run(ApplicationArguments args) throws InterruptedException {

        Instant beginTime = Instant.now();

        Future<Long> eatingF = task.eating(10);
        Future<Long> watchingF = task.watching(10);
        Future<Long> listeningF = task.listening(10);
        Future<Long> illingF = task.illing(10);
        task.dying(5);

        while (!eatingF.isDone() || !watchingF.isDone() || !listeningF.isDone() || !illingF.isDone()) {
            Thread.sleep(100);
        }
        long eatCost = futureGet(eatingF);
        long watchCost = futureGet(watchingF);
        long listenCost = futureGet(listeningF);
        long illCost = futureGet(illingF);

        Instant endTime = Instant.now();
        long totalCost = Duration.between(beginTime, endTime).toMillis();
        log.info("all task complete, total cost milliseconds:{}", totalCost);

    }

    private long futureGet(Future<Long> future) {
        long result = 0L;
        if (future.isDone()) {
            try {
                result = future.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
        }
        return result;
    }
}
