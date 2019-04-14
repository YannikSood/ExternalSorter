import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Arrays;

/**
 * 
 * @author yanniksood
 *
 */
public class MergeSorter {
    private Buffer outBuffer;
    private Heap minHeap;
    private Record firstRecord;
    private RandomAccessFile runFile;
    private RandomAccessFile outputFile;
    private Record[] firstRecords;
    private Heap lilHeap;


    /**
     * MergeSort
     * @param runFileName
     * @param outFileName
     * @param heap
     * @param outBuff
     * @throws IOException 
     */
    public MergeSorter(
        String runFileName,
        String outFileName,
        Heap heap,
        Buffer outBuff)
        throws IOException {
        runFile = new RandomAccessFile(runFileName, "r");
        outputFile = new RandomAccessFile(outFileName, "w");
        minHeap = heap;
        outBuffer = outBuff;
        firstRecord = new Record(null, null);
        lilHeap = new Heap(null, 8);
        
        
    }


    /**
     * Load the blocks into the heap array 
     * 
     * @param bI the block index
     * @param rI the run index
     * 
     * @throws IOException
     */
    public void loadBlocks(int bI, int rI) throws IOException { 
        int blockIndex = bI;
        int runIndex = rI;
        byte[] temp = new byte[8192 * 8];
        
        for (int j = 0; j < 8; j++) {
            temp = this.getBlock(runIndex, blockIndex);
            int byteCount = 0;
            
            while (byteCount < 8192) {
                
                byte[] key = new byte[8];
                byte[] value = new byte[8];
                
                for (int i = 0; i < 8; i++) {
                    key[i] = temp[byteCount];
                    value[i] = temp[byteCount];
                    byteCount++;
                }
                
                byteCount = byteCount + 8;

                Record newRec = new Record(key, value);
                
                // assign record
                minHeap.insert(newRec);
            }
            runIndex++;
        }
        
        
        
        
        
    }


    /**
     * Merge sort on run file using a 512 * 8 Array,
     * output buffer and output file
     * 
     * @throws IOException
     */
    public void mSort(String filename) throws IOException {

    }
    
    /**
     * Get a block
     * @param runIndex What Run?
     * @param blockIndex What Block?
     * @return the block at the run & index desired
     * @throws IOException 
     */
    private byte[] getBlock(int runIndex, int blockIndex) throws IOException {
        byte[] temp = new byte[8192];
        int runOffset = 8192 * 8;
        int blockOffset = 8192;
        
        runFile.seek((runOffset * runIndex) + (blockOffset * blockIndex));
        
        for (int i = 0; i < 8192; i++) {
            temp[i] = runFile.readByte();
        }
        return temp;
        
    }
}
