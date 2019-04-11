import java.io.IOException;
import java.util.Arrays;

/**
 * A buffer. Read from and write to capabilities
 * 
 *
 * 
 * * // On my honor: // // - I have not used source code obtained from
 * another student, // or any other unauthorized source, either modified
 * or // unmodified. // // - All source code and documentation used in
 * my program is // either my original work, or was derived by me from
 * the // source code published in the textbook for this course. // // -
 * I have not discussed coding details about this project with // anyone
 * other than my partner (in the case of a joint // submission),
 * instructor, ACM/UPE tutors or the TAs assigned // to this course. I
 * understand that I may discuss the concepts // of this program with
 * other students, and that another student // may help me debug my
 * program so long as neither of us writes // anything during the
 * discussion or modifies any computer file // during the discussion. I
 * have violated neither the spirit nor // letter of this restriction.
 *
 * @author <Yannik Sood> <yannik24>
 * @version 04.10.19
 *
 */
public class Buffer implements BufferADT {
    private byte[] bufferArray;
    private static final int BUFFER_LENGTH = 8192;
    // -------------------------------------------------------------------------
    /**
     * constructor for Buffer class
     * @param b the byte array that this buffer holds
     */
    public Buffer(byte[] buff) {
        if (buff != null && buff.length <= BUFFER_LENGTH) {
            bufferArray = new byte[BUFFER_LENGTH];
            setArray(buff);
        }
    }
    
    /**
     * @param a the array to set
     */
    public void setArray(byte[] buff) {
        for (int i = 0; i < buff.length; i++) {
            bufferArray[i] = buff[i];
            System.out.println(bufferArray[i]);
        }
        
    }
    
    /**
     * @return the array
     */
    public byte[] getArray() {
        return bufferArray;
    }
    
    
}
