package com.askarakhmetov.leetcode;

import java.util.Arrays;

/*
    27. Remove Element
 */
public class Task27 {
    public static void main(String[] args) {
        int[] nums = {0,1,2,2,3,0,4,2};
        int val = 2;
        int k = removeElement(nums, val);
        System.out.println(Arrays.toString(nums));
        System.out.println(k);
    }

    public static int removeElement(int[] nums, int val) {
        int k = 0;
        int j = nums.length - 1;
        for (int i = 0; i < nums.length; i++) {
            int elem = nums[i];
            if (elem == val) {
                for (; j != i; j--) {
                    if (nums[j] != val) {
                        nums[i] = nums[j];
                        nums[j] = elem;
                        k++;
                        break;
                    }
                }
            } else {
                k++;
            }
            if (j == i) {
                break;
            }
        }
        return k;
    }
}
