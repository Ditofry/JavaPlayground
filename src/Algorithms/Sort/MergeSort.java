package Algorithms.Sort;

import java.util.ArrayList;
import PlaygroundUtils.DataMocks;

/**
 * Simple divide and conquer algorithm that runs in O(nlog(n))
 * I did this using arrays, the idea being that when you "divide" and pass a
 * "smaller chunk" to the next recursion, you're actually just passing the same input
 * with updated Min and Max values.
 */
public class MergeSort {

    public void sort(int input[]){ divideAndConquer(input, 0, input.length - 1); }

    public void divideAndConquer(int input[], int low, int high){ // Not sure if there's a better way of splitting array in Java
        // Base Case (if low <= high our size is 0 or 1)
        if (low >= high){ return; }

        // Cache the mid for our split operation
        int mid = (low + high) / 2;

        // Divide left side
        divideAndConquer(input, low, mid);

        // divide right side
        divideAndConquer(input, mid + 1, high);

        // merge input that have been subdivided
        merge(input, low, high);
    }

    public void merge(int input[], int low, int high){
        // create a temp array bounded by the high and low range we're looking at.
        int temp[] = new int[high - low +1];
        int mid = (low + high) / 2;
        // Write to array in proper order
        // Since things are partitioned between the two midpoints, we likely are looking at an array segment
        // has an ordering in two partitions, so we can effectively treat the two halves of the input that we received
        // as two arrays
        // So we create iteration trackers for each portion, as well as an iterator for the temp array:
        int i = low;
        int j = mid + 1;
        int k = 0;
        // And now we loop
        while(i <=  mid && j <= high){
            if (input[i] < input[j]){
                temp[k++] = input[i++];
            } else {
                temp[k++] = input[j++];
            }
        }
        // If i finishes first then we need to go through the rest of j
        while(j <= high){
            temp[k++] = input[j++];
        }
        // If j finishes first then we need to go through the rest of i
        while(i <= mid){
            temp[k++] = input[i++];
        }

        // use temp array to re-write the elements of the input array(section)
        i = low;
        for (int r = 0; r < temp.length;){
            input[i++] = temp[r++];
        }
    }

    public boolean isSorted(int[] input){
        boolean sorted = true;
        for (int i = 1; i < input.length; i++){
            if (input[i - 1] > input[i]){
                sorted = false;
            }
        }
        return sorted;
    }

    public void printArray(int arr[]){
        for (int item : arr){
            System.out.print(item + " ");
        }
    }

    public static void main(String args[]){
        int input1[] = {1};
        int input2[] = {4,2};
        int input3[] = {6,2,9};
        int input4[] = {6,-1,10,4,11,14,19,12,18};
        MergeSort ms = new MergeSort();
        ms.sort(input1);
        ms.sort(input2);
        ms.sort(input3);
        ms.sort(input4);

        ms.printArray(input1);
        ms.printArray(input2);
        ms.printArray(input3);
        ms.printArray(input4);
    }
}
