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
        outputFile = new RandomAccessFile(outFileName, "r");
        minHeap = heap;
        outBuffer = outBuff;
        firstRecord = new Record(null, null);
        this.loadBlocks(runFile);
        lilHeap = new Heap(null, 8);
        
    }


    /**
     * Load the blocks into the heap array 
     * @param filename
     * @throws IOException
     */
    public void loadBlocks(RandomAccessFile file) throws IOException {
        
    }


    /**
     * Merge sort on run file using a 512 * 8 Array,
     * output buffer and output file
     * 
     * @throws IOException
     */
    public void mSort(String filename) throws IOException {

    }
}
