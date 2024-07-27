class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int l = 0, r = 1;
        while (r < n) {
            if (nums[l] != nums[r]) {
                nums[l + 1] = nums[r];
                l++;
            }
            r++;
        }
        return l + 1;
    }
}