import java.io.IOException;

/**
 * The Replacement Selection Sort Method
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
public class ExternalSorter {
    private Buffer inBuffer;
    private Buffer outBuffer;
    private Heap minHeap;
    private BinaryParser par;
    
    /**
     * Constructor
     * 
     * @param filename
     * @throws IOException 
     */
    public ExternalSorter(String filename) throws IOException {
        par = new BinaryParser(filename);
        minHeap = new Heap(par.getBlock(), 16384); // pre-load heap
        inBuffer = new Buffer(par.getBlock()); // load buffer with 1 block
        
        this.sort(); // start sorting until no more blocks to load
    }
    
    /**
     * Replacement sort on input file using an input buffer,
     * output buffer and an 8 block heap.
     */
    public void sort() {
        
    }
}
