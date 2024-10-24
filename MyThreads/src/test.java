class CountingThread implements Runnable {
    private final String threadName;
  
    public CountingThread(String name) {
      this.threadName = name;
    }
    
    @Override
    public void run() {
      for (int i = 0; i < 100; i++) {
        System.out.println(threadName + ": " + i);
      }
    }
  }
  
  public class test {
    public static void main(String[] args) {
      for (int i = 1; i <= 5; i++) {
        Thread thread = new Thread(new CountingThread("Thread-" + i));
        thread.start();
      }
      System.out.println("Main ist fertig");
    }
  }
  