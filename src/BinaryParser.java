import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class BinaryParser {
    private RandomAccessFile raf;
    private Boolean endFile;
    private int currByte;

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
            this.currByte = 0;
        }
        catch (FileNotFoundException e) {
            this.endFile = true;
            
            if (raf != null) {
                this.raf.close();
            }
        }
    }
    
    /**
     * 
     * 
     * @return
     * @throws IOException
     */
    public byte[] getBlock() throws IOException {
        ByteBuffer bb = ByteBuffer.allocate(8192);

        try {
            // grab a block of bytes
            for (int i = 0; i < 8192; i++) {
                byte b = raf.readByte(); // will jump to catch if fail
                currByte++;
                bb.put(b);
            }

            // place it into an array
            byte[] barr = bb.array();

            // return a reference to this array
            // System.out.println(Arrays.toString(barr));
            return barr;

        }
        catch (EOFException e) { // end of file
            endFile = true;
            raf.close();
            
            // this will run if not a full block
            byte[] barr = bb.array();
            
            // we can use currByte to grab up to where data ends
            // instead of the full 8192 array if required
            
            // System.out.println("not complete block or empty");
            // System.out.println(Arrays.toString(barr));
            return barr;
        }
    }
    
    /**
     * Return current byte position of parser.
     * 
     * @return
     */
    public int getCurrByte() {
        return this.currByte;
    }
    
    /**
     * Return whether we have reached end of file or not.
     * 
     * @return
     */
    public boolean getEOF() {
        return this.endFile;
    }
}
