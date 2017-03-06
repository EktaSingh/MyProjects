/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

/**
 *
 * @author Ekta
 */
public class Sorting {

    public long timeCalculated(long startTime, long endTime) {
        long timeElapsed = endTime - startTime;
        return timeElapsed;
    }

    int[] selSort(int[] arr) {

        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {

                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }

        return arr;
    }

    public int[] comSort(int[] list, int start, int end) {
        if (start < end) {

            int x = (end*40)/100;
            // System.out.println("Using insertion");
            int[] arr = new int[x];
            for (int i = 0; i < x; i++) {
                arr[i] = list[i];
            }

            this.insertionSort(arr);

            int n = end - x;
            if (n != 0) {
                arr = new int[n];
                for (int i = 0; i < n; i++) {
                    arr[i] = list[i + x];
                }
                // System.out.println("Using quick");
                quickSort(arr, 0, n - 1);
            }

//            // This is where we switch to Insertion Sort!
//            if ((end-start)<50)
//            {
//                this.insertionSort(list, start, end + 1);
//            }
//            else
//            {
//                int part = this.quickSortPartition(list, start, end);
//                this.comSort(list, start, part-1);
//                this.comSort(list, part + 1, end);
//            }
        }

        return list;
    }

    public int[] insertionSort(int[] arr) {
        
      int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j=i-1;
 
            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while(j>=0 && arr[j]>key){
                arr[j+1]=arr[j];
                j = j-1;
            }
            
            arr[j+1]=key;
        }

        return arr;
    }

    public int[] quickSort(int[] arr, int low, int high) {

        if (low < high) {
            int pi = quickSortPartition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }

        return arr;

    }

    private int quickSortPartition(int[] list, int leftIndex, int rightIndex) {
        int left = leftIndex;
        int right = rightIndex;
        int pivot = list[leftIndex];
        while (left < right) {
            if (list[left] < pivot) {
                left++;
                continue;
            }
            if (list[right] > pivot) {
                right--;
                continue;
            }
            int tmp = list[left];
            list[left] = list[right];
            list[right] = tmp;
            left++;
        }
        return left;
    }

    public int[] heapSort(int[] arr) {

        int n = arr.length;

        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        for (int i = n - 1; i >= 0; i--) {

            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            heapify(arr, i, 0);
        }

        return arr;
    }

    void heapify(int[] arr, int n, int i) {

        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;

        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }

        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }

        if (largest != i) {

            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;

            heapify(arr, n, largest);
        }

    }

    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    private void merge(int arr[], int l, int m, int r) {
        // 1. Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* 2. Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /* 3. Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i) {
            L[i] = arr[l + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = arr[m + 1 + j];
        }

        /* 4. Merge the temp arrays */
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* 6. Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* 7. Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using
    // merge()
    int[] mergeSort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }

        return arr;
    }

}
