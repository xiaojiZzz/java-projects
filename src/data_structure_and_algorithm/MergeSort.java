package data_structure_and_algorithm;

// 归并排序
public class MergeSort {
    public void mergeSort(int[] arr, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(arr, low, mid);
            mergeSort(arr, mid + 1, high);
            merge(arr, low, mid, high);
        }
    }

    public void merge(int[] arr, int low, int mid, int high) {
        int len = high - low + 1;
        int[] tmp = new int[len];
        int idx = 0;
        int left = low;
        int right = mid + 1;
        while (left <= mid && right <= high) {
            tmp[idx++] = arr[left] <= arr[right] ? arr[left++] : arr[right++];
        }
        while (left <= mid) {
            tmp[idx++] = arr[left++];
        }
        while (right <= high) {
            tmp[idx++] = arr[right++];
        }
        for (int i = 0; i < len; i++) {
            arr[low + i] = tmp[i];
        }
    }
}
