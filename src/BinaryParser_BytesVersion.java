import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.apache.commons.codec.binary.Hex;

public class BinaryParser_BytesVersion {

    public BinaryParser_BytesVersion(String fileName) throws IOException {
        int byteCount = 0;
        int halfCount = 0;
        
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        Boolean endFile = false;
        
        while (!endFile) {
            ByteBuffer bb = ByteBuffer.allocate(8);
            
            try {
                for (int i = 0; i < 8; i++) {
                    byte b = raf.readByte(); // will jump to catch if fails
                    
                    bb.position(i);
                    bb.put(b);
                    byteCount++;
                }
                
                halfCount++;
                
                byte[] barr = bb.array();
                
                if (halfCount % 2 == 0) {
                    System.out.println("ID: " + Arrays.toString(barr)); 
                }
                else {
                    System.out.println("Key: " + Arrays.toString(barr));
                }
            }
            catch (EOFException e) {
                endFile = true;
            }

        }
        
        raf.close();
    }
}
