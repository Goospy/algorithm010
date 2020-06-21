public class Heap {
    private int[] a; // 数组，从下标 1 开始存储数据
    private int n;  // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    /**
     * 堆排序：先构建堆，然后逐次将堆顶元素和堆底元素交换，重新构建1至n-i范围内的堆，
     * @param a
     * @param n
     */
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