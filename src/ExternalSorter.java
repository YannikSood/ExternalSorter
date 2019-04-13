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
    private Record lastRemoved;

    /**
     * Constructor
     * 
     * @param filename
     * @throws IOException
     */
    public ExternalSorter(String filename) throws IOException {
        // initialize parser, Heap and lastInsert
        this.lastRemoved = null;
        par = new BinaryParser(filename);
        minHeap = new Heap(); // empty 8 block Heap
        
        // pre-load heap with 8 blocks (if possible)
        while ((minHeap.getSize() < 512 * 8) && !par.getEOF()) {
            byte[] byteArray = par.getBlock(); // grab a block
            System.out.println(par.getCurrBytes());
            System.out.println(par.getTotalBytes());

            // create records
            if (byteArray != null) {
                byte[] key;
                byte[] value;
                int byteCount = 0;

                while (byteCount < par.getCurrBytes()) {
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
                    minHeap.insert(new Record(key, value));
                }
            }

        }

        // if more to load, pre-load the buffer with up to 1 block (if possible)
        if (!par.getEOF()) {
            byte[] temp = par.getBlock(); // grab up to 1 block
            inBuffer = new Buffer(temp, par.getCurrBytes());

            System.out.println("buffer pre-loaded as well");
        }

        // replacement sort the input file into run file
        this.rSort();

        // merge sort run file

    }

    /**
     * Replacement sort on input file using an input buffer,
     * output buffer and an 8 block heap.
     */
    public void rSort() {
        System.out.println("---------sorting------");
        System.out.println("-----------------------");

        System.out.println(par.getEOF());
        System.out.println(minHeap.getSize());

        // while heap is not empty, keep sorting
        while (minHeap.getSize() > 0) {
            // just remove on first call
            if (this.lastRemoved == null) {
                // "remove" except it's still in the array
                Record temp = minHeap.getRoot();
                this.lastRemoved = temp;
                
                // send the bytes to outBuffer
                
                
            }
            else { // not the first removal
                
            }
        }

    }

}
