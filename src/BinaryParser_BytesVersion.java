import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class BinaryParser_BytesVersion {
    private Buffer inputBuffer;


    public BinaryParser_BytesVersion(String fileName) throws IOException {
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        Boolean endFile = false;
        byte[] temp = new byte[0];
        inputBuffer = new Buffer(temp);

        while (!endFile) {
            ByteBuffer bb = ByteBuffer.allocate(8192);

            try {
                for (int i = 0; i < 8192; i++) {
                    byte b = raf.readByte(); // will jump to catch if fail
                    bb.put(b);
                }
                byte[] barr = bb.array();
                inputBuffer.setArray(barr);
                 
            }
            catch (EOFException e) {
                
                endFile = true;
            }

        }

        raf.close();
    }
}
