import algo.Sorts;
import containers.ArrayList;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> arrayList = new ArrayList<>(new Integer[] { 1, 5, 0, -2, -1, 4, 5, 4, 3, 8, 1, 9, 0, 20, -25, 133 });
        arrayList.sort(Comparator.naturalOrder(), Sorts.SortingType.TIMSORT);
        System.out.println(arrayList);
        ArrayList<Integer> arrayList1 = new ArrayList<>(new Integer[] { 2, 3, 1, 10000, -1, 0, 0, 10000, -1, 5, 3, 4, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 20, 14, 11, 12, 11, 54, 56, 57, 58, 59, 60, 61, 62, 63, 63, 64, 0, -1, 25, 100, 1000, -100, -1000, 1, 19, 20, 1, -19, -20, -3, -4, -5, 5, -4, 5, 3 });
        arrayList1.sort(Comparator.naturalOrder(), Sorts.SortingType.TIMSORT);
        System.out.println(arrayList1);
        ArrayList<Integer> arrayList2 = new ArrayList<>(new Integer[] { 10000, 0, 0, 10000, -1, 5, 3, 4, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 1, 12, 11, 54, 56, 57, 58, 59, 60, 61, 62, 63, 63, 64, 0, -1, 25, 100, 1000, -100, -1000, 1, 19, 20, 1, -19, -20, -3, -4, -5, 5, -4, 5, 3 });
        arrayList2.sort(Comparator.naturalOrder(), Sorts.SortingType.TIMSORT);
        System.out.println(arrayList2);
        ArrayList<Integer> arrayList3 = new ArrayList<>(new Integer[] { 10000, 0, 0, 10000, -1, 5, 3, 4, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 1, 12, 11, 54, 56, 57, 58, 59, 60, 61, 62, 63, 63, 64, 0, -1, 25, 100, 1000, -100, -1000, 1, 19, 20, 1, -19, -20, -3, -4, -5, 5, -4, 5, 3 });
        for (int i = 0; i < 32; i++)
            arrayList3.removeFirst();
        arrayList3.sort(Comparator.naturalOrder(), Sorts.SortingType.INSERTION_SORT);
        System.out.println(arrayList3);
    }
}