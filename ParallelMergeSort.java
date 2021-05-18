import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort extends RecursiveAction {
    private final int start;
    private final int end;
    private final int[] arr;
    private int length;
    private static final int THRESHOLD = 100;

    private void InsertionSort() {
        for (int i = start + 1; i < end + 1; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[start..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= start && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    private void merge(int lo, int mid, int hi) {
        int[] buf = Arrays.copyOfRange(arr, lo, mid);
        for (int i = 0, j = lo, k = mid; i < buf.length; j++)
            arr[j] = (k == hi || buf[i] < arr[k]) ?
                    buf[i++] : arr[k++];
    }

    @Override
    protected void compute() {
        // check if length of current partition is greater than the threshold
        if (length >= THRESHOLD) {
            //return the mid index for merge sort
            int mid = (start + end + 1) >>> 1;

            //divide array
            ParallelMergeSort left = new ParallelMergeSort(start, mid - 1, arr);

            ParallelMergeSort right = new ParallelMergeSort(mid, end, arr);

            invokeAll(left, right);

            merge(start, mid, end);
        } else {
            InsertionSort();
        }
    }

    public ParallelMergeSort(int start, int end, int [] arr) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.length = end - start + 1;
    }

    public void print() {
        for (int i = 0; i < arr.length; i++)
            System.out.print(arr[i] + " ");
    }

    public static void InsertionSort(int[] array) {
        int n = array.length;
        for (int i = 1; i < n; ++i) {
            int key = array[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    public static void main(String args[]){
        int n;
        int []arr;
        int []compare;
        long start, end, duration;
        // Forkjoin ThreadPool to keep
        // thread creation as per resources
        ForkJoinPool pool = ForkJoinPool.commonPool();

        try {
            File file = new File("test1.txt");
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                arr = new int[n];
                compare = new int[n];
                int i = 0;
                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        arr[i++] = scanner.nextInt();
                        compare[i - 1] = arr[i - 1];
                    } else {
                        scanner.next();
                    }
                }


                start = System.nanoTime();
                // Start the first thread in fork
                // join pool for range 0, n-1
                pool.invoke(
                        new ParallelMergeSort(
                                0, n - 1, arr));
                end = System.nanoTime();
                duration = end - start;

                for (int j = 0; j < n; j++)
                    System.out.print(arr[j] + " ");
                System.out.println();
                System.out.println("Parallel MergeSort execution time for n = " + n + ": " + duration + " ns");
                start = System.nanoTime();
                InsertionSort(compare);
                end = System.nanoTime();
                duration = end - start;
                System.out.println("Insertion Sort execution time for n = " + n + ": " + duration + " ns");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            File file = new File("test2.txt");
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                arr = new int[n];
                compare = new int[n];
                int i = 0;
                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        arr[i++] = scanner.nextInt();
                        compare[i - 1] = arr[i - 1];
                    } else {
                        scanner.next();
                    }
                }


                start = System.nanoTime();
                // Start the first thread in fork
                // join pool for range 0, n-1
                pool.invoke(
                        new ParallelMergeSort(
                                0, n - 1, arr));
                end = System.nanoTime();
                duration = end - start;

                for (int j = 0; j < n; j++)
                    System.out.print(arr[j] + " ");
                System.out.println();
                System.out.println("Parallel MergeSort execution time for n = " + n + ": " + duration + " ns");
                start = System.nanoTime();
                InsertionSort(compare);
                end = System.nanoTime();
                duration = end - start;
                System.out.println("Insertion Sort execution time for n = " + n + ": " + duration + " ns");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            File file = new File("test3.txt");
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                arr = new int[n];
                compare = new int[n];
                int i = 0;
                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        arr[i++] = scanner.nextInt();
                        compare[i - 1] = arr[i - 1];
                    } else {
                        scanner.next();
                    }
                }


                start = System.nanoTime();
                // Start the first thread in fork
                // join pool for range 0, n-1
                pool.invoke(
                        new ParallelMergeSort(
                                0, n - 1, arr));
                end = System.nanoTime();
                duration = end - start;

                for (int j = 0; j < n; j++)
                    System.out.print(arr[j] + " ");
                System.out.println();
                System.out.println("Parallel MergeSort execution time for n = " + n + ": " + duration + " ns");
                start = System.nanoTime();
                InsertionSort(compare);
                end = System.nanoTime();
                duration = end - start;
                System.out.println("Insertion Sort execution time for n = " + n + ": " + duration + " ns");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            File file = new File("test.txt");
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextInt()) {
                n = scanner.nextInt();
                arr = new int[n];
                compare = new int[n];
                int i = 0;
                while (scanner.hasNext()) {
                    if (scanner.hasNextInt()) {
                        arr[i++] = scanner.nextInt();
                        compare[i - 1] = arr[i - 1];
                    } else {
                        scanner.next();
                    }
                }


                start = System.nanoTime();
                // Start the first thread in fork
                // join pool for range 0, n-1
                pool.invoke(
                        new ParallelMergeSort(
                                0, n - 1, arr));
                end = System.nanoTime();
                duration = end - start;

                for (int j = 0; j < n; j++)
                    System.out.print(arr[j] + " ");
                System.out.println();
                System.out.println("Parallel MergeSort execution time for n = " + n + ": " + duration + " ns");
                start = System.nanoTime();
                InsertionSort(compare);
                end = System.nanoTime();
                duration = end - start;
                System.out.println("Insertion Sort execution time for n = " + n + ": " + duration + " ns");
            }
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
