import java.util.Arrays;
import student.TestCase;

/**
 * Test class for Record.
 * 
 * @author adaniel1 & yannik24
 * @version 4.14.19
 */
public class RecordTest extends TestCase {
    private Record rec1;
    private Record rec2;
    private Record rec3;
    
    /**
     * Setup class for Record tests
     */
    public void setUp() {
        byte[] key1 = new byte[] {113, -49, 34, -48, 2, -20, 59, -64};
        byte[] value1 = new byte[] {74, -1, 102, -38, 10, 58, 66, -3};
        byte[] key2 = new byte[] {16, 61, 103, 74, 12, -77, 25, 65};
        byte[] value2 = new byte[] {58, 121, 114, 105, 108, 23, 64, -83};
        byte[] max = new byte[] {-1, -1, -1, -1, -1, -1, -1, -1};
        
        this.rec1 = new Record(key1, value1);
        this.rec2 = new Record(key2, value2);
        this.rec3 = new Record(max, value2);
    }
    
    /**
     * Test the get key function for records.
     */
    public void testGetKey() {
        assertEquals("[113, -49, 34, -48, 2, -20, 59, -64]",
            Arrays.toString(rec1.getKey()));
    }
    
    /**
     * Test get record function for records.
     */
    public void testGetRecord() {
        assertEquals("[113, -49, 34, -48, 2, -20, 59, -64, 74,"
            + " -1, 102, -38, 10, 58, 66, -3]",
                Arrays.toString(rec1.getRecord()));
    }
    
    /**
     * Test the get value function for records.
     */
    public void testGetValue() {
        assertEquals("[74, -1, 102, -38, 10, 58, 66, -3]",
            Arrays.toString(rec1.getValue()));
    }
    
    /**
     * Test the compareTo method for records.
     */
    public void testCompareTo() {
        assertTrue(rec1.compareTo(rec2) > 0);
        assertTrue(rec2.compareTo(rec1) < 0);
        assertEquals(rec1.compareTo(rec1), 0);
        assertTrue(rec1.compareTo(rec3) < 0);
        assertTrue(rec3.compareTo(rec1) > 0);
        assertEquals(rec3.compareTo(rec3), 0);
    }

}
