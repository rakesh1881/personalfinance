package com.personalfinance.common;
class MyThread extends Thread {
    public void run() {
        try {
            for (int i = 1; i <= 1; i++) {
              //  System.out.println(Thread.currentThread().getId() + " Value " + i);
                System.out.println("Before Thread in MyThread");
                Thread.sleep(10000);
                System.out.println("After Thread in MyThread");
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getId() + " Thread interrupted");
        }
    }
}

public class ThreadExample {
    public static void main(String args[]) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
//        t2.start();

        // Let threads run for a while
        try {
        	System.out.println("Before Thread in Thread Example");
            Thread.sleep(3000);
            System.out.println("After Thread in Thread Example");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Interrupt the threads
    //    t1.interrupt();
      //  t2.interrupt();
    }
}