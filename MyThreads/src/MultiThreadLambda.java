// public class MultiThreadLambda {
//         public static void main(String[] args) {
//             Runnable myRun = () -> {
//                 System.out.println("myRun läuft");
//             };
//             Thread t = new Thread(myRun);
//             t.start();
//         }
// }

public class MultiThreadLambda {
    public static void main(String[] args) {
        new Thread (() -> {
            System.out.println("myRun läuft");
        }).start();
    }
}