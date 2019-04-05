import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class BinaryParser {

    public BinaryParser(String fileName) throws IOException {

        byte numWords = 0;
        RandomAccessFile raf = new RandomAccessFile(fileName, "r");
        byte value = raf.readByte();
        numWords = value;

        while (numWords != 0) {
            try { // Read first 8 blocks in first.
                System.out.println(value);
                value = raf.readByte();
                numWords = value;
            }
            catch (

            FileNotFoundException e) {
                System.out.println("Could not file file:" + fileName);
            }
            catch (IOException e) {
                System.err.println("Writing error: " + e);
            }
        }
        raf.close();
    }
}
