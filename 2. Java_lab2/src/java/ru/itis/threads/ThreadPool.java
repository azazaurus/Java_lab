package ru.itis.threads;

import java.util.Deque;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ThreadPool {
    private Deque<Runnable> tasks;

    private PoolWorker[] pool;

    public ThreadPool(int threadsCount) {
        pool = new PoolWorker[threadsCount];
        for (int i = 0; i < pool.length; ++i)
            pool[i] = new PoolWorker(); // почему нельзя инициализировать в методе newPool?
    }

    public static ThreadPool newPool(int threadsCount) {
        ThreadPool threadPool = new ThreadPool(threadsCount);
        threadPool.tasks = new ConcurrentLinkedDeque<>();

        for (int i = 0; i < threadPool.pool.length; i++) {
            threadPool.pool[i].start();
        }

        return threadPool;
    }

    public void submit(Runnable task) {
        tasks.addLast(task);
    }

    private class PoolWorker extends Thread {
        @Override
        public void run() {
            Runnable task;
            while (true) {
                if (!tasks.isEmpty()) {
                    task = tasks.pollFirst();
                    if (task != null)
                        task.run();

                } else {
                    try {
                        sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}




