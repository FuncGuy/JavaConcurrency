import java.util.concurrent.atomic.AtomicInteger;

public class Solution {


    public static void main(String args[]) throws Exception {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT */
        A t1 = new A();
        t1.name = "Thread one";
        A t2 = new A();
        t2.name = "Thread two";
        A t3 = new A();
        t3.name = "Thread three";
        A t4 = new A();
        t4.name = "Thread four";
        A t5 = new A();
        t5.name = "Thread five";
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}




class A extends Thread{
    String name = "";
    static Integer num = 1;
    public void run(){
        while(num <= 100){

            synchronized(num){

                System.out.println(name + " " +num);
                num++;

            }

            try{
                Thread.sleep(100);
            }catch(InterruptedException e){}


        }
    }
}