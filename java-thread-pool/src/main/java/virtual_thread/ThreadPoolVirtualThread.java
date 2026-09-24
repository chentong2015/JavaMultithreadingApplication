package virtual_thread;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class ThreadPoolVirtualThread {

    // TODO. 线程池(默认ForkJoinPool高并发线程池)运行远超于OS线程数的任务
    // - 每个任务都对应创建一个虚拟线程, 所有提交任务并发执行
    // - 所有并发的虚拟线程能够运行在有限的OS线程数之上
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger();

        // 针对每个提交任务创建虚拟线程 => 在线程池中并发执行
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 50).forEach(i -> {
                // Summit a task (run by virtual thread instance)
                executor.submit(() -> {
                    count.incrementAndGet();
                    Thread.sleep(Duration.ofMinutes(1));
                    System.out.println(count);

                    Thread.sleep(Duration.ofMinutes(1));
                    System.out.println(count);
                    return i;
                });
            });

            Thread.sleep(Duration.ofSeconds(5));
            System.out.println("started = " + count.get());
        }
    }
}
