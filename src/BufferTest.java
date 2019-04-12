import student.TestCase;

/**
 * @author yanniksood
 *
 */
public class BufferTest extends TestCase {
    private Buffer buffer;
    private byte[] bytes;


    public void setUp() {

        bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
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
        bytes = new byte[] { 4, 3, 2 };
        buffer.setArray(bytes);
        assertEquals(buffer.getArray()[0], 4);
        assertEquals(buffer.getArray()[1], 3);
        assertEquals(buffer.getArray()[2], 2);
    }


    /**
     * test constructor
     */
    public void testSize() {
        bytes = new byte[] { 4, 3, 2 };
        buffer.setArray(bytes);
        assertEquals(buffer.getSize(), 3);
    }


    /**
     * test constructor
     */
    public void testGetKey() {
        bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20 };
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
        bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20 };
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


    /**
     * test constructor
     */
    public void testRemove() {
        bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32 };
        buffer.setArray(bytes);
        assertEquals(buffer.getSize(), 32);
        byte[] x = buffer.remove();
        assertEquals(x[0], 1);
        assertEquals(x[1], 2);
        assertEquals(x[2], 3);
        assertEquals(x[3], 4);
        assertEquals(x[4], 5);
        assertEquals(x[5], 6);
        assertEquals(x[6], 7);
        assertEquals(x[7], 8);
        assertEquals(x[8], 9);
        assertEquals(x[9], 10);
        assertEquals(x[10], 11);
        assertEquals(x[11], 12);
        assertEquals(x[12], 13);
        assertEquals(x[13], 14);
        assertEquals(x[14], 15);
        assertEquals(x[15], 16);
        assertEquals(buffer.getSize(), 16);
        byte[] y = buffer.remove();
        assertEquals(buffer.getSize(), 0);
        assertEquals(y[0], 17);
        assertEquals(y[1], 18);
        assertEquals(y[2], 19);
        assertEquals(y[3], 20);
        assertEquals(y[4], 21);
        assertEquals(y[5], 22);
        assertEquals(y[6], 23);
        assertEquals(y[7], 24);
        assertEquals(y[8], 25);
        assertEquals(y[9], 26);
        assertEquals(y[10], 27);
        assertEquals(y[11], 28);
        assertEquals(y[12], 29);
        assertEquals(y[13], 30);
        assertEquals(y[14], 31);
        assertEquals(y[15], 32);
        assertEquals(buffer.remove(), null);
    }

}
