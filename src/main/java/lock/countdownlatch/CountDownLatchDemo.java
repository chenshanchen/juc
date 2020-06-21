package lock.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch: 让一些线程阻塞直到另一些线程完成后才被唤醒
 *
 * CountDownLatch 主要有两个方法：
 *  当一个或多个线程调用 await() 时，调用线程会被阻塞。
 *  其他线程调用 countdown() 计数器减 1 (调用 countdown() 时线程不会阻塞)，
 *  当计数器的值变为0，因调用 await() 被阻塞的线程会被唤醒，继续执行
 */
public class CountDownLatchDemo {

    // 一共六个子线程都执行完毕之后，main线程才能执行
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6 ; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 灭国");
                countDownLatch.countDown();
            },CountryEnum.ForEach_CountryEnum(i).getRetMessage()).start();
        }
        try {
            // 阻塞 main 线程，直到子线程 countdown() 为 0
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "\t 秦，一统华夏！");

    }
}
