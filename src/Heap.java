/**
 * 
 *
 * @author <Yannik Sood> <yannik24>
 * @version 04.10.19
 *
 */
public class Heap {
    private Record[] minHeap;
    private int max; // max size of heap
    private int size; // no. elements in heap


    /**
     * Heap constructor
     * 
     * @param rec       Record array
     * @param maxSize   Size of record array
     */
    public Heap(Record[] rec, int maxSize, int n) {
        this.max = maxSize;
        this.size = n;
        this.minHeap = rec; // minHeap is pointing to the buffer array
        this.makeMinHeap();
    }


    /**
     * get the size
     * 
     * @return size
     */
    public int getSize() {
        return size;
    }


    /**
     * Insert element in heap
     * 
     * @param element
     *            to insert
     */
    public void insert(Record record) {
        if (size >= max) {
            System.out.println("Heap Full");
            return;
        }
        minHeap[++size] = record;
        int current = size;

        while (minHeap[current].getValue() < minHeap[getParentPos(current)]
            .getValue()) {
            swapNodes(current, getParentPos(current));
            current = getParentPos(current);
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
