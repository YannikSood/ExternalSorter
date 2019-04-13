import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import student.TestCase;

/**
 * Test class for the file parser.
 * 
 * @author adaniel1
 *
 */
public class BinaryParserTest extends TestCase {
    BinaryParser par;
    
    /**
     * Setup for each test.
     * 
     * @throws IOException 
     */
    public void setUp() throws IOException {
        this.par = new BinaryParser("sampleInput16.bin");
    }
    
    /**
     * Test constructor and wrong file.
     * 
     * @throws IOException 
     */
    public void testBinaryParser() throws IOException {
        // constructor test
        assertFalse(par.getFileStatus());
        assertEquals(0, par.getCurrByte());

        // test wrong file name
        try {
            BinaryParser temp = new BinaryParser("Game of Thrones this god damn"
                + "Sunday F%@$, who got an HBO account I can use??.txt");
            assertTrue(temp.getFileStatus());
        }
        catch (Exception r) {
            assertTrue(r instanceof FileNotFoundException);
        }
    }
    
    /**
     * Test the getBlock() function on an empty file.
     */
    public void testGetBlockEmpty() {
        try {
            BinaryParser temp = new BinaryParser("empty.bin"); // empty file
            assertFalse(temp.getFileStatus());
            
            temp.getBlock();
        }
        catch (Exception r) {
            assertTrue(r instanceof EOFException);
        }
    }
    
    /**
     * Test getBlock() function on an incomplete block.
     */
    public void testGetBlockPartial() {
        
    }
}
