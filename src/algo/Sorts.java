package algo;

import containers.ArrayList;
import containers.List;
import containers.Pair;

import java.util.Comparator;

public final class Sorts {

    private Sorts() {
        new Error("Forbidden instance of class " + Sorts.class).printStackTrace();
        System.exit(1);
    }


    public enum SortingType {

        DEFAULT,
        SELECTION_SORT,
        BUBBLE_SORT,
        INSERTION_SORT,
        MERGE_SORT,
        QUICK_SORT,
        HEAP_SORT,
        TIMSORT;

        private static SortingType currentDefault = TIMSORT;

        public static SortingType getCurrentDefault() {
            return currentDefault;
        }

        public static void setCurrentDefault(SortingType currentDefault) {
            if (currentDefault == DEFAULT)
                throw new RuntimeException();
            SortingType.currentDefault = currentDefault;
        }
    }


    /**
     * Standard sorting for a list.
     * Sorting with <a href="https://en.wikipedia.org/wiki/Timsort">Timsort</a> as default.
     */
    public static <T> void sort(List<T> list, Comparator<? super T> comparator) {
        sort(list, comparator, SortingType.DEFAULT);
    }

    /**
     * Standard sorting for a list.
     * Sorting with <a href="https://en.wikipedia.org/wiki/Timsort">Timsort</a> as default.
     * Sorting from start inclusive to end exclusive: [start, end).
     */
    public static <T> void sort(List<T> list, int start, int end, Comparator<? super T> comparator) {
        sort(list, start, end, comparator, SortingType.DEFAULT);
    }

    public static <T> void sort(List<T> list, Comparator<? super T> comparator, SortingType sortingType) {
        sort(list, 0, list.size(), comparator, sortingType);
    }

    public static <T> void sort(List<T> list, int start, int end, Comparator<? super T> comparator, SortingType sortingType) {
        if (sortingType == SortingType.DEFAULT)
            sortingType = SortingType.getCurrentDefault();
        switch (sortingType) {
            case TIMSORT -> timsort(list, start, end, comparator);
            case QUICK_SORT -> quickSort(list, start, end, comparator);
            case HEAP_SORT -> heapSort(list, start, end, comparator);
            case MERGE_SORT -> mergeSort(list, start, end, comparator);
            case INSERTION_SORT -> insertionSort(list, start, end, comparator);
            case SELECTION_SORT -> selectionSort(list, start, end, comparator);
            case BUBBLE_SORT -> bubbleSort(list, start, end, comparator);
            case DEFAULT -> throw new RuntimeException();
        }
    }


    public static <T> void selectionSort(List<T> list, Comparator<? super T> comparator) {
        selectionSort(list, 0, list.size(), comparator);
    }

    public static <T> void selectionSort(List<T> list, int start, int end, Comparator<? super T> comparator) {
        for (int i = start; i < end; i++) {
            int minIndex = i;
            T min = list.get(i);
            for (int j = i + 1; j < end; j++) {
                T cur = list.get(j);
                if (comparator.compare(cur, min) < 0) {
                    minIndex = j;
                    min = cur;
                }
            }
            T tmp = list.get(i);
            list.set(i, list.get(minIndex));
            list.set(minIndex, tmp);
        }
    }


    public static <T> void bubbleSort(List<T> list, Comparator<? super T> comparator) {
        bubbleSort(list, 0, list.size(), comparator);
    }

