import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;

/**
 * BinaryParser class is responsible for parsing the input file
 * using RandomAccessFile.
 * 
 * @author      adaniel1 & yannik24
 * @version     4.14.19
 */
public class BinaryParser {
    private RandomAccessFile raf;
    private Boolean endFile;
    private int totalBytes;
    private int currBytes;

    /**
     * Default constructor initializes RAF and variables.
     * 
     * @param fileName          Name of file to be read
     * @throws IOException      If the file is not found
     */
    public BinaryParser(String fileName) throws IOException {
        try {
            this.raf = new RandomAccessFile(fileName, "r");
            this.endFile = false;
            this.totalBytes = 0;
        }
        catch (FileNotFoundException e) {
            return;
        }
    }
    
    /**
     * This method grabs one block's worth of bytes from the input file
     * and returns the bytes in a byte array.
     * 
     * @return                  byte array with data
     * @throws IOException      If end of file has been reached
     */
    public byte[] getBlock() throws IOException {
        // initialize
        ByteBuffer bb = ByteBuffer.allocate(8192);
        this.currBytes = 0;

        try {
            // grab a block of bytes
            for (int i = 0; i < 8192; i++) {
                byte b = raf.readByte(); // will jump to catch if fail
                this.totalBytes++;
                currBytes++;
                bb.put(b);
            }

            // place it into an array
            byte[] barr = bb.array();

            // return a reference to this array
            return barr;

        }
        catch (EOFException e) {
            // this will run if EOF
            this.endFile = true;
            this.raf.close();
            
            byte[] barr = bb.array();
            
            // we can use currByte to grab up to where data ends
            // instead of the full 8192 array if required
            
            // this is O(currBytes), I think a better soln. is to pass
            // currBytes to buffer so that it knows how far to read
            
            if (this.currBytes == 0) { // there was nothing was read
                return null;
            }
            else { // partial block read
                // return a reference to this array
                return barr;
            }
        }
    }
    
    /**
     * Return total bytes read by the parser during it's lifetime
     * 
     * @return      total bytes read
     */
    public int getTotalBytes() {
        return this.totalBytes;
    }
    
    /**
     * Return whether we have reached end of file or not.
     * 
     * @return      end of file status
     */
    public boolean getEOF() {
        return this.endFile;
    }
    
    /**
     * Return the number of bytes read for a single block grab.
     * Note: This resets to 0 every time getBlock() is called.
     * 
     * @return      bytes read for one block grab session
     */
    public int getCurrBytes() {
        return this.currBytes;
    }
    
}
