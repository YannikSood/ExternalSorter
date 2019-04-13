import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class BinaryParser {
    private RandomAccessFile raf;
    private Boolean endFile;
    private int totalBytes;
    private int currBytes;

    /**
     * 
     * 
     * @param fileName
     * @throws IOException
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
     * 
     * 
     * @return
     * @throws IOException
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
            System.out.println(Arrays.toString(barr));
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
                System.out.println("there was nothing to read on call");
                return null;
            }
            else { // partial block read
                // return a reference to this array
                System.out.println("not a complete block");
                System.out.println(Arrays.toString(barr));
                return barr;
            }
        }
    }
    
    /**
     * Return current byte position of parser.
     * 
     * @return
     */
    public int getTotalBytes() {
        return this.totalBytes;
    }
    
    /**
     * Return whether we have reached end of file or not.
     * 
     * @return
     */
    public boolean getEOF() {
        return this.endFile;
    }
    
    /**
     * Return the number of bytes read for a single block grab.
     * Note: This resets to 0 every time getBlock() is called.
     */
    public int getCurrBytes() {
        return this.currBytes;
    }
    
}
