package leetcode.array;

import java.util.Arrays;

/**
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 *
 * <pre>
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 *
 *
 * [1,2,3,4,5,6,7]
 * [6,7,1,2,3,4,5]
 *
 * [1,2,3,4,5,6,7,8,9,10,11,12,13,14,15]
 * [12,13,14,15,5,6,7,8,9,10,11,1,2,3,4]
 * [12,13,14,15,1,2,3,4,9,10,11,5,6,7,8]
 * [12,13,14,15,1,2,3,4,5,6,7,9,10,11,8]
 * [12,13,14,15,1,2,3,4,5,6,7,8,10,11,9]
 * [12,13,14,15,1,2,3,4,5,6,7,8,9,11,10]
 * [12,13,14,15,1,2,3,4,5,6,7,8,9,10,11]
 *
 * [6,7,8,4,5,1,2,3]
 * [4,5,6,7,8,2,3,1]
 *
 * [1,2,3,4,5,6,7,8]
 * [5,2,3,4,1,6,7,8]
 * [5,6,7,8,1,2,3,4]
 *
 *
 * [7,8,3,4,5,6,1,2]
 * [7,8,1,4,5,6,3,2]
 * [7,8,1,2,5,6,3,4]
 * [7,8,1,2,3,6,5,4]
 * [7,8,1,2,3,4,5,6]
 *
 * [7,8,1,2,3,4,5,6]
 *
 * [4,5,6,7,1,2,3]
 * [3,4,5,6,7,2,1]
 *
 * [7,1,2,3,4,5,6]
 *
 * [6,7,1,2,3,4,5]
 *
 * [5,6,7,4,1,2,3]
 *
 * [3,4,5,6,7,1,2]
 *
 * </pre>
 */
public class ArrayRotate {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        rotate(arr, 12);
        System.out.println("arr = " + Arrays.toString(arr));
    }

    public static void rotate(int[] arr, int count) {
        if (arr == null || arr.length == 0 || arr.length == count || count <= 0 || (count > arr.length && count % arr.length == 0)) {
            System.out.printf("ignore");
            return;
        }
        int length = arr.length;
        if (count > length)
            count = count % length;

        System.out.println("count = " + count);

        int lastPoint = length - 1;

        if (count == 1 || count == lastPoint) {
            int movePoint = length - count;
            for (int i = 0; i < length - 1; i++) {
                int right = arr[movePoint];
                arr[movePoint] = arr[i];
                arr[i] = right;

                if (movePoint < lastPoint) {
                    movePoint++;
                }
            }
        } else {
            swapRightToLeft(0, count, arr);
        }
    }

    private static void swapRightToLeft(int nextStartIndex, int count, int[] arr) {
        int length = arr.length - nextStartIndex;
        int center = length / 2;
        if (count == center && length % 2 == 0) {//刚好对称
            for (int i = 0; i < center; i++) {
                int right = arr[center + i + nextStartIndex];
                arr[center + i + nextStartIndex] = arr[i + nextStartIndex];
                arr[i + nextStartIndex] = right;
            }
            System.out.println();
            System.out.println("A count = " + count);
            System.out.println("A: " + Arrays.toString(arr));
        } else {
            if (count <= center) {
                for (int i = 0; i < count; i++) {
                    int leftValue = arr[i + nextStartIndex];
                    int rightIndex = length - count + i + nextStartIndex;
                    arr[i + nextStartIndex] = arr[rightIndex];
                    arr[rightIndex] = leftValue;
                }
                System.out.println();
                System.out.println("B count = " + count);
                System.out.println("B: " + Arrays.toString(arr));
                swapRightToLeft(nextStartIndex + count, count, arr);
            } else {
                int swapCount = length - count;

                /*if (swapCount == 1) {
                    *//*int lastPoint = length - 1;
                    int movePoint = length - swapCount;
                    for (int i = nextStartIndex; i < arr.length - 1; i++) {
                        int right = arr[movePoint];
                        arr[movePoint] = arr[i];
                        arr[i] = right;

                        if (movePoint < lastPoint) {
                            movePoint++;
                        }
                    }*//*
                } else */{
                    for (int i = 0; i < swapCount; i++) {
                        int leftValue = arr[i + nextStartIndex];
                        int rightIndex = swapCount + i + nextStartIndex;
                        arr[i + nextStartIndex] = arr[rightIndex];
                        arr[rightIndex] = leftValue;
                    }
                    System.out.println();
                    System.out.println("C count = " + swapCount);
                    System.out.println("C: " + Arrays.toString(arr));
                    swapRightToLeft(nextStartIndex + swapCount, swapCount, arr);
                }
            }
        }
    }
}
