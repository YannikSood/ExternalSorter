import java.io.IOException;
import java.io.RandomAccessFile;
import student.TestCase;

/**
 * Test cases for ExternalSorter, which features a replacement sort
 * and a merge sort.
 * 
 * @author adaniel1 (Daniel Almeida), yannik24 (Yannik Sood)
 * @version 4.14.19
 */
public class ExternalSorterTest extends TestCase {
    private RandomAccessFile rafExpected;
    private RandomAccessFile rafAfter;
    
    /**
     * Default constructor for tests.
     * Creates two files for replacement tests 
     * and two files for mergesort tests.
     */
    public void setUp() {
        // nothing to do
    }
    
    /**
     * Test replacement sort on 8 blocks exactly.
     * 
     * @throws IOException      file not found or I/O
     */
    public void testReplacementEight() throws IOException {
        // sort
        new ExternalSorter("eightBlocks.bin");
        
        // after sort
        this.rafAfter = new RandomAccessFile("eightBlocks.bin", "r");
        
        byte[] after = new byte[65536];
        for (int i = 0; i < 65536; i++) {
            after[i] = rafAfter.readByte();
        }
        
        // expected
        this.rafExpected = new RandomAccessFile("eightBlocks.bin", "r");
        
        byte[] expected = new byte[65536];
        for (int i = 0; i < 65536; i++) {
            expected[i] = rafExpected.readByte();
        }
        
        // check
        for (int i = 0; i < 65536; i++) {
            assertEquals(expected[i], after[i]);
        }
        
    }
    
    /**
     * Test replacement sort on less than 8 blocks.
     * 
     * @throws IOException      file not found or I/O
     */
    public void testReplacementLessEight() throws IOException {
        // sort
        new ExternalSorter("incompleteBlock.bin");
        
        // after sort
        this.rafAfter = new RandomAccessFile("incompleteBlock.bin", "r");
        
        byte[] after = new byte[96];
        for (int i = 0; i < 96; i++) {
            after[i] = rafAfter.readByte();
        }
        
        // expected
        this.rafExpected = new RandomAccessFile("incompleteBlock.bin", "r");
        
        byte[] expected = new byte[96];
        for (int i = 0; i < 96; i++) {
            expected[i] = rafExpected.readByte();
        }
        
        // check
        for (int i = 0; i < 96; i++) {
            assertEquals(expected[i], after[i]);
        }
        
    }
    
}
