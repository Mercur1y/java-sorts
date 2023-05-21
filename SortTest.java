import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

public class SortTest {
    public static void main(String[] args) {
        int size = 1000000;

        Random random = new Random();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(size);
        }

        long startTime;
        long endTime;

//        startTime = System.currentTimeMillis();
//        shakerSort(arr);
//        endTime = System.currentTimeMillis();
//        System.out.println("Shaker sort time: " + (endTime - startTime) + "ms");
//
//        startTime = System.currentTimeMillis();
//        combSort(arr);
//        endTime = System.currentTimeMillis();
//        System.out.println("Comb sort time: " + (endTime - startTime) + "ms");
//
//        startTime = System.currentTimeMillis();
//        insertionSort(arr);
//        endTime = System.currentTimeMillis();
//        System.out.println("Insertion sort time: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        shellsort(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Shellsort time: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        sort(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Tree sort time: " + (endTime - startTime) + "ms");

//        startTime = System.currentTimeMillis();
//        gnomeSort(arr);
//        endTime = System.currentTimeMillis();
//        System.out.println("Gnome sort time: " + (endTime - startTime) + "ms");
//
//        startTime = System.currentTimeMillis();
//        selectionSort(arr);
//        endTime = System.currentTimeMillis();
//        System.out.println("Selection sort time: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        heapSort(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Heapsort time: " + (endTime - startTime) + " ns");

        startTime = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        endTime = System.currentTimeMillis();
        System.out.println("Quicksort time: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        mergeSort(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Merge sort time: " + (endTime - startTime) + "ms");

        startTime = System.currentTimeMillis();
        countingSort(arr);
        endTime = System.currentTimeMillis();
        System.out.println("Counting sort time: " + (endTime - startTime) + "ms");


        startTime = System.currentTimeMillis();
        groupMergeSortOptimized(arr, 50);
        endTime = System.currentTimeMillis();
        System.out.println("My sort: " + (endTime - startTime) + " ms");

    }

    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void shakerSort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            // Move largest element to the right
            for (int i = left; i < right; i++) {
                if (arr[i] > arr[i + 1]) {
                    swap(arr, i, i + 1);
                }
            }
            right--;

            // Move smallest element to the left
            for (int i = right; i > left; i--) {
                if (arr[i] < arr[i - 1]) {
                    swap(arr, i, i - 1);
                }
            }
            left++;
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void combSort(int[] arr) {
        int n = arr.length;
        int gap = n;
        boolean swapped = true;
        while (gap != 1 || swapped) {
            gap = getNextGap(gap);
            swapped = false;
            for (int i = 0; i < n - gap; i++) {
                if (arr[i] > arr[i + gap]) {
                    swap(arr, i, i + gap);
                    swapped = true;
                }
            }
        }
    }

    private static int getNextGap(int gap) {
        gap = (gap * 10) / 13;
        if (gap < 1) {
            return 1;
        }
        return gap;
    }

    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

    public static void sort(int[] arr) {
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < arr.length; i++) {
            treeSet.add(arr[i]);
        }
        int i = 0;
        for (Integer elem : treeSet) {
            arr[i++] = elem;
        }
    }

    public static void shellsort(int[] arr) {
        int n = arr.length;
        int gap = n / 2;
        while (gap > 0) {
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
            gap /= 2;
        }
    }

    public static void mergeSort(int[] arr) {
        int[] buffer = new int[arr.length];
        mergeSort(arr, buffer, 0, arr.length - 1);
    }

    private static void mergeSort(int[] arr, int[] buffer, int left, int right) {
        if (left < right) {
            if (right - left < 16) {
                insertionSort(arr, left, right);
            } else {
                int mid = (left + right) / 2;
                mergeSort(arr, buffer, left, mid);
                mergeSort(arr, buffer, mid + 1, right);
                merge(arr, buffer, left, mid, right);
            }
        }
    }

    public static void gnomeSort(int[] arr) {
        int index = 0;
        while (index < arr.length) {
            if (index == 0) {
                index++;
            }
            if (arr[index] >= arr[index - 1]) {
                index++;
            } else {
                int temp = arr[index];
                arr[index] = arr[index - 1];
                arr[index - 1] = temp;
                index--;
            }
        }
    }

    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }

    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // To heapify a subtree rooted with node i which is
// an index in arr[]. n is size of heap
    static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    public static void countingSort(int[] arr) {
        int max = Arrays.stream(arr).max().orElse(0);
        int[] counts = new int[max + 1];

        for (int num : arr) {
            counts[num]++;
        }

        int index = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                arr[index++] = i;
                counts[i]--;
            }
        }
    }

    private static void merge(int[] arr, int[] buffer, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                buffer[k++] = arr[i++];
            } else {
                buffer[k++] = arr[j++];
            }
        }

        while (i <= mid) {
            buffer[k++] = arr[i++];
        }

        while (j <= right) {
            buffer[k++] = arr[j++];
        }

        System.arraycopy(buffer, left, arr, left, right - left + 1);
    }

    private static void insertionSort(int[] arr, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int current = arr[i];
            int j = i - 1;

            while (j >= left && arr[j] > current) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = current;
        }
    }

    public static void groupMergeSortOptimized(int[] arr, int groupSize) {
        if (groupSize < 2) {
            throw new IllegalArgumentException("Group size must be at least 2");
        }
        int n = arr.length;
        int[] tmp = new int[n];
        for (int i = 0; i < n; i += groupSize) {
            int start = i;
            int end = Math.min(i + groupSize, n);
            if (end - start <= 10) {
                insertionSort(arr, start, end);
            } else {
                mergeSort(arr, tmp, start, end - 1);
            }
        }
        int[] res = tmp;
        for (int group = groupSize; group < n; group *= 2) {
            int[] tmp2 = arr;
            for (int i = 0; i < n; i += 2 * group) {
                int start = i;
                int mid = Math.min(i + group, n);
                int end = Math.min(i + 2 * group, n);
                merge(tmp, tmp2, start, mid - 1, end - 1);
            }
            int[] tmp3 = tmp;
            tmp = tmp2;
            tmp2 = tmp3;
        }
        if (arr != tmp) {
            System.arraycopy(tmp, 0, arr, 0, n);
        }
    }

    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;//завершить выполнение, если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }
}
