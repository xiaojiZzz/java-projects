package data_structure_and_algorithm;

import java.util.Random;


//快速排序
public class QuickSort {

    private Random random = new Random();

    public void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotpos = partition(arr, low, high);
            quickSort(arr, low, pivotpos - 1);
            quickSort(arr, pivotpos + 1, high);
        }
    }

    public int partition(int[] arr, int low, int high) {
        int pivotIndex = low + random.nextInt(high - low + 1);
        int pivot = arr[pivotIndex];
        swap(arr, pivotIndex, low);
        while (low < high) {
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
