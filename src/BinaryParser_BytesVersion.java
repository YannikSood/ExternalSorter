import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class BinaryParser_BytesVersion {
    private RandomAccessFile raf;
    private Boolean endFile;
    private int currByte;

    /**
     * 
     * 
     * @param fileName
     * @throws IOException
     */
    public BinaryParser_BytesVersion(String fileName) throws IOException {
        try {
            this.raf = new RandomAccessFile(fileName, "r");
            this.endFile = false;
            this.currByte = 0;
            this.getBlock();
        }
        catch (FileNotFoundException e) {
            this.endFile = true;
            this.raf.close();
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

            // place it into an array (not really needed but whatever)
            byte[] barr = bb.array();

            // return a reference to this array
            System.out.println(Arrays.toString(barr));
            return barr;

        }
        catch (EOFException e) { // end of file
            endFile = true;
            raf.close();
            
            byte[] barr = bb.array();
            System.out.println(Arrays.toString(barr));
            return barr;
        }
    }
}
