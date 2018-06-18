package com.hellosscat.sync;

import javafx.concurrent.Worker;

import java.util.concurrent.Semaphore;

/**
 * Semaphore:计数信号量
 *
 * 场景:
 *
 * 假若一个工厂有5台机器，但是有8个工人，一台机器同时只能被一个工人使用，只有使用完了，其他工人才能继续使用。
 *
 * Created by John on 2018/6/18 0018.
 */
public class SemaphoreUsage {

    public static void main(String[] args) {
        final int N = 8;
        final Semaphore semaphore = new Semaphore(5);

        for (int i = 0; i < N; i++) {
            new Worker(i, semaphore).start();
        }
    }

    static class Worker extends Thread{
        private int no;
        private Semaphore semaphore;

        public Worker(int no, Semaphore semaphore) {
            this.no = no;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            System.out.println("wordker["+no+"]：申请使用机器");
            try {
                semaphore.acquire();
                System.out.println("wordker[" + no + "]：申请成功");
                Thread.sleep(5000);
                System.out.println("wordker[" + no + "]：使用完，释放机器");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
