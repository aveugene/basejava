package ru.javawebinar.basejava;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MainConcurrency {
    private static final int THREADS_NUMBER = 10_000;
    private static int counter;
    private static AtomicInteger atomicInteger = new AtomicInteger();
//    private static final Object LOCK = new Object();
//    private static final Lock lock = new ReentrantLock();
    private static final ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static final Lock WRITE_LOCK = reentrantReadWriteLock.writeLock();
    private static final Lock READ_LOCK = reentrantReadWriteLock.readLock();

    private static final ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());

        Thread thread0 = new Thread() {
            @Override
            public void run() {
                System.out.println(getName() + ", " + getState());
                throw new IllegalStateException();
            }
        };
        thread0.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ", " + Thread.currentThread().getState());
            }
        }).start();

        System.out.println(thread0.getState());

        final MainConcurrency mainConcurrency = new MainConcurrency();
        CountDownLatch latch = new CountDownLatch(THREADS_NUMBER);
//        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CompletionService completionService = new ExecutorCompletionService(executorService);

//        List<Thread> threads = new ArrayList<>(THREADS_NUMBER);

        for (int i = 0; i < THREADS_NUMBER; i++) {
            Future<Integer> future = executorService.submit(() ->

//            Thread thread = new Thread(() ->
            {
                for (int j = 0; j < 100; j++) {
                    mainConcurrency.inc();
                    System.out.println(threadLocal.get().format(new Date()));
                }
                latch.countDown();
                return 5;
            });
            /*System.out.println(future.isDone());
            try {
                System.out.println(future.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }*/
//            thread.start();
//            threads.add(thread);

        }
        /*
        threads.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

*/
//        Thread.sleep(500);
        latch.await(10, TimeUnit.SECONDS);
        executorService.shutdown();
//        System.out.println(counter);
        System.out.println(atomicInteger);


        // deadlock from video:
        final String lock1 = "lock1";
        final String lock2 = "lock2";
        deadLock(lock1, lock2);
        // need to sleep to start this method
        deadLock(lock2, lock1);

    }

    private static void deadLock(Object lock1, Object lock2) {
        new Thread(() -> {
            System.out.println("Waiting " + lock1);
            synchronized (lock1) {
                System.out.println("Holding " + lock1);

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Waiting " + lock2);
                synchronized (lock2) {
                    System.out.println("Holding " + lock2);
                }
            }
        }).start();

    }


    //  My deadlock realisation:
 /*       final BankAccount first = new BankAccount("first", 1000);
        final BankAccount second = new BankAccount("second", 1000);

        new Thread(new synchedWithdraw(first, second), "Поток 1").start();
        new Thread(new synchedWithdraw(second, first), "Поток 2").start();
    }

    static class BankAccount {
        private String name;
        private int amount;

        BankAccount(String name, int amount) {
            this.name = name;
            this.amount = amount;
        }

        void withdraw(int amount) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " начал вывод " + amount + " c " + name);
            this.amount -= amount;
            System.out.println(Thread.currentThread().getName() + " закончил вывод " + amount + " c " + name + ".");
        }

        void deposit(int amount) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + " начал зачисление " + amount + " c " + name);
            this.amount += amount;
            System.out.println(Thread.currentThread().getName() + " закончил зачисление " + amount + " c " + name + ".");
        }
    }

    static class synchedWithdraw implements Runnable {
        private final BankAccount first;
        private final BankAccount second;

        synchedWithdraw(BankAccount first, BankAccount second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public void run() {
            synchronized (first) {
                System.out.println(Thread.currentThread().getName() + " получил ключ от " + first.name);

                try {
                    first.withdraw(200);
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + " пробует получить ключ от " + second.name);
                    synchronized (second) {
                        System.out.println(Thread.currentThread().getName() + " получил ключ от " + second.name);
                        second.deposit(100);
                    }
                    System.out.println(Thread.currentThread().getName() + " освободил ключ от " + second.name);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + " освободил ключ от " + first.name);
        }
    }*/

    //    private static synchronized void inc() {
    private void inc() {
        atomicInteger.incrementAndGet();
//            synchronized (this) {
//        lock.lock();
//        try {
//            counter++;
//        } finally {
//            lock.unlock();
//        }
//            }
    }
}
