package com.askarakhmetov.leetcode;

/*
    88. Merge Sorted Array
 */

import java.util.Arrays;

public class Task88 {
    public static void main(String[] args) {
        int[] nums1 = {7, 9, 0, 0, 0};
        int m = 2;
        int[] nums2 = {1, 4, 8};
        int n = 3;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int idx_num1 = m - 1;
        int idx_num2 = n - 1;
        for (int i = m + n - 1; i >= 0; i--) {
            if (idx_num2 < 0) {
                break;
            }
            if (idx_num1 < 0) {
                System.arraycopy(nums2, 0, nums1, 0, idx_num2 + 1);
                break;
            }
            if (nums2[idx_num2] >= nums1[idx_num1]) {
                nums1[i] = nums2[idx_num2];
                idx_num2--;
            } else {
                nums1[i] = nums1[idx_num1];
                idx_num1--;
            }
        }
    }
}