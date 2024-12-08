/**
 *  #Leetcode(162 )| https://leetcode.com/problems/find-peak-element/
 *
 *      [ A peak element is an element that is strictly
 *          greaterthan its neighbors.]
 */



public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        int n = nums.length;

        if (n == 1 || nums[0] > nums[1]) return 0;

        if (nums[n - 2] < nums[n - 1]) return (n - 1);

        int low = 1;
        int high = n - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1] ) return mid;

            if (nums[mid - 1] < nums[mid]) low = mid + 1;

            else high = mid - 1;

        }

        return -1;
    }
}