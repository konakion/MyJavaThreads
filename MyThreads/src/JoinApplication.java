
import java.util.Arrays;

class RandomizeArrayThread extends Thread {
    private final double[ ] a;
    private final int li;
    private final int re;
    public RandomizeArrayThread (double[ ] a, int li, int re) {
    this.li = li;
    this.re = re;
    this.a = a;
    }
    @Override
    public void run() {
    for (int i = li; i < re; i++)
    a[i] = Math.random();
    }
}   

public class JoinApplication {
    public static void main(String[ ] args) throws InterruptedException {
        int N = 1000;
        double[ ] a = new double[N];
        Thread t1 = new RandomizeArrayThread(a, 0, N/2);
        Thread t2 = new RandomizeArrayThread(a, N/2, N);
        t1.start();
        t2.start();
        t1.join(); // Warte bis t1 zu Ende
        t2.join(); // Warte bis t2 zu Ende
        System.out.println(Arrays.toString(a));
        System.out.println("Alles fertig");
    }
}