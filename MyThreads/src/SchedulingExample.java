public class SchedulingExample {
    public static void main(String[] args) {
        (new Start()).start();
        (new Start()).start();
        (new Start()).start();
        System.out.println("main is running ...");
        while (true);
    }
        // end of class Start
}

class Start extends Thread {
    public void run() {
    System.out.println("I’m running ...");
    while (true);
    }
}