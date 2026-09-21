package collections.heap;

import java.util.concurrent.PriorityBlockingQueue;

// TODO. 优先级队列的底层结构是Heap堆, 阻塞则线程安全
// 优先级排序: 插入和删除的时间复杂度都是log(n)
public class JavaPriorityBlockingQueue {

    public static void main(String[] args) {
        // 默认优先阻塞队列的初始容量 DEFAULT_INITIAL_CAPACITY = 11
        PriorityBlockingQueue<String> priorityBlockingQueue = new PriorityBlockingQueue<>();
        priorityBlockingQueue.add("item 1");
        priorityBlockingQueue.add("item 2");
        priorityBlockingQueue.add("item 3");

    }
}