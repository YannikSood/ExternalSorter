/**
 * A Min Heap that stores records of key/value pairs
 * 
 *
 * 
 * * // On my honor: // // - I have not used source code obtained from
 * another student, // or any other unauthorized source, either modified
 * or // unmodified. // // - All source code and documentation used in
 * my program is // either my original work, or was derived by me from
 * the // source code published in the textbook for this course. // // -
 * I have not discussed coding details about this project with // anyone
 * other than my partner (in the case of a joint // submission),
 * instructor, ACM/UPE tutors or the TAs assigned // to this course. I
 * understand that I may discuss the concepts // of this program with
 * other students, and that another student // may help me debug my
 * program so long as neither of us writes // anything during the
 * discussion or modifies any computer file // during the discussion. I
 * have violated neither the spirit nor // letter of this restriction.
 *
 * @author <Yannik Sood> <yannik24>
 * @version 04.10.19
 *
 */
public class Heap {
    private Record[] minHeap;
    private int size;
    private int max;


    /**
     * Heap Constructor
     * 
     * @param maxsize
     */
    public Heap(Record[] rec, int maxSize) {
        this.max = maxSize;
        this.size = rec.length;
        minHeap = rec;
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
