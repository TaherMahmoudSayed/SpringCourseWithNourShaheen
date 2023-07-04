package com.book.booksproject.service;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
public class PriceScheduler {

    /*
    * If our tasks are truly independent, it's more convenient to run them in parallel.
    *For that, we need to provide a TaskScheduler that better suits our needs:
  //   @Bean
  //   public TaskScheduler  taskScheduler() {
  //  ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
  //  threadPoolTaskScheduler.setPoolSize(5);
  //  threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
  //  return threadPoolTaskScheduler;
  // }
  *
    * "ThreadPoolTaskScheduler" is a TaskExecutor that provides a pool of threads for executing tasks asynchronously.
    *  It is used by Spring to execute the asynchronous tasks created by @Async.
    * If you don't specify a TaskExecutor when using @Async, Spring will use the default SimpleAsyncTaskExecutor.
    * "SimpleAsyncTaskExecutor" creates a new thread for each task and does not reuse threads.
    * @Scheduled is used to schedule a task to be executed at a fixed rate or delay.
    * When you use @Scheduled with @Async, the method will be executed asynchronously at the specified rate or delay.
    * I hope this helps. Let me know if you have any other questions.
    *
    *
    **/

   /* @Scheduled(fixedRate = 2000)
    @SchedulerLock(name = "computePriceScheduler")
    @Async
    public void computePrice() throws InterruptedException {
        Thread.sleep(40000);
        log.info("message from computePrice at:"+ LocalDateTime.now());
    }*/
    @SchedulerLock(name = "computeBookCountScheduler",lockAtLeastFor = "10s")
    @Scheduled(fixedRate = 4000,initialDelay = 10000)
    @Async
    public  void computeBookCount() throws InterruptedException {
        Thread.sleep(8000);
        log.info("message from computeBookCount at:"+ LocalDateTime.now());
    }

}
