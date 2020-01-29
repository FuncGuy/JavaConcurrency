import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


public class Main
{
    public static void main(String[] args)
    {
        final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        RejectedTaskHandler handler = new RejectedTaskHandler();

        executor.setRejectedExecutionHandler(handler);

        for (int i = 0; i < 10; i++)
        {
            Task task=new Task("Task-"+i);
            executor.execute(task);
        }

        //shut down the executor so that new tasks will be rejected
        executor.shutdown();

        System.out.println("<------------ SIZE ------------>" + executor.getQueue().size());

        int queued = executor.getQueue().size();
        int active = executor.getActiveCount();
        int notCompleted = queued + active; // approximate

        System.out.println("<------------ SIZE ------------>" + notCompleted);

        Task task = new Task("Rejected task");
        executor.execute(task);
    }
}