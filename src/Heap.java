
public class Heap {
    private Record[] heap; // Pointer to the heap array
    private int size; // Maximum size of the heap
    private int n; // Number of things now in heap


    /**
     * Constructor
     * 
     * @param h
     *            Heap
     * @param count
     *            of values
     * @param max
     *            size
     */
    public Heap(Record[] h, int count, int max) {
        heap = h;
        n = count;
        size = max;
        buildHeap();
    }


    // Return current size of the heap
    public int currSize() {
        return n;
    }


    // Return true if pos a leaf position, false otherwise
    public boolean isLeaf(int pos) {
        return (pos >= n / 2) && (pos < n);
    }


    // Return position for left child of pos
    public int leftchild(int pos) {
        if (pos >= n / 2) {
            return -1;
        }

        return 2 * pos + 1;
    }


    // Return position for right child of pos
    public int rightchild(int pos) {
        if (pos >= (n - 1) / 2) {
            return -1;
        }

        return 2 * pos + 2;
    }


    // Return position for parent
    public int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }

        return (pos - 1) / 2;
    }


    // Insert val into heap
    public void insert(Record key) {
        if (n >= size) {
            System.out.println("Heap is full");
            return;
        }
        int curr = n++;
        heap[curr] = key; // Start at end of heap
        // Now sift up until curr's parent's key > curr's key
        while ((curr != 0) && (heap[curr].compareTo(heap[parent(curr)]) > 0)) {
            swap(heap, curr, parent(curr));
            curr = parent(curr);
        }
    }


    private void swap(Record[] heap2, int curr, int parent) {
        // TODO Auto-generated method stub

    }


    // Heapify contents of Heap
    public void buildHeap() {
        for (int i = n / 2 - 1; i >= 0; i--) {
            siftdown(i);
        }
    }


    // Put element in its correct place
    public void siftdown(int pos) {
        if ((pos < 0) || (pos >= n)) {
            return; // Illegal position
        }

        while (!isLeaf(pos)) {
            int j = leftchild(pos);
            if ((j < (n - 1)) && (heap[j].compareTo(heap[j + 1]) < 0)) {
                j++; // j is now index of child with greater value
            }
            if (heap[pos].compareTo(heap[j]) >= 0) {
                return;
            }
            swap(heap, pos, j);
            pos = j; // Move down
        }
    }


    // Remove and return maximum value
    public  Record removemax() {
        if (n == 0) {
            return null; // Removing from empty heap
        }
        swap(heap, 0, --n);// Swap maximum with last value
        if (n != 0) { // Not on last element
            siftdown(0); // Put new heap root val in correct place
        }
        return heap[n];
    }


    // Remove and return element at specified position
    public int remove(int pos) {
        if ((pos < 0) || (pos >= n))
            return -1; // Illegal heap position
        if (pos == (n - 1))
            n--; // Last element, no work to be done
        else {
            swap(heap, pos, --n); // Swap with last value
            update(pos);
        }
        return 1;
    }


    // Modify the value at the given position
    public void modify(int pos, Comparable newVal) {
        if ((pos < 0) || (pos >= n))
            return; // Illegal heap position
        heap[pos] = null;
        update(pos);
    }


    // The value at pos has been changed, restore the heap property
    public void update(int pos) {
        // If it is a big value, push it up
        while ((pos > 0) && (heap[pos].compareTo(heap[parent(pos)]) > 0)) {
            swap(heap, pos, parent(pos));
            pos = parent(pos);
        }
        if (n != 0)
            siftdown(pos); // If it is little, push down
    }
}
