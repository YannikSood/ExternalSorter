/**
 *
 *
 * @author <Yannik Sood> <yannik24>
 * @version 04.10.19
 *
 */
public class Heap {
    private Record[] minHeap;
    private Byte[] currBlock;
    private int maxSize; // max size of heap
    private int nHeap; // no. elements in heap

    private BinaryParser parser; // grabbing data for buffer
    // private Buffer inputBuffer = new Buffer();
    // private Buffer outputBuffer = new Buffer();


    /**
     *
     *
     * @param byteArray     Array containing bytes from 1 block
     * @param n             Number of elements in the byte array
     */
    public Heap(Byte[] byteArray, int n) {
        this.maxSize = 512 * 8;
        this.nHeap = 0;

        this.minHeap = new Record[maxSize];
        this.currBlock = byteArray;

        // populate the heap with records


        // heapify
        this.makeMinHeap();
    }

    /**
     * Return heap size.
     *
     * @return size
     */
    public int getSize() {
        return this.nHeap;
    }

    /**
     * Return true if pos a leaf position, false otherwise
     *
     * @param pos
     * @return
     */
    boolean isLeaf(int pos) {
        return (pos >= nHeap/2) && (pos < nHeap);
    }

    /**
     * Return position for left child of pos
     *
     * @param pos
     * @return
     */
    int leftChild(int pos) {
        if (pos >= nHeap/2) {
            return -1;
        }

      return 2*pos + 1;
    }

    /**
     * Return position for right child of pos
     *
     * @param pos
     * @return
     */
    public int getSize() {
        return nHeap;
    }

    /**
     * Return position for parent
     *
     * @param pos
     * @return
     */
    int parent(int pos) {
        if (pos <= 0) {
            return -1;
        }


    /**
     * Insert element in heap
     *
     * @param element
     *            to insert
     */
    public void insert(Record record) {
        if (nHeap >= maxSize) {
            System.out.println("Heap Full");
            return;
        }

        int curr = nHeap++; // this assigns current nHeap then increments
        minHeap[curr] = record; // start at end of heap

        // sifting down
        while ((curr != 0) && minHeap[curr].compareTo(minHeap[parent(curr)]) < 0) {
            swapNodes(curr, parent(curr));
            curr = parent(curr);
        }
    }


    /**
     * Remove min value
     *
     * @return minimum value
     */
    public Record removeMin() {
        Record popped = minHeap[1];
        minHeap[1] = minHeap[size--];
        minHeapHelper(1);
        return popped;
    }


    /**
     * Make the heap a min heap
     */
    public void makeMinHeap() {
        for (int i = (size / 2); i >= 1; i--) {
            minHeapHelper(i);
        }
    }


    /**
     * Helper to make the min heap
     *
     * @param pos
     *            node to heapify
     */
    private void minHeapHelper(int pos) {

        if (!(pos >= (size / 2) && pos <= size)) {
            if (minHeap[pos].getValue() > minHeap[getLeftChildPos(pos)]
                .getValue() || minHeap[pos]
                    .getValue() > minHeap[getRightChildPos(pos)].getValue()) {
                if (minHeap[getLeftChildPos(pos)]
                    .getValue() < minHeap[getRightChildPos(pos)].getValue()) {
                    swapNodes(pos, getLeftChildPos(pos));
                    minHeapHelper(getLeftChildPos(pos));
                }

                else {
                    swapNodes(pos, getRightChildPos(pos));
                    minHeapHelper(getRightChildPos(pos));
                }
            }
        }
    }


    /**
     * Swap two nodes
     *
     * @param first
     *            node
     * @param second
     *            node
     */
    private void swapNodes(int first, int second) {
        Record temp = minHeap[first];
        minHeap[first] = minHeap[second];
        minHeap[second] = temp;
    }


    /**
     * Get the parent pos
     *
     * @param pos
     *            node pos
     * @return parent pos
     */
    private int getParentPos(int pos) {
        return pos / 2;
    }


    /**
     * Get left child pos
     *
     * @param pos
     *            curr pos
     * @return left child pos
     */
    private int getLeftChildPos(int pos) {
        return pos * 2;
    }


    /**
     * Get right child pos
     *
     * @param pos
     *            curr node
     * @return right child pos
     */
    private int getRightChildPos(int pos) {
        return (pos * 2) + 1;
    }

}
