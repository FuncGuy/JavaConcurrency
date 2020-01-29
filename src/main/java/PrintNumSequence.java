import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintNumSequence
{
    public static void main(String[] args)
    {
        /*AtomicInteger atomicInteger = new AtomicInteger(0);
        new NumPrinter(atomicInteger, 0).start();// thread0
        new NumPrinter(atomicInteger, 1).start();// thread1
        new NumPrinter(atomicInteger, 2).start();// thread2
*/
    }
}

enum Color {
    red, blue, green;
}

class NumPrinter extends Thread
{

    private AtomicInteger   atomicInteger;
    private int             threadNum;

    public NumPrinter(AtomicInteger atomicInteger, int threadNum)
    {
        this.atomicInteger = atomicInteger;
        this.threadNum = threadNum;
    }

    @Override
    public void run()
    {
        int num = atomicInteger.intValue();
        do
        {
            synchronized (atomicInteger)
            {
                num = atomicInteger.intValue();
                // If number is 9 then stop.
                if (num > 100)
                {
                    atomicInteger.notifyAll();
                    break;
                }
                // 3 is number of threads
                if ((num % 3) == threadNum)
                {
                    System.out.println("Thread-" + threadNum + " -->" + num);
                    num = atomicInteger.incrementAndGet();

                }
                atomicInteger.notifyAll();
                try
                {
                    atomicInteger.wait();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        } while (true);
    }
}