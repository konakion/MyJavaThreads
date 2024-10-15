class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void method1() {
        synchronized (lock1) {
            System.out.println("Thread 1: Holding lock 1...");

            try { Thread.sleep(10); } catch (InterruptedException e) {}

            System.out.println("Thread 1: Waiting for lock 2...");
            synchronized (lock2) {
                System.out.println("Thread 1: Holding lock 1 & 2...");
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            System.out.println("Thread 2: Holding lock 2...");

            try { Thread.sleep(10); } catch (InterruptedException e) {}

            System.out.println("Thread 2: Waiting for lock 1...");
            synchronized (lock1) {
                System.out.println("Thread 2: Holding lock 1 & 2...");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        DeadlockExample deadlock = new DeadlockExample();

        Thread t1 = new Thread(deadlock::method1);
        Thread t2 = new Thread(deadlock::method2);

        t1.start();
        t2.start();
    }
}
