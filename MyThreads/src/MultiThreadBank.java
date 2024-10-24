public class MultiThreadBank {
    static class BankAccount {
        private int balance;
        public BankAccount(int initialBalance) {balance = initialBalance; }
        public synchronized int getBalance() {return balance; }
        public synchronized void deposit(int amount) {balance += amount; }
    }

    static class Customer implements Runnable {
        private BankAccount account;
        private int amount;
        public Customer(BankAccount a, int d) { account = a; amount = d; }
        public void run() {
        for (int i = 0; i < 1000; i++) account.deposit(amount);
        }
    }

    public static void main(String[] args)  throws InterruptedException {
        BankAccount a = new BankAccount(1000);
        Thread kunde1 = new Thread(new Customer(a, +10));
        Thread kunde2 = new Thread(new Customer(a, -10));
        kunde1.start(); kunde2.start();
        kunde1.join(); kunde2.join();
        System.out.println(a.getBalance());
    }
}