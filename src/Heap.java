/**
 * Heap class as implemented on Canvas, adjusted for minHeap and replacement
 * sorting.
 *
 * @author <Yannik Sood> <yannik24>, <Daniel Almeida> <adaniel1>
 * @version 04.14.19
 *
 */
public class Heap {
    private Record[] minHeap;
    private int maxSize; // max size of heap
    private int nHeap; // no. elements in heap

    /**
     * Creates an empty Heap.
     */
    public Heap() {
        this.maxSize = 512 * 8;
        this.minHeap = new Record[maxSize];
        this.nHeap = 0;
    }

    /**
     * Creates a pre-loaded Heap.
     *
     * @param byteArray     Array containing bytes from up to n bytes
     * @param n             Number of elements in the byte array
     */
    public Heap(byte[] byteArray, int n) {
        this.maxSize = 512 * 8;
        this.minHeap = new Record[maxSize];
        this.nHeap = 0;

        // create records
        byte[] key;
        byte[] value;
        int byteCount = 0;

        while (byteCount < n) {
            // if you don't do this, it will reference to the same array
            // in each record
            key = new byte[8];
            value = new byte[8];

            // grab key
            for (int i = 0; i < 8; i++) {
                key[i] = byteArray[byteCount];
                byteCount++;
            }

            // grab value
            for (int i = 0; i < 8; i++) {
                value[i] = byteArray[byteCount];
                byteCount++;
            }

            // assign record
            minHeap[nHeap++] = new Record(key, value);
        }

        // heapify
        this.makeMinHeap();
    }

    /**
     * Return heap size.
     *
     * @return heap size
     */
    public int getSize() {
        return this.nHeap;
    }

    /**
     * Special case setSize() for replacement sorting of dead nodes.
     * WARNING: USE WITH CAUTION.
     * 
     * @param i     new size of Heap
     */
    public void setSize(int i) {
        this.nHeap = i;
    }

    /**
     * Return true if pos a leaf position, false otherwise
     *
     * @param pos       position in Heap
     * @return          true if pos is a leaf position
     */
    public boolean isLeaf(int pos) {
        return (pos >= nHeap / 2) && (pos < nHeap);
    }

    /**
     * Return position for left child of pos
     *
     * @param pos       position in heap
     * @return          index for left child of pos
     */
    public int leftChild(int pos) {
        if (pos < 0) {
            return -2;
        }
        else if (pos >= nHeap / 2) {
            return -1;
        }

        return 2 * pos + 1;
    }

    /**
     * Return position for right child of pos
     * 
     * @param pos       position in heap
     * @return          index for right child of pos
     */
    public int rightChild(int pos) {
        if (pos < 0) {
            return -2;
        }
        else if (pos >= (nHeap - 1) / 2) {
            return -1;
        }

        return 2 * pos + 2;
    }

    /**
     * Return position for parent
     *
     * @param pos       position in heap
     * @return          index for parent of pos
     */
    public int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }

        return (pos - 1) / 2;
    }

    /**
     * Insert a record to the Heap
     * 
     * @param record    record to be inserted
     */
    public void insert(Record record) {
        if (nHeap >= maxSize) {
            return;
        }

        int cur = nHeap++; // this assigns current nHeap then increments
        minHeap[cur] = record; // start at end of heap

        // sifting until curr's parent's key < curr's key
        while ((cur != 0) && minHeap[cur].compareTo(minHeap[parent(cur)]) < 0) {
            swapNodes(cur, parent(cur));
            cur = parent(cur);
        }
    }

    /**
     * Remove and return record at pos.
     * 
     * @param pos   position in heap
     * @return      record at position pos
     */
    public Record remove(int pos) {
        if ((pos < 0) || (pos >= nHeap)) {
            return null;
        }

        if (pos == (nHeap - 1)) {
            nHeap--; // just decrease the size
            return minHeap[nHeap]; // return removed element
        }
        else {
            swapNodes(pos, --nHeap); // swap with last record
            update(pos);
            return minHeap[nHeap]; // return removed element
        }
    }

    /**
     * Return the root value of the heap.
     * 
     * @return      record at root of heap.
     */
    public Record getRoot() {
        return minHeap[0]; // return root element
    }

    /**
     * Value of pos has been changed, restore heap property.
     * 
     * @param pos   position to be updated
     */
    private void update(int pos) {
        // if it's smaller than parent, push up
        while ((pos > 0) && minHeap[pos].compareTo(minHeap[parent(pos)]) < 0) {
            swapNodes(pos, parent(pos));
            pos = parent(pos);
        }

        // if it's larger then sift down if needed
        if (nHeap != 0) {
            siftDown(pos);
        }
    }

    /**
     * Modify value at given position.
     * 
     * @param pos       position in heap to be modified
     * @param newVale   record to replace position's record
     */
    public void modify(int pos, Record newVale) {
        if ((pos < 0) || (pos >= nHeap)) {
            return;
        }

        minHeap[pos] = newVale;
        update(pos);
    }

    /**
     * Special case modify for replacement sorting, does not call update.
     * 
     * @param pos       position in heap
     * @param newVale   record to replace position's record
     */
    public void rSortMod(int pos, Record newVale) {
        minHeap[pos] = newVale;
    }

    /**
     * Remove min value from heap.
     *
     * @return      minimum Record
     */
    public Record removeMin() {
        if (nHeap == 0) {
            return null;
        }

        swapNodes(0, --nHeap); // also decrements heap size

        if (nHeap != 0) { // not last element
            siftDown(0);
        }

        return minHeap[nHeap];
    }

    /**
     * Heapify contents of the Heap. Will do nothing on empty Heap.
     */
    public void makeMinHeap() {
        for (int i = (nHeap / 2) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * Put element in its correct place (heapify).
     *
     * @param pos   node to be positioned
     */
    private void siftDown(int pos) {
        if ((pos < 0) || (pos >= nHeap)) {
            return;
        }

        while (!isLeaf(pos)) {
            int j = leftChild(pos);

            // checking which node to pick
            if ((j < (nHeap - 1))
                && (minHeap[j].compareTo(minHeap[j + 1]) > 0)) {
                j++; // index of child with lower value
            }

            // does this if second child does not exist or is greater/equal
            if (minHeap[j].compareTo(minHeap[pos]) >= 0) {
                return; // the child j is greater than or equal to pos
            }

            // swap depending on above outcomes
            swapNodes(pos, j);
            pos = j;
        }
    }

    /**
     * Swap two nodes
     * 
     * @param first     first node
     * @param second    second node
     */
    private void swapNodes(int first, int second) {
        Record temp = minHeap[first];
        minHeap[first] = minHeap[second];
        minHeap[second] = temp;
    }

}
