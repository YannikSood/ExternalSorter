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

    private static final int FRONT = 1;


    /**
     * Heap Constructor
     * 
     * @param maxsize
     */
    public Heap(int maxSize) {
        this.max = maxSize;
        this.size = 0;
        minHeap = new Record[this.max + 1];
        minHeap[0] = null;
    }


    /**
     * Insert element in heap
     * 
     * @param element
     *            to insert
     */
    public void insert(Record record) {
        minHeap[++size] = record;
        int current = size;

        while (minHeap[current].getValue() < minHeap[getParent(current)]
            .getValue()) {
            swapNodes(current, getParent(current));
            current = getParent(current);
        }
    }


    /**
     * Remove min value
     * 
     * @return minimum value
     */
    public Record remove() {
        Record popped = minHeap[FRONT]; 
        minHeap[FRONT] = minHeap[size--];
        minHeapHelper(FRONT);
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


    // Function to heapify the node at pos
    private void minHeapHelper(int pos) {

        // If the node is a non-leaf node and greater
        // than any of its child
        if (!(pos >= (size / 2) && pos <= size)) {
            if (minHeap[pos].getValue() > minHeap[getLeftChild(pos)].getValue()
                || minHeap[pos].getValue() > minHeap[getRightChild(pos)]
                    .getValue()) {

                // Swap with the left child and heapify
                // the left child
                if (minHeap[getLeftChild(pos)]
                    .getValue() < minHeap[getRightChild(pos)].getValue()) {
                    swapNodes(pos, getLeftChild(pos));
                    minHeapHelper(getLeftChild(pos));
                }

                // Swap with the right child and heapify
                // the right child
                else {
                    swapNodes(pos, getRightChild(pos));
                    minHeapHelper(getRightChild(pos));
                }
            }
        }
    }


    // Function to swap two nodes of the heap
    private void swapNodes(int first, int second) {
        Record temp = minHeap[first];
        minHeap[first] = minHeap[second];
        minHeap[second] = temp;
    }


    // Function to return the position of
    // the parent for the node currently
    // at pos
    private int getParent(int pos) {
        return pos / 2;
    }


    // Function to return the position of the
    // left child for the node currently at pos
    private int getLeftChild(int pos) {
        return (2 * pos);
    }


    // Function to return the position of
    // the right child for the node currently
    // at pos
    private int getRightChild(int pos) {
        return (2 * pos) + 1;
    }

}
