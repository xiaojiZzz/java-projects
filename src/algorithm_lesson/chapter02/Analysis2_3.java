package algorithm_lesson.chapter02;

@SuppressWarnings({"all"})
//算法分析题2-3
public class Analysis2_3 {
    public int[] binarySearch(int[] a, int x, int left, int right) {
        int mid = -1;
        while (left <= right) {
            mid = (left + right) / 2;
            if (x == a[mid]) {
                return new int[]{mid, mid};
            }
            if (x > a[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return new int[]{right, left};
    }
}
