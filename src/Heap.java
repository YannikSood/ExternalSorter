/**
 * Heap class as implemented on Canvas, adjusted for minHeap.
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

    /**
     *
     *
     * @param byteArray     Array containing bytes from 1 block
     * @param n             Number of elements in the byte array
     */
    public Heap(Byte[] byteArray, int n) {
        this.maxSize = 512 * 8;
        this.minHeap = new Record[maxSize];
        this.nHeap = 0;
        
        this.currBlock = byteArray; // currBlock points to byteArray passed in

        // populate the heap with records


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
     * Return true if pos a leaf position, false otherwise
     *
     * @param pos
     * @return
     */
    boolean isLeaf(int pos) {
        return (pos >= nHeap / 2) && (pos < nHeap);
    }

    /**
     * Return position for left child of pos
     *
     * @param pos
     * @return
     */
    int leftChild(int pos) {
        if (pos >= nHeap / 2) {
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
    int rightChild(int pos) {
        if (pos >= (nHeap - 1) / 2) {
            return -1;
        }

        return 2*pos + 2;
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

        return (pos - 1) / 2;
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

        // sifting until curr's parent's key < curr's key
        while ((curr != 0) && minHeap[curr].compareTo(minHeap[parent(curr)]) < 0) {
            swapNodes(curr, parent(curr));
            curr = parent(curr);
        }
    }
    
    /**
     * Remove and return record at pos.
     * 
     * @param pos
     * @return
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
     * Value of pos has been changed, restore heap property.
     */
    void update(int pos) {
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
     * @param pos
     * @param newVale
     */
    public void modift(int pos, Record newVale) {
        if ((pos < 0) || (pos >= nHeap)) {
            return;
        }
        
        minHeap[pos] = newVale;
        update(pos);
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
     * Make the heap a min heap
     */
    public void makeMinHeap() {
        for (int i = (nHeap / 2) - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * Put element in its correct place (heapify). Equal value goes left.
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
            if ((j < (nHeap-1)) && (minHeap[j].compareTo(minHeap[j+1]) > 0)) {
                j++; // index of child with lower value
            }
            
            // does this if second child does not exist or is smaller/equal
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
