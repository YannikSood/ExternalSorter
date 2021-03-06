import student.TestCase;

/**
 * Tests for the buffer class.
 * 
 * @author yanniksood <yannik24> & adaniel1 (Daniel Almeida)
 * @version 4.14.19
 */
public class BufferTest extends TestCase {
    private Buffer buffer;
    private byte[] bytes;

    /**
     * Default set up for each test case.
     */
    public void setUp() {

        bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        buffer = new Buffer(bytes, 10);
    }

    /**
     * Test buffer constructor
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

        assertEquals(8192, buffer.getArray().length);
        
        // test output buffer constructor
        Buffer oBuff = new Buffer();
        assertEquals(8192, oBuff.getArray().length);
        assertEquals(0, oBuff.getSize());
    }
    
    /**
     * Test buffer special cases
     */
    public void testConstructorSpecial() {
        // all invalid
        Buffer sOne = new Buffer(null, 8192);
        Buffer sTwo = new Buffer(null, 8193);
        Buffer sThree = new Buffer(bytes, 8193);
        
        assertNull(sOne.getArray());
        assertNull(sTwo.getArray());
        assertNull(sThree.getArray());
    }
    
    /**
     * Test setArray special cases
     */
    public void testSetArraySpecial() {
        buffer.setArray(new byte[8192], 8192); // limit
        assertEquals(8192, buffer.getSize());
        
        Buffer buff = new Buffer(bytes, 10);
        buff.setArray(bytes, 8193); // invalid
        assertEquals(10, buff.getSize());
    }

    /**
     * Test buffer setArray() method
     */
    public void testSet() {
        bytes = new byte[] { 4, 3, 2 };
        buffer.setArray(bytes, 3);
        assertEquals(buffer.getArray()[0], 4);
        assertEquals(buffer.getArray()[1], 3);
        assertEquals(buffer.getArray()[2], 2);
    }


    /**
     * Test buffer return size method.
     */
    public void testSize() {
        bytes = new byte[] { 4, 3, 2 };
        buffer.setArray(bytes, 3);
        assertEquals(buffer.getSize(), 3);
        assertEquals(8192, buffer.getArray().length);
        assertEquals(0, buffer.getNumRec());
    }
    
    /**
     * Test getNumRec on multiple bytes
     */
    public void testNumRec() {
        bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32 };
        buffer.setArray(bytes, 32);
        assertEquals(2, buffer.getNumRec());
    }


    /**
     * Test returning the front record
     */
    public void testGetRecord() {
        bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32 };
        buffer.setArray(bytes, 32);
        byte[] x = buffer.getRecord();
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
        buffer.remove();
        byte[] y = buffer.getRecord();
        assertEquals(buffer.getSize(), 16);
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
    }


    /**
     * Test removal of a record
     */
    public void testRemove() {
        bytes = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
            16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
            32 };
        buffer.setArray(bytes, 32);
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


    /**
     * Test insertion of a record
     */
    public void testInsert() {
        bytes = new byte[8192];
        byte[] temp = new byte[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13,
            14, 15, 16};
        byte[] temp2 = new byte[] {17, 18, 19, 20, 21, 22, 23, 24, 25,
            26, 27, 28, 29, 30, 31, 32 };
        for (int i = 0; i < 8192; i++) {
            bytes[i] = (byte)i;
        }

        buffer.setArray(bytes, 8192);
        assertEquals(buffer.getSize(), 8192);
        assertEquals(buffer.insert(temp), -1);
        while (buffer.getSize() != 0) {
            buffer.remove();
        }
        assertEquals(buffer.getSize(), 0);
        assertEquals(16, buffer.insert(temp));
        assertEquals(32, buffer.insert(temp2));
        assertEquals(-1, buffer.insert(bytes));
        assertEquals(32, buffer.getSize());
        
        for (int j = 0; j < 16; j++) {
            assertEquals(buffer.getArray()[j], temp[j]);
        }
        
        for (int k = 16; k < 32; k++) {
            assertEquals(buffer.getArray()[k], temp2[k - 16]);
        }
    }
    
    /**
     * This method tests clearing of the buffer array.
     */
    public void testClear() {
        buffer.clear();
        assertEquals(8192, buffer.getArray().length);
        assertNotNull(buffer.getArray());
        assertEquals(0, buffer.getSize());
    }

}
