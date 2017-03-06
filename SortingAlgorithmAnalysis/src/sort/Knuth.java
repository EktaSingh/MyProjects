/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Ekta
 */
public class Knuth {

    // this class should not be instantiated
    private Knuth() {
    }

    /**
     * Rearranges an array of objects in uniformly random order (under the
     * assumption that {@code Math.random()} generates independent and uniformly
     * distributed numbers between 0 and 1).
     *
     * @param a the array to be shuffled
     */
    public static void shuffle(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [0, i]
            int r = (int) (Math.random() * (i + 1));
            int swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    /**
     * Rearranges an array of objects in uniformly random order (under the
     * assumption that {@code Math.random()} generates independent and uniformly
     * distributed numbers between 0 and 1).
     *
     * @param a the array to be shuffled
     */
    public static void shuffleAlternate(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [i, n-1]
            int r = i + (int) (Math.random() * (n - i));
            int swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    public static int randInt(int min, int max) {

        // Usually this can be a field rather than a method variable
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    static int[] randomArr(int j) {
        // random data
        int[] arrRandom = new int[j];
        for (int i = j - 1; i >= 0; i--) {
            int num = randInt(1, 10000);
            arrRandom[i] = num;

        }
        // shuffle the array
        Knuth.shuffle(arrRandom);

        return arrRandom;
    }

    static void randomData(Sorting sort) {

        for (int j = 10000; j <= 1000000; j += 500000) {

            long[] timeElapsedMegre = new long[10];
            long[] timeElapsedQuick = new long[10];
            long[] timeElapsedInsertion = new long[10];
            long[] timeElapsedSeletion = new long[10];
            long[] timeElapsedHeap = new long[10];
            long[] timeElapsedComb = new long[10];

            System.out.println("FOR ARRAY SIZE: " + j);

            System.out.println("***************FOR RANDOM ARRAY: ***************");

            for (int k = 0; k < 10; k++) {

                int[] arrRandom = randomArr(j);
                long startTime = System.currentTimeMillis();
                sort.mergeSort(arrRandom, 0, arrRandom.length - 1);
                long endTime = System.currentTimeMillis();
                timeElapsedMegre[k] = sort.timeCalculated(startTime, endTime);

                arrRandom = randomArr(j);
                startTime = System.currentTimeMillis();
                sort.quickSort(arrRandom, 0, arrRandom.length - 1);
                endTime = System.currentTimeMillis();
                timeElapsedQuick[k] = sort.timeCalculated(startTime, endTime);

                arrRandom = randomArr(j);
                startTime = System.currentTimeMillis();
                sort.insertionSort(arrRandom);
                endTime = System.currentTimeMillis();
                timeElapsedInsertion[k] = sort.timeCalculated(startTime, endTime);

                arrRandom = randomArr(j);
                startTime = System.currentTimeMillis();
                sort.selSort(arrRandom);
                endTime = System.currentTimeMillis();
                timeElapsedSeletion[k] = sort.timeCalculated(startTime, endTime);

                arrRandom = randomArr(j);
                startTime = System.currentTimeMillis();
                sort.heapSort(arrRandom);
                endTime = System.currentTimeMillis();
                timeElapsedHeap[k] = sort.timeCalculated(startTime, endTime);

                arrRandom = randomArr(j);
                startTime = System.currentTimeMillis();
                sort.comSort(arrRandom, 0, arrRandom.length);
                endTime = System.currentTimeMillis();
                timeElapsedComb[k] = sort.timeCalculated(startTime, endTime);

            }

            int sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedMegre[i];
            }

            sum = sum / 10;
            System.out.println("Array after MERGE sort");
            //printArray(arrRandom);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedQuick[i];
            }

            sum = sum / 10;
            System.out.println("Array after QUICK sort");
            //printArray(arrRandom);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedInsertion[i];
            }

            sum = sum / 10;
            System.out.println("Array after INSERTION sort");
            //printArray(arrRandom);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedSeletion[i];
            }

            sum = sum / 10;
            System.out.println("Array after SELECTION sort");
            //printArray(arrRandom);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedHeap[i];
            }

