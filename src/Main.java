import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static Object mon = new Object();
    static Integer num = 5;
    static Integer currentNum =1;
    static ArrayList<String> list = new ArrayList<>();


    public static void main(String[] args) {
        Thread thread1 = new Thread(()-> {
            for (int i = 0; i < num; i++) {
                synchronized (mon) {
                    while (currentNum != 1) {
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    list.add("\n" +(i+1) +" A");
                    currentNum = 2;
                    mon.notifyAll();
                }

            }
        });
        thread1.start();

        Thread thread2 = new Thread(()-> {
            for (int i = 0; i < num; i++) {
                synchronized (mon) {
                    while (currentNum != 2) {
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    list.add("B");
                    currentNum = 3;
                    mon.notifyAll();
                }

            }
        });
                thread2.start();

        Thread thread3 = new Thread(()-> {
            for (int i = 0; i < num; i++) {
                synchronized (mon) {
                    while (currentNum != 3) {
                        try {
                            mon.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    list.add("C");
                    currentNum = 1;
                    mon.notifyAll();
                }

            }
        });
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list);

    }

}











