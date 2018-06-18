package com.hellosscat.thread;

/**
 * 如何停止一个线程
 * Created by John on 2018/5/30 0030.
 */
public class StopThreadPractice {

    static class MyThread extends Thread{

        private volatile boolean isTerminat = false;

        @Override
        public void run() {
            synchronized (this) {
                while (!isTerminat) {
                    System.out.println(getName() + " isRunning.");
                    try {
                        System.out.println(getName() + " wait.");
                        wait(300);
                    } catch (InterruptedException e) {
                        isTerminat = true;
                    }
                }
            }
            System.out.println(getName() + " terminated.");
        }

        public void setIsTerminat(boolean isTerminat) {
            this.isTerminat = isTerminat;
        }
    }


    public static void main(String[] args) throws InterruptedException {

        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.start();
        t2.start();

        int num = 1;
        for (;;){
            if (++num==50){
                Thread.sleep(1000);// do something
                t1.setIsTerminat(true);
                t1.interrupt();

                t2.setIsTerminat(true);
                t2.interrupt();
                break;
            }
            System.out.println("main..."+num);
        }
    }
}
