import java.util.concurrent.LinkedBlockingQueue;

public class CustomThreadPool {

    //Thread pool size
    private int poolSize;

    //workers which picks up the task and execute
    private WorkerThread[] workers;

    //Queue
    private LinkedBlockingQueue<Runnable> queue;

    public CustomThreadPool(int poolSize) {
        this.poolSize = poolSize;
        queue = new LinkedBlockingQueue<>();
        workers = new WorkerThread[poolSize];


        for (int i = 0; i < poolSize; i++) {
            workers[i] = new WorkerThread();
            workers[i].start();

        }
    }

    public void execute(Runnable task) {
        synchronized (queue) {
            queue.add(task);
            queue.notify();
        }
    }


    private class WorkerThread extends Thread {

        public void run() {
            Runnable task;

            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try{
                            queue.wait();
                        }catch (InterruptedException e) {
                            System.out.println(e);
                        }
                    }
                    task = queue.poll();
                }

                try{

                    task.run();
                }catch (RuntimeException e) {
                    System.out.println(e);
                }
            }

        }

    }

    public void shutdown() {
        System.out.println("ShuttingDown threadpool");
        for (int i = 0; i < poolSize; i++) {
            workers[i] = null;
        }
    }

}