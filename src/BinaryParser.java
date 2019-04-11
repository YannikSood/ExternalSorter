import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BinaryParser {

    public BinaryParser(String fileName) throws IOException {
        int byteCount = 0;
        
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        Boolean endFile = false;
        
        while (!endFile) {
            long idSum = 0;
            double keySum = 0;
            int completeRecord = 0;
            
            try {
                // grab first 8 bytes
                for (int i = 7; i >= 0; i--) {
                    long temp = ((long)raf.readUnsignedByte() << (i * 8));
                    idSum += temp;
                    
                    byteCount++;
                    completeRecord += 8;
                }
                
                // print first 8 bytes
                System.out.print(idSum + " and ");
                
                // grab second 8 bytes
                for (int i = 7; i >= 0; i--) {
                    double temp = ((long)raf.readByte() << (i * 8));
                    keySum += temp;
                    
                    byteCount++;
                    completeRecord += 8;
                }
                
                // print second 8 bytes and new line
                System.out.println(keySum);
            }
            catch (EOFException e) {
                endFile = true;
            }

        }
        
        raf.close();
    }
}
