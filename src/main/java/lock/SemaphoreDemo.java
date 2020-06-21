package lock;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Semaphore 是信号量的意思
 * 信号量主要有两个目的：
 *  一个是用于多个共享资源的相互排斥使用
 *  另一个是用于资源数的控制
 *
 * 举例：
 *  六个车抢占三个车位
 */
public class SemaphoreDemo {

    public static void main(String[] args) {

        //只有3个共享资源(3个停车位)
        Semaphore semaphore = new Semaphore(3);

        //六辆车来抢占资源
        for (int i = 1; i <= 6 ; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t 抢占到了车位");
                    //随机产生【1-10】 的随机数
                    int second = new Random().nextInt(10) + 1;
                    try {
                        TimeUnit.SECONDS.sleep(second);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "\t 停车 " + second + "秒之后离开车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
