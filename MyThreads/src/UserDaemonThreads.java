public class UserDaemonThreads {
    public static void main(String[] args) {
        Thread bgThread = new Thread(new DaemonHelper());
        Thread usrThread = new Thread(new UserHelper());
        bgThread.setDaemon(true);

        bgThread.start();
        usrThread.start();
    }
}

class DaemonHelper implements Runnable {
    @Override
    public void run() {
        int counter = 0;
        while (counter < 500) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            counter++;
            System.out.println("Daemon helper running ...");
        }
    }
}

class UserHelper implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("User thread done with execution.");
    }
}