package Algorithms.Sort;

import java.util.ArrayList;
import PlaygroundUtils.DataMocks;
import com.sun.org.apache.xpath.internal.operations.Bool;

public class MergeSort {

    private DataMocks generator = new DataMocks();
    private static int[] testArray;
    private static ArrayList<Integer> testArrayList;

    public static void main(String[] args){
        MergeSort mm = new MergeSort();
        mm.sort();
        System.out.println("Is sorted: " + mm.isSorted(testArrayList));
    }

    public MergeSort() {
        testArrayList = generator.randomIntArrayList(420);

        System.out.println("The size of the dataset is " + testArrayList.size());
        System.out.println("The set:");
//        System.out.println(testArrayList);
    }

    public void sort(){

    }

    public void merge(){

    }

    public boolean isSorted(ArrayList<Integer> list){
        boolean sorted = true;
        for (int i = 1; i < list.size(); i++){
            if (list.get(i - 1) > list.get(i)){
                sorted = false;
            }
        }
        return sorted;
    }
}
