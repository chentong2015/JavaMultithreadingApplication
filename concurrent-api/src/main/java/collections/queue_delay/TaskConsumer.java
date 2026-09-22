package collections.queue_delay;

import java.util.concurrent.DelayQueue;

// 消费这个持久监听延迟队列中的Task, 到延迟时间立即执行
public class TaskConsumer implements Runnable {

    private final DelayQueue<MyDelayedTask> queue;

    public TaskConsumer(DelayQueue<MyDelayedTask> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // TODO. 无限等待直到有task可用，阻塞时不消耗CPU
                // Retrieves and removes the expired head of this queue,
                // waiting if necessary until an expired element is available on this queue.
                MyDelayedTask task = queue.take();

                // 只有一个消费者线程，当执行耗时任务时
                // 其它到时任务也没有办法被执行到，正在队列中排队
                task.run();
            } catch (InterruptedException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
