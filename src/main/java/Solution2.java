import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Solution2 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 100; i++) {

            executorService
                    .submit(new AB());
        }

        executorService.shutdown();
    }
}

class AB extends Thread{

    static int num;

    public void run(){
        synchronized (this){
            System.out.println(num++);
        }
    }
}
