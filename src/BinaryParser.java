import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BinaryParser {

    public BinaryParser(String fileName) throws IOException {

        long key;
        double value;
        boolean i = true;
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        while (i) {
            try {
                key = raf.readLong();
                value = raf.readDouble();
                System.out.println(key);
                System.out.println(value);

            }
            catch (EOFException e) {
                i = false;
            }

        }
        raf.close();
    }
}
