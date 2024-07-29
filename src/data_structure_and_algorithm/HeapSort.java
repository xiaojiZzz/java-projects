package data_structure_and_algorithm;

// 堆排序，建堆：o(n)，排序：o(nlogn)
public class HeapSort {
    public void heapSort(int[] arr) {
        heapify(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            adjustHeap(arr, 0, i);
        }
    }

    private void heapify(int[] arr) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, n);
        }
    }

    private void adjustHeap(int[] arr, int i, int n) {
        while (2 * i + 1 < n) {
            int j = 2 * i + 1;
            if (j + 1 < n && arr[j + 1] > arr[j]) {
                j++;
            }
            if (arr[j] <= arr[i]) {
                break;
            }
            swap(arr, i, j);
            i = j;
        }
    }

    private void swap(int[] arr, int i, int j) {
        int tmp = arr[j];
        arr[j] = arr[i];
        arr[i] = tmp;
    }
}
