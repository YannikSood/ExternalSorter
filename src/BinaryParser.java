import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BinaryParser {

    public BinaryParser(String fileName) throws IOException {

        long key = 0;
        double value = 0.0;
        int i = 0;
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        
        while (i < 4096) {
            try {
                key = raf.readLong();
                value = raf.readDouble();
                System.out.println(key);
                System.out.println(value);

                i++;
            }
            catch (EOFException e) {
                break;
            }

        }
        raf.close();
    }
}
