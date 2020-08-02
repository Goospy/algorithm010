学习笔记

###位运算
x ^ 0 = x

x ^ 1s = ~x

x ^ (~x) = 1s

x ^ x = 0

c = a ^ b => a ^ c = b, b ^ c = a

a ^ b ^ c = a ^ (b ^ c) = (a ^ b) ^ c

将x最右边的n位清零：x & (1 << n);

获取x的第n位的值(0或者1)：(x >> n) & 1

获取x的第n位的幂值： x & (1 << n)

仅将第n位置为1： x | (1 << n)

仅将第n位置为0： x & (~(1 << n))

将x最高位至第n位(含)清零：x & ((1 << n) - 1)

清空最低位的1：x & (x - 1)

###布隆过滤器
用二进制数组来存储数据的存在关系，性能卓越，但对于数据的存在性有偏差，只能准确判断数据不存在。

###排序

冒泡排序
```java
    public void sort(int[] array) {
        for(int i = 0; i < array.length; i++) {
            for(int j = 0; j < array.length - i - 1; j++) {
                if(array[j + 1] > array[j]) {
                    exch(array, j, j + 1);
                }
            }
        }
    }

    public void exch(int[] array, int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }
```

选择排序
```java
    public void sort(int[] array) {
        int length = array.length;
        for(int i = 0; i < length; i++) {
            int min = i;
            for(int j = i + 1; j < length; j ++) {
                if(array[min] > array[j]) {
                    min = j;
                }
            }
            if(min != i) {
                exch(array, min, i);
            }
        }
    }

    public void exch(int[] array, int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }
```

插入排序
```java
    public void sort(int[] array) {
        int length = array.length;
        for(int i = 1; i < length; i++) {
            for(int j = i; j > 0 && array[j] < array[j - 1]; j --) {
                exch(array, j, j - 1);
            }
        }
    }

    public void exch(int[] array, int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }
```

快速排序
```java
public static void quickSort(int[] array, int begin, int end) {
    if (end <= begin) return;
    int pivot = partition(array, begin, end);
    quickSort(array, begin, pivot - 1);
    quickSort(array, pivot + 1, end);
}
static int partition(int[] a, int begin, int end) {
    // pivot: 标杆位置，counter: 小于pivot的元素的个数
    int pivot = end, counter = begin;
    for (int i = begin; i < end; i++) {
        if (a[i] < a[pivot]) {
            exch(array, pivot, i);
            counter++;
        }
    }
    exch(array, pivot, counter);
    return counter;
}

    public void exch(int[] array, int a, int b) {
        int temp = array[b];
        array[b] = array[a];
        array[a] = temp;
    }
```

归并排序
```java
public static void mergeSort(int[] array, int left, int right) {
    if (right <= left) return;
    int mid = (left + right) >> 1; // (left + right) / 2

    mergeSort(array, left, mid);
    mergeSort(array, mid + 1, right);
    merge(array, left, mid, right);
}

public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 中间数组
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }

        while (i <= mid)   temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
        // 也可以用 System.arraycopy(a, start1, b, start2, length)
    }
```
堆排序
```java
public class Heap {
    private int[] a; // 数组，从下标 1 开始存储数据
    private int n;  // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    public void heapSort(int[] a, int n) {
        buildHeap(a, n);
        for(int i = n; i > 1; i--) {
            swap(a, 1, i);
            heapify(a, i, 1);
        }
    }

    public void insert(int data) {
        if(count > n) return;
        a[++count] = data;
        int i = count;
        while(i > 0 && a[i] > a[i/2]) {
            swap(a, i, i / 2);
            i = i/2;
        }
    }

    public void removeMax() {
        if(count == 0) return;
        a[1] = a[count--];
        heapify(a, count, 1);
    }

    private void heapify(int[] a, int n, int i) { // 自上往下堆化
        while(true) {
            int maxP = i;
            if(i*2 <= n && a[i] < a[i*2]) maxP = i*2;
            if(i*2 + 1 <= n && a[maxP] < a[i*2 + 1]) maxP = i*2 + 1;
            if(i == maxP) break;
            swap(a, i, maxP);
            i = maxP;
        }
    }

    private void buildHeap(int[] a, int n) {
        for(int i = n/2; i >= 1; i--) {
            heapify(a, n, i);
        }
    }

    private void swap(int[] a, int b, int c) {
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }
}
```
