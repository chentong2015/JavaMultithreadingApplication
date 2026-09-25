package tools.semaphore;

import java.util.concurrent.Semaphore;

// TODO. Semaphore 信号量: 控制许可(permit)来限制执行数量
// Semaphore常用于控制并发的线程数, 流量控制, 公用资源访问控制
// acquire和release通常需要成对出现, 保证获取的permit能被释放
public class JavaSemaphore {

    public void testSemaphore() throws InterruptedException {
        Semaphore semaphore = new Semaphore(10);
        // 阻塞: Thread disabled for thread scheduling purposes
        semaphore.acquire();

        // 非阻塞: 使用tryAcquire()立即返回permit的获取
        boolean hasPermit = semaphore.tryAcquire();

        // 释放获取到的Permit许可，激活正在等待信号的线程
        semaphore.release();
    }
}