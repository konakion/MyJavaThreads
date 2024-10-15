// public class MultiThreadJoin {
//     public static void main(String[] args)throws InterruptedException{
//         Thread t = new Thread();
//         t.start();
//         // irgendwelche Berechnungen des main-Threads: ...
//         t.join(); // Warte, bis der Thread t fertig ist
//         // ... weitere Berechnungen des main-Threads
//     }
// }

public class MultiThreadJoin {
    
    public static void main(String[] args) throws InterruptedException {
        // Erstelle 5 Threads
        for (int i = 1; i <= 5; i++) {
            // Jeder Thread bekommt einen eindeutigen Namen, z.B. "Thread-1", "Thread-2", usw.
            Thread thread = new Thread(new CountingTask("Thread-" + i));
            thread.start(); // Startet den Thread
            thread.join(); // Warte, bis der Thread fertig ist
        }
        System.out.println("Main ist fertig");
    }
}

class CountingTask implements Runnable {
    private final String threadName;

    // Konstruktor für den Thread, um den Namen zu setzen
    public CountingTask(String name) {
        this.threadName = name;
    }

    @Override
    public void run() {
        // Iteriere von 0 bis 99
        for (int i = 0; i <= 99; i++) {
            System.out.println(threadName + ": " + i);
        }
    }
}
