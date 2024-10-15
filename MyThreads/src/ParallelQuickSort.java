public class ParallelQuickSort {
    
    public static void sort(int[] a) {
        int maxDepth = 2;  // maximale Rekursionstiefe für paralleles Sortieren
        Thread sortThread = new QuickSortThread(a, 0, a.length - 1, maxDepth);
        sortThread.start();
        try {
            sortThread.join();  // Warten, bis der Thread fertig ist
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Thread-Klasse für paralleles QuickSort
    static class QuickSortThread extends Thread {
        private int[] a;
        private int li;
        private int re;
        private int maxDepth;

        public QuickSortThread(int[] a, int li, int re, int maxDepth) {
            this.a = a;
            this.li = li;
            this.re = re;
            this.maxDepth = maxDepth;
        }

        @Override
        public void run() {
            if (li >= re) return;

            int i = partition3Median(a, li, re);

            if (maxDepth <= 0) {
                // Sequentielles QuickSort, wenn die maximale Tiefe erreicht ist
                quickSort(a, li, i - 1);
                quickSort(a, i + 1, re);
            } else {
                Thread tli = null;
                Thread tre = null;
                
                if (li < i - 1) {
                    tli = new QuickSortThread(a, li, i - 1, maxDepth - 1);
                    tli.start();
                }

                if (i + 1 < re) {
                    tre = new QuickSortThread(a, i + 1, re, maxDepth - 1);
                    tre.start();
                }

                try {
                    if (tli != null) tli.join();  // Warten, bis der linke Thread fertig ist
                    if (tre != null) tre.join();  // Warten, bis der rechte Thread fertig ist
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Partitionierung mit 3-Median-Strategie
        private int partition3Median(int[] a, int li, int re) {
            int pivot = a[re];
            int i = li - 1;
            for (int j = li; j < re; j++) {
                if (a[j] < pivot) {
                    i++;
                    swap(a, i, j);
                }
            }
            swap(a, i + 1, re);
            return i + 1;
        }

        // Hilfsmethode zum Tauschen von Elementen
        private void swap(int[] a, int i, int j) {
            int temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        // Sequentielles QuickSort
        private void quickSort(int[] a, int li, int re) {
            if (li < re) {
                int i = partition3Median(a, li, re);
                quickSort(a, li, i - 1);
                quickSort(a, i + 1, re);
            }
        }
    }

    // Testmethode
    public static void main(String[] args) {
        int[] array = {9, 7, 5, 11, 12, 2, 14, 3, 10, 6};
        System.out.println("Unsortiertes Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        sort(array);  // Paralleles QuickSort starten

        System.out.println("Sortiertes Array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}