    public static <T> void bubbleSort(List<T> list, int start, int end, Comparator<? super T> comparator) {
        for (int i = end - 1; i > start; i--) {
            for (int j = start; j < i; j++) {
                if (comparator.compare(list.get(j), list.get(j + 1)) > 0) {
                    T tmp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, tmp);
                }
            }
        }
    }


    public static <T> void insertionSort(List<T> list, Comparator<? super T> comparator) {
        insertionSort(list, 0, list.size(), comparator);
    }

    public static <T> void insertionSort(List<T> list, int start, int end, Comparator<? super T> comparator) {
        for (int i = start + 1; i < end; i++) {
            for (int j = i; j > 0; j--) {
                if (comparator.compare(list.get(j), list.get(j - 1)) < 0) {
                    T tmp = list.get(j);
                    list.set(j, list.get(j - 1));
                    list.set(j - 1, tmp);
                }
            }
        }
    }


    public static <T> void mergeSort(List<T> list, Comparator<? super T> comparator) {
        mergeSort(list, 0, list.size(), comparator);
    }

    @SuppressWarnings("unchecked")
    public static <T> void mergeSort(List<T> list, int start, int end, Comparator<? super T> comparator) {
        if (end - start <= 1)
            return;
        if (end - start == 2) {
            if (comparator.compare(list.get(start), list.get(start + 1)) > 0) {
                T tmp = list.get(start);
                list.set(start, list.get(start + 1));
                list.set(start + 1, tmp);
            }
        } else {
            int mid = start + ((end - start) >> 1);
            mergeSort(list, start, mid, comparator);
            mergeSort(list, mid, end, comparator);
            int i = 0, i1 = start, i2 = mid;
            Object[] buffer = new Object[end-start];
            while (i1 < mid && i2 < end)
                if (comparator.compare(list.get(i1), list.get(i2)) > 0)
                    buffer[i++] = list.get(i2++);
                else
                    buffer[i++] = list.get(i1++);
            while (i1 < mid)
                buffer[i++] = list.get(i1++);
            while (i2 < end)
                buffer[i++] = list.get(i2++);
            for (int j = start; j < end; ++j)
                list.set(j, (T) buffer[j-start]);
        }
    }


    public static <T> void quickSort(List<T> list, Comparator<? super T> comparator) {
        quickSort(list, 0, list.size(), comparator);
    }

    public static <T> void quickSort(List<T> list, int start, int end, Comparator<? super T> comparator) {

        if (end - start <= 1)
            return;

        int mid = start + ((end - start) >> 1);

        T pivot = list.get(mid);

        // swapping pivot with last
        list.set(mid, list.get(end - 1));
        list.set(end - 1, pivot);

        int lastMin = start - 1;
        for (int i = start; i < end - 1; i++) {
            if (comparator.compare(list.get(i), pivot) < 0) {
                ++lastMin;
                T tmp = list.get(lastMin);
                list.set(lastMin, list.get(i));
                list.set(i, tmp);
            }
        }
        ++lastMin;
        // swapping pivot with first greater
        list.set(end - 1, list.get(lastMin));
        list.set(lastMin, pivot);

        // recursion
        quickSort(list, start, lastMin, comparator);
        quickSort(list, lastMin + 1, end, comparator);
    }


    public static <T> void heapSort(List<T> list, Comparator<? super T> comparator) {
        heapSort(list, 0, list.size(), comparator);
    }

    public static <T> void heapSort(List<T> list, int start, int end, Comparator<? super T> comparator) {

        class HeapSortClass {

            int size = end - start;

            void correctDown(int i) {
                int ch1 = 2 * i + 1;
                if (ch1 >= size)
                    return;
                int ch2 = ch1 + 1;
                if (ch2 == size)
                    ch2 = ch1;
                if (comparator.compare(list.get(ch1), list.get(i)) > 0
                        || comparator.compare(list.get(ch2), list.get(i)) > 0) {
                    int maxChild;
                    if (comparator.compare(list.get(ch1), list.get(ch2)) > 0)
                        maxChild = ch1;
                    else
                        maxChild = ch2;
                    T tmp = list.get(i);
                    list.set(i, list.get(maxChild));
                    list.set(maxChild, tmp);
                    correctDown(maxChild);
                }
            }

            void correctUp(int i) {
                if (i <= 0)
                    return;
                int p = (i - 1) >> 1;
                if (comparator.compare(list.get(i), list.get(p)) > 0) {
                    T tmp = list.get(i);
                    list.set(i, list.get(p));
                    list.set(p, tmp);
                    correctUp(p);
                }
            }

            void sort() {
                for (int i = end - 1; i >= start; i--)
                    correctUp(i);

                while (size-- > 0) {
                    T tmp = list.get(start);
                    list.set(start, list.get(size));
                    list.set(size, tmp);
                    correctDown(0);
                }
            }
        }

        new HeapSortClass().sort();
    }


    /**
     * <a href="https://en.wikipedia.org/wiki/Timsort">Timsort</a> for a list.
     */
    public static <T> void timsort(List<T> list, Comparator<? super T> comparator) {
        timsort(list, 0, list.size(), comparator);
    }

    /**
     * <a href="https://en.wikipedia.org/wiki/Timsort">Timsort</a> for a list from start inclusive to end exclusive: [start, end).
     */
    @SuppressWarnings("unchecked")
    public static <T> void timsort(List<T> list, int start, int end, Comparator<? super T> comparator) {

        if (end - start <= 1) // (*)
            return;

        class TimsortClass {

            final int MAX_MINRUN = 64;

            final int GALLOP_COUNT = 7;

            int calculateMinrun(int n) {
                int r = 0;
                while (n >= MAX_MINRUN) {
                    r |= n & 1;
                    n >>= 1;
                }
                return n + r;
            }

            void merge(int start, int mid, int end) {

                Object[] tmpArr = new Object[mid - start];

                for (int i = 0; i < tmpArr.length; i++)
                    tmpArr[i] = list.get(start + i);

                int index = 0;

                int currentGallopCount = 0;

                while (start < mid && mid < end) {
                    if (comparator.compare((T) tmpArr[index], list.get(mid)) <= 0) {
                        list.set(start, (T) tmpArr[index]);
                        ++index;
                        ++currentGallopCount;
                    } else {
                        list.set(start, list.get(mid));
                        ++mid;
                        currentGallopCount = 0;
                    }
                    ++start;

                    if (currentGallopCount == GALLOP_COUNT) {
                        currentGallopCount = 0;

                        // binary search

                        int maxIndex;

                        int left = start, right = mid;

                        while (right - left > 1) {
                            int mid1 = left + ((right - left) >> 1);
                            int diff = comparator.compare(list.get(mid1), list.get(mid));
                            if (diff <= 0)
                                left = mid1;
                            else
                                right = mid1;
                        }

                        if (comparator.compare(list.get(left), list.get(mid)) > 0)
                            maxIndex = left;
                        else
                            maxIndex = right;

                        while (start < maxIndex)
                            list.set(start++, (T) tmpArr[index++]);
                    }
                }

                while (start < mid)
                    list.set(start++, (T) tmpArr[index++]);
            }
        }

        TimsortClass timsortClass = new TimsortClass();

        int size = end - start;

        int minrun = timsortClass.calculateMinrun(size);

        ArrayList<Pair<Integer,Integer>> indexes = new ArrayList<>();

        int curRunStart = start, curRunEnd = curRunStart + 1;

        while (true) {

            // next element (curRunStart + 1) exists (*)
            boolean increasing = comparator.compare(list.get(curRunStart), list.get(curRunStart + 1)) <= 0;

            Comparator<T> comp
                    = increasing
                    ? (Comparator<T>) comparator
                    : (o1, o2) -> {
                return /*minus here:*/-comparator.compare(o1, o2) + 1;
            };

            while (curRunEnd < end - 1 && comp.compare(list.get(curRunEnd), list.get(curRunEnd + 1)) <= 0)
                ++curRunEnd;

            if (curRunEnd < end - 1 && curRunEnd - curRunStart < minrun)
                curRunEnd = Math.min(curRunStart + minrun, end);

            // 'a0 > a1 > ... > an' TO 'an <= an-1 <= ...  <= a0'
            if (!increasing) {
                int mid = (curRunEnd - curRunStart) >> 1;
                for (int i = 0; i <= mid; i++) {
                    T tmp = list.get(curRunStart + i);
                    list.set(curRunStart + i, list.get(curRunEnd - i - 1));
                    list.set(curRunEnd - i - 1, tmp);
                }
            }

            // next element existing required at next iteration
            if (curRunEnd + 1 == end) // (*) (**)
                ++curRunEnd;

            insertionSort(list, curRunStart, curRunEnd, comparator);

            indexes.add(new Pair<>(curRunStart, curRunEnd - curRunStart));

            while (indexes.size() >= 3) {
                Pair<Integer,Integer> Z = indexes.getLast();
                Pair<Integer,Integer> Y = indexes.get(indexes.size() - 2);
                Pair<Integer,Integer> X = indexes.get(indexes.size() - 3);
                if (Z.second > Y.second + X.second || (Y.second > X.second && Z.second < X.second)) {
                    timsortClass.merge(X.first, Y.first, Y.first + Y.second);
                    indexes.set(indexes.size() - 3, new Pair<>(X.first, X.second + Y.second));
                    indexes.remove(indexes.size() - 2);
                } else if (Y.second > X.second) {
                    timsortClass.merge(Y.first, Z.first, Z.first + Z.second);
                    indexes.set(indexes.size() - 2, new Pair<>(Y.first, Y.second + Z.second));
                    indexes.removeLast();
                }
            }

            if (curRunEnd == end)
                break;

            curRunStart = curRunEnd;
            curRunEnd = curRunStart + 1; // < end because (**)
        }

        while (true) {
            if (indexes.size() < 2)
                break;
            if (indexes.size() == 2) {
                timsortClass.merge(start, indexes.getLast().first, end);
                break;
            }
            Pair<Integer,Integer> Z = indexes.getLast();
            Pair<Integer,Integer> Y = indexes.get(indexes.size() - 2);
            Pair<Integer,Integer> X = indexes.get(indexes.size() - 3);
            if (Z.second > X.second) {
                timsortClass.merge(X.first, Y.first, Y.first + Y.second);
                indexes.set(indexes.size() - 3, new Pair<>(X.first, X.second + Y.second));
                indexes.remove(indexes.size() - 2);
            } else {
                timsortClass.merge(Y.first, Z.first, Z.first + Z.second);
                indexes.set(indexes.size() - 2, new Pair<>(Y.first, Y.second + Z.second));
                indexes.removeLast();
            }
        }
    }
}