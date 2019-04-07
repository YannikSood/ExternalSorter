import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BinaryParser {

    public BinaryParser(String fileName) throws IOException {
        int byteCount = 0;
        
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        
        while (byteCount < 128) {
            long idSum = 0;
            double keySum = 0;
            
            try {
                // grab first 8 bytes
                for (int i = 7; i >= 0; i--) {
                    long temp = ((long)raf.readUnsignedByte() << (i * 8));
                    idSum += temp;
                    byteCount++;
                }
                
                // grab second 8 bytes
                for (int i = 7; i >= 0; i--) {
                    double temp = ((long)raf.readByte() << (i * 8));
                    keySum += temp;
                    byteCount++;
                }
                
                // print record (16 bytes)
                System.out.println(idSum + " and " + keySum);
                
            }
            catch (EOFException e) {
                
            }

        }
        
        raf.close();
    }
}
