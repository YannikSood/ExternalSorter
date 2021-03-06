import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import student.TestCase;
/**
 * Test class for the file parser.
 * 
 * @author adaniel1 & yannik24
 * @version 4.14.19
 */
public class BinaryParserTest extends TestCase {
    private BinaryParser par;
    
    /**
     * Setup for each test.
     * 
     * @throws IOException if wrong file name
     */
    public void setUp() throws IOException {
        this.par = new BinaryParser("incompleteBlock.bin");
    }
    
    // Note: These tests will also test the trivial getter methods.
    
    /**
     * Test constructor and wrong file name.
     */
    public void testBinaryParser() {
        // constructor test
        assertFalse(par.getEOF());
        assertEquals(0, par.getTotalBytes());
    }
    
    /**
     * Test wrong file name
     */
    public void testWrongFilenName() {
        // test wrong file name
        Exception exception = null;
        BinaryParser temp = null;
        
        try {
            temp = new BinaryParser("Game of Thrones this "
                + "Sunday F%@$, who got an HBO account I can use??.txt");
        }
        catch (IOException e) {
            exception = e;
            assertTrue(e instanceof FileNotFoundException);
            assertTrue(temp.getEOF());
        }
        
        assertNotNull(exception);
    }
    
    /**
     * Test the getBlock() function on an empty file.
    */
    public void testGetBlockEmpty() {
        BinaryParser temp = null;
        Exception exception = null;
        
        try {
            temp = new BinaryParser("empty.bin"); // empty file
            assertFalse(temp.getEOF());
            assertEquals(0, temp.getTotalBytes());
            
            temp.getBlock(); // ignoring the empty array it returns
        }
        catch (IOException r) {
            exception = r;
            assertTrue(r instanceof EOFException);
            assertTrue(temp.getEOF());
            assertEquals(0, temp.getTotalBytes());
        }
        
        assertNotNull(exception);
    }
    
    /**
     * Test getBlock() function on an incomplete block.
     */
    public void testGetBlockPartial() {
        byte[] b = null;
        Exception exception = null;
        
        try {
            assertFalse(par.getEOF());
            assertEquals(0, par.getTotalBytes());
            
            b = par.getBlock();
        }
        catch (IOException r) {
            exception = r;
            assertTrue(r instanceof EOFException);
            
            // but we still grabbed something
            assertNotNull(b);
            assertEquals(88, par.getTotalBytes());
            byte[] result = new byte[] {113, -80};
            assertEquals(result[0], b[0]);
            assertEquals(result[1], b[87]);
            
            // counter checks
            assertEquals(88, par.getCurrBytes());
            assertEquals(88, par.getTotalBytes());
        }
        
        assertNotNull(exception);
    }
    
    /**
     * Test getblock() function on a complete block.
     */
    public void testGetBlock() {
        BinaryParser temp = null;
        byte[] b = null;
        Exception exception = null;
        
        try {
            temp = new BinaryParser("testBin.bin");
            assertFalse(temp.getEOF());
            
            b = temp.getBlock();
        }
        catch (IOException r) {
            assertTrue(r instanceof EOFException);
            assertNotNull(b);
            
            // we grabbed first block out of 2
            assertEquals(8192, temp.getTotalBytes());
            byte[] result = new byte[] {0, 98};
            assertEquals(result[0], b[0]);
            assertEquals(result[1], b[b.length - 1]);
           
            // counter checks
            assertEquals(8192, par.getCurrBytes());
            assertEquals(8192, par.getTotalBytes());
        }
        
        assertNotNull(exception);
    }
    
    /**
     * Test getblock() function on two complete blocks.
     */
    public void testGetTwoBlocks() {
        BinaryParser temp = null;
        byte[] b = null;
        Exception exception = null;
        
        try {
            temp = new BinaryParser("testBin.bin");
            assertFalse(temp.getEOF());
            
            b = temp.getBlock(); // first block
            b = temp.getBlock(); // second block
        }
        catch (IOException r) {
            assertTrue(r instanceof EOFException);
            assertNotNull(b);
            
            // we grabbed second block out of 2
            byte[] result = new byte[] {-91, -118};
            assertEquals(result[0], b[0]);
            assertEquals(result[1], b[b.length - 1]);
            
            // counter checks
            assertEquals(8192, par.getCurrBytes());
            assertEquals(8192 * 2, par.getTotalBytes());
        }
        
        assertNotNull(exception);
    }
    
}
