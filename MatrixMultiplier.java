public class MatrixMultiplier {

    static final int SIZE = 3;

    static int[][] A = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 9}
    };

    static int[][] B = {
        {9, 8, 7},
        {6, 5, 4},
        {3, 2, 1}
    };

    static int[][] C = new int[SIZE][SIZE];

    static class MultiplyRow extends Thread {
        int row;

        MultiplyRow(int row) {
            this.row = row;
        }

        public void run() {
            for (int i = 0; i < SIZE; i++) {
                C[row][i] = 0;
                for (int j = 0; j < SIZE; j++) {
                    C[row][i] += A[row][j] * B[j][i];
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread[] threads = new Thread[SIZE];

        for (int i = 0; i < SIZE; i++) {
            threads[i] = new MultiplyRow(i);
            threads[i].start();
        }

        for (int i = 0; i < SIZE; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Result matrix:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }
    }
}