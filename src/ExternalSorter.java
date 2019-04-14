import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * The ExternalSorter class is the brains of the operation.
 * It will conduct an replacement sort, following by a merge sort on
 * an input file of 8N blocks.
 *
 * @author <Yannik Sood> <yannik24>, <Daniel Almeida> <adaniel1>
 * @version 04.14.19
 */
public class ExternalSorter {
    private Buffer inBuffer;
    private Buffer outBuffer;
    private Heap minHeap;
    private BinaryParser par;
    private Record lastRemoved;

    /**
     * Constructor initiates buffers, heap, parser and variables.
     * Also responsible for making calls to start replacement sort
     * and merge sort on files.
     * 
     * @param filename      input file
     * @throws IOException  if file name is incorrect or EOF
     */
    public ExternalSorter(String filename) throws IOException {
        // initialize parser, Heap and lastInsert
        this.lastRemoved = null;
        par = new BinaryParser(filename);
        minHeap = new Heap(); // empty 8 block Heap
        
        // pre-load heap with 8 blocks (if possible)
        while ((minHeap.getSize() < 512 * 8) && !par.getEOF()) {
            byte[] byteArray = par.getBlock(); // grab a block

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
        }
        else {
            inBuffer = new Buffer();
        }
        
        // initialize output buffer
        outBuffer = new Buffer();

        // replacement sort the input file into run file
        this.rSort();

        // merge sort run file
        // this.mSort()
    }

    /**
     * Replacement sort on input file using an input buffer,
     * output buffer and an 8 block heap.
     * 
     * @throws IOException if file name incorrect or EOF.
     */
    public void rSort() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("tenBlocks.bin", "rw");
        int nDead = 0;
        boolean rFlag = false; // helper (prevent double reset of nodes)

        // while heap is not empty, keep sorting
        while (minHeap.getSize() > 0) {
            // if this removal is cleaning the dead nodes
            if (inBuffer.getSize() == 0 && par.getEOF()) {
                // reset dead nodes
                if (!rFlag) {
                    minHeap.setSize(nDead + minHeap.getSize());
                    nDead = 0;
                }
                
                // remove them all using removeMin()
                while (minHeap.getSize() > 0) {
                    // send the "removed" bytes to outBuffer
                    if (outBuffer.getSize() < 8192 ) { // not full
                        this.lastRemoved = minHeap.getRoot();
                        outBuffer.insert(minHeap.removeMin().getRecord());
                    }
                    else { // full
                        // write to file to clear outBuffer
                        raf.write(outBuffer.clear());
                        
                        this.lastRemoved = minHeap.getRoot();
                        outBuffer.insert(minHeap.removeMin().getRecord());
                    }
                }
                
                // flush out leftover from outBuffer
                raf.write(outBuffer.clear());
            }
            else {
                // "remove" except it's still in the array
                Record removedRec = minHeap.getRoot();
                this.lastRemoved = removedRec;
                   
                // send the "removed" bytes to outBuffer
                if (outBuffer.getSize() < 8192 ) { // not full
                    outBuffer.insert(removedRec.getRecord());
                }
                else { // full
                    // write to file to clear outBuffer
                    raf.write(outBuffer.clear());
                    
                    outBuffer.insert(removedRec.getRecord());
                }

                if (inBuffer.getSize() > 0) { // not empty
                    byte[] rec = inBuffer.remove(); // grab the 16 bytes

                    // create the record
                    byte[] key = new byte[8];
                    byte[] value = new byte[8];

                    for (int i = 0; i < 8; i++) {
                        key[i] = rec[i];
                        value[i] = rec[i+8];
                    }

                    Record newRec = new Record(key, value);
                    
                    // if the next record is smaller, swap with end of heap
                    // and decrease it's size
                    if (newRec.compareTo(lastRemoved) < 0) {
                        minHeap.removeMin();
                        minHeap.rSortMod(minHeap.getSize(), newRec);
                        nDead++;
                    }
                    else {
                        // insert the record to root and siftDown as needed.
                        minHeap.modify(0, newRec);
                    }
                    
                    // check if we need to re-load the input buffer
                    if (inBuffer.getSize() == 0) {
                        // reset dead nodes
                        minHeap.setSize(nDead + minHeap.getSize());
                        nDead = 0;
                        
                        byte[] temp = par.getBlock(); // grab up to 1 block
                        if (temp != null) {
                            inBuffer.setArray(temp, par.getCurrBytes());
                        }
                        else {
                            rFlag = true;
                        }
                        
                        
                    }

                }
                
            }
            
            
        }
        
        raf.close();

    }

}
