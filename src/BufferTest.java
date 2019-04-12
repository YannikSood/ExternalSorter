import student.TestCase;

/**
 * @author yanniksood
 *
 */
public class BufferTest extends TestCase {

    private Buffer buffer;
    private byte[] bytes;
    /**
     * Unused Constructor
     */
    public BufferTest() {
        //Unused Constructor
    }
    
    public void setUp() {

        bytes = new byte[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        buffer = new Buffer(bytes);
    }
    
    /**
     * test constructor
     */
    public void testConstructor() {
        assertEquals(buffer.getArray()[0], 1);
        assertEquals(buffer.getArray()[1], 2);
        assertEquals(buffer.getArray()[2], 3);
        assertEquals(buffer.getArray()[3], 4);
        assertEquals(buffer.getArray()[4], 5);
        assertEquals(buffer.getArray()[5], 6);
        assertEquals(buffer.getArray()[6], 7);
        assertEquals(buffer.getArray()[7], 8);
        assertEquals(buffer.getArray()[8], 9);
        assertEquals(buffer.getArray()[9], 10);
    }
    
    /**
     * test constructor
     */
    public void testSet() {
        bytes = new byte[]{4, 3, 2};
        buffer.setArray(bytes);
        assertEquals(buffer.getArray()[0], 4);
        assertEquals(buffer.getArray()[1], 3);
        assertEquals(buffer.getArray()[2], 2);
    }
    
    /**
     * test constructor
     */
    public void testSize() {
        bytes = new byte[]{4, 3, 2};
        buffer.setArray(bytes);
        assertEquals(buffer.getSize(), 3);
    }
    
    /**
     * test constructor
     */
    public void testGetKey() {
        bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        buffer.setArray(bytes);
        assertEquals(buffer.getKey()[0], 1);
        assertEquals(buffer.getKey()[1], 2);
        assertEquals(buffer.getKey()[2], 3);
        assertEquals(buffer.getKey()[3], 4);
        assertEquals(buffer.getKey()[4], 5);
        assertEquals(buffer.getKey()[5], 6);
        assertEquals(buffer.getKey()[6], 7);
        assertEquals(buffer.getKey()[7], 8);
    }
    
    /**
     * test constructor
     */
    public void testGetVal() {
        bytes = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
        buffer.setArray(bytes);
        assertEquals(buffer.getVal()[0], 9);
        assertEquals(buffer.getVal()[1], 10);
        assertEquals(buffer.getVal()[2], 11);
        assertEquals(buffer.getVal()[3], 12);
        assertEquals(buffer.getVal()[4], 13);
        assertEquals(buffer.getVal()[5], 14);
        assertEquals(buffer.getVal()[6], 15);
        assertEquals(buffer.getVal()[7], 16);
    }
    
    
    
    
    
    
}

