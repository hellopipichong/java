package com.hellosscat.sync;

import javafx.concurrent.Worker;

import java.util.concurrent.Semaphore;

/**
 * Semaphore:�����ź���
 *
 * ����:
 *
 * ����һ��������5̨������������8�����ˣ�һ̨����ͬʱֻ�ܱ�һ������ʹ�ã�ֻ��ʹ�����ˣ��������˲��ܼ���ʹ�á�
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
            System.out.println("wordker["+no+"]������ʹ�û���");
            try {
                semaphore.acquire();
                System.out.println("wordker[" + no + "]������ɹ�");
                Thread.sleep(5000);
                System.out.println("wordker[" + no + "]��ʹ���꣬�ͷŻ���");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
