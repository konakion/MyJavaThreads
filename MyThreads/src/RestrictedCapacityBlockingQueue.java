import java.util.LinkedList;
import java.util.Queue;

// RestrictedCapacityBlockingQueue-Klasse
class RestrictedCapacityBlockingQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int cap = 5;  // Kapazität der Queue

    // synchronized Methode zum Hinzufügen eines Elements zur Queue
    public synchronized void add(int x) {
        while (queue.size() >= cap)
            wait();
        queue.add(x);
        System.out.println("added: " + queue.size());
        notifyAll();  // Benachrichtigt wartende Threads, dass ein neues Element hinzugefügt wurde
    }

    // synchronized Methode zum Entfernen eines Elements aus der Queue
    public synchronized int remove() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();  // Wartet, bis ein Element in der Queue vorhanden ist
        }
        int x = queue.poll(); // Holt das erste Element aus der Queue
        System.out.println("removed: " + queue.size());
        notifyAll();
        return x;
    }
}

// Producer-Klasse (Erzeuger-Thread)
class Producer extends Thread {
    private final RestrictedCapacityBlockingQueue bq;
    private final int start;

    public Producer(RestrictedCapacityBlockingQueue bq, int start) {
        this.bq = bq;
        this.start = start;
    }

    @Override
    public void run() {
        // Schreibt 100 Zahlen in die RestrictedCapacityBlockingQueue
        for (int i = start; i < start + 100; i++) {
            bq.add(i);
        }
    }
}

// Consumer-Klasse (Verbraucher-Thread)
class Consumer extends Thread {
    private final RestrictedCapacityBlockingQueue bq;
    private final String name;

    public Consumer(RestrictedCapacityBlockingQueue bq, String name) {
        this.bq = bq;
        this.name = name;
    }

    @Override
    public void run() {
        // Holt 150 Zahlen aus der RestrictedCapacityBlockingQueue und gibt sie aus
        for (int i = 0; i < 150; i++) {
            try {
                int value = bq.remove();
                System.out.println(name + ": " + value);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

// Hauptklasse zur Ausführung des Producer-Consumer-Beispiels
public class ProducerConsumerExample {
    public static void main(String[] args) {
        RestrictedCapacityBlockingQueue bq = new RestrictedCapacityBlockingQueue();

        // Erzeuge drei Producer-Threads
        Producer p1 = new Producer(bq, 0);
        Producer p2 = new Producer(bq, 1000);
        Producer p3 = new Producer(bq, 1000000);

        // Erzeuge zwei Consumer-Threads
        Consumer c1 = new Consumer(bq, "Consumer 1");
        Consumer c2 = new Consumer(bq, "Consumer 2");

        // Starte alle Producer- und Consumer-Threads
        p1.start();
        p2.start();
        p3.start();
        c1.start();
        c2.start();
    }
}