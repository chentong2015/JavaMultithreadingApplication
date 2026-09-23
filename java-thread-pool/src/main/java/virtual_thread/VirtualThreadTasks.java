package virtual_thread;

import java.time.Duration;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class VirtualThreadTasks {

    // 运行远超于OS线程数量的虚拟线程，并发执行任务
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger count = new AtomicInteger();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 100).forEach(i -> {
                executor.submit(() -> {
                    count.incrementAndGet();
                    Thread.sleep(Duration.ofMinutes(10));
                    return i;
                });
            });

            Thread.sleep(Duration.ofSeconds(1));
            System.out.println("started = " + count.get());
        }
    }
}