            sum = sum / 10;
            System.out.println("Array after HEAP sort");
            //printArray(arrRandom);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedComb[i];
            }

            sum = sum / 10;
            System.out.println("Array after COMBINATION sort");
            //printArray(arrRandom);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            System.out.println("##################################################################################");
        }

        // ChartFrame frame = new ChartFrame();
    }

    static int[] partialArray(int j) {

        // partial data
        int[] arrPartial = new int[j];
        for (int i = 0; i < j; i++) {
            int temp = i;
            if (i <= arrPartial.length / 2) {
                int num = temp++;
                arrPartial[i] = num;
            } else {
                int num = randInt(0, 1000);
                arrPartial[i] = num;
            }

        }

        return arrPartial;
    }

    static void partialData(Sorting sort) {

        for (int j = 1000; j <= 60000; j += 10000) {

            long[] timeElapsedMegre = new long[10];
            long[] timeElapsedQuick = new long[10];
            long[] timeElapsedInsertion = new long[10];
            long[] timeElapsedSeletion = new long[10];
            long[] timeElapsedHeap = new long[10];
            long[] timeElapsedComb = new long[10];

            System.out.println("FOR ARRAY SIZE: " + j);

            System.out.println("***************FOR PARTIALLY SORTED ARRAY: ***************");

            for (int k = 0; k < 10; k++) {

                int[] arrPartial = partialArray(j);
                long startTime = System.currentTimeMillis();
                sort.mergeSort(arrPartial, 0, arrPartial.length - 1);
                long endTime = System.currentTimeMillis();
                timeElapsedMegre[k] = sort.timeCalculated(startTime, endTime);

                arrPartial = partialArray(j);
                startTime = System.currentTimeMillis();
                sort.quickSort(arrPartial, 0, arrPartial.length - 1);
                endTime = System.currentTimeMillis();
                timeElapsedQuick[k] = sort.timeCalculated(startTime, endTime);

                arrPartial = partialArray(j);
                startTime = System.currentTimeMillis();
                sort.insertionSort(arrPartial);
                endTime = System.currentTimeMillis();
                timeElapsedInsertion[k] = sort.timeCalculated(startTime, endTime);

                arrPartial = partialArray(j);
                startTime = System.currentTimeMillis();
                sort.selSort(arrPartial);
                endTime = System.currentTimeMillis();
                timeElapsedSeletion[k] = sort.timeCalculated(startTime, endTime);

                arrPartial = partialArray(j);
                startTime = System.currentTimeMillis();
                sort.heapSort(arrPartial);
                endTime = System.currentTimeMillis();
                timeElapsedHeap[k] = sort.timeCalculated(startTime, endTime);

                arrPartial = partialArray(j);
                startTime = System.currentTimeMillis();
                sort.comSort(arrPartial, 0, arrPartial.length);
                endTime = System.currentTimeMillis();
                timeElapsedComb[k] = sort.timeCalculated(startTime, endTime);

            }

            int sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedMegre[i];
            }

            sum = sum / 10;
            System.out.println("Array after MERGE sort");
            //printArray(arrPartial);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedQuick[i];
            }

            sum = sum / 10;
            System.out.println("Array after QUICK sort");
            //printArray(arrPartial);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedInsertion[i];
            }

            sum = sum / 10;
            System.out.println("Array after INSERTION sort");
            //printArray(arrPartial);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedSeletion[i];
            }

            sum = sum / 10;
            System.out.println("Array after SELECTION sort");
            //printArray(arrPartial);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedHeap[i];
            }

            sum = sum / 10;
            System.out.println("Array after HEAP sort");
            //printArray(arrPartial);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedComb[i];
            }

            sum = sum / 10;
            System.out.println("Array after COMBINATION sort");
            //printArray(arrPartial);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            System.out.println("##################################################################################");
        }

    }

    static int[] fullArr(int j) {

        // random data
        int[] arrFull = new int[j];
        int k = 0;
        for (int i = 0; i < j; i += 100) {
            int temp = i;

            int num = temp++;
            for (int m = 0; m < 100; m++) {

                arrFull[k] = num;
                k++;
            }
        }
        return arrFull;

    }

    static void fullData(Sorting sort) {

        for (int j = 1000; j <= 90000; j += 10000) {

            long[] timeElapsedMegre = new long[10];
            long[] timeElapsedQuick = new long[10];
            long[] timeElapsedInsertion = new long[10];
            long[] timeElapsedSeletion = new long[10];
            long[] timeElapsedHeap = new long[10];
            long[] timeElapsedComb = new long[10];

            System.out.println("FOR ARRAY SIZE: " + j);

            System.out.println("***************FOR FULLY SORTED ARRAY: ***************");
            int[] arrFull = null;
            for (int k = 0; k < 10; k++) {
                arrFull = fullArr(j);
                long startTime = System.currentTimeMillis();
                sort.mergeSort(arrFull, 0, arrFull.length - 1);
                long endTime = System.currentTimeMillis();
                timeElapsedMegre[k] = sort.timeCalculated(startTime, endTime);

                arrFull = fullArr(j);
                startTime = System.currentTimeMillis();
                sort.quickSort(arrFull, 0, arrFull.length - 1);
                endTime = System.currentTimeMillis();
                timeElapsedQuick[k] = sort.timeCalculated(startTime, endTime);

                arrFull = fullArr(j);
                startTime = System.currentTimeMillis();
                sort.insertionSort(arrFull);
                endTime = System.currentTimeMillis();
                timeElapsedInsertion[k] = sort.timeCalculated(startTime, endTime);

                arrFull = fullArr(j);
                startTime = System.currentTimeMillis();
                sort.selSort(arrFull);
                endTime = System.currentTimeMillis();
                timeElapsedSeletion[k] = sort.timeCalculated(startTime, endTime);

                arrFull = fullArr(j);
                startTime = System.currentTimeMillis();
                sort.heapSort(arrFull);
                endTime = System.currentTimeMillis();
                timeElapsedHeap[k] = sort.timeCalculated(startTime, endTime);

                arrFull = fullArr(j);
                startTime = System.currentTimeMillis();
                sort.comSort(arrFull, 0, arrFull.length);
                endTime = System.currentTimeMillis();
                timeElapsedComb[k] = sort.timeCalculated(startTime, endTime);

            }

            int sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedMegre[i];
            }

            sum = sum / 10;
            System.out.println("Array after MERGE sort");
            // printArray(arrFull);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedQuick[i];
            }

            sum = sum / 10;
            System.out.println("Array after QUICK sort");
            // printArray(arrFull);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedInsertion[i];
            }

            sum = sum / 10;
            System.out.println("Array after INSERTION sort");
            // printArray(arrFull);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedSeletion[i];
            }

            sum = sum / 10;
            System.out.println("Array after SELECTION sort");
            // printArray(arrFull);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedHeap[i];
            }

            sum = sum / 10;
            System.out.println("Array after HEAP sort");
            // printArray(arrFull);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            sum = 0;
            for (int i = 0; i < 10; i++) {

                sum += timeElapsedComb[i];
            }

            sum = sum / 10;
            System.out.println("Array after COMBINATION sort");
            // printArray(arrFull);
            System.out.println("Total Time Elapsed in miliseconds: " + sum + "\nTotal Time Elapsed in seconds: " + sum / 1000);
            System.out.println("***********");

            System.out.println("##################################################################################");
        }

    }

    public static void main(String[] args) {

        Sorting sort = new Sorting();

        Scanner s = new Scanner(System.in);
        System.out.println("Run code for"
                + "\n1. Random data"
                + "\n2. Partially sorted data"
                + "\n3. Fully sorted data");
        int n = s.nextInt();
        switch (n) {

            case 1:
                randomData(sort);
                break;
            case 2:
                partialData(sort);
                break;
            default:
                fullData(sort);
                break;
        }

        // print results.
        //for (int i = 0; i < a.length; i++)
    }
}
