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
    private int size;
    // -------------------------------------------------------------------------
    /**
     * constructor for Buffer class
     * @param b the byte array that this buffer holds
     */
    public Buffer(byte[] buff) {
        size = 0;
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
            size++;
            System.out.print(bufferArray[i] + ", ");
        }
        
    }
    
    /**
     * @return the array
     */
    public byte[] getArray() {
        return bufferArray;
    }
    
    /**
     * @return the current size of the array
     */
    public int getSize() {
        return size;
    }
    
    /**
     * return the first 8 bytes 
     * @return the key (long)
     */
    public byte[] getKey() {
        byte[] temp = new byte[8];
        for (int i = 0; i < 8; i++) {
            temp[i] = bufferArray[i];
        }
        
        return temp;
    }
    
    /**
     * Return the 8 bytes after the first 8
     * @return the value (double)
     */
    public byte[] getVal() {
        byte[] temp = new byte[8];
        for (int i = 0; i < 8; i++) {
            temp[i] = bufferArray[i + 8];
        }
        
        return temp;
    }
    
    /**
     * Remove 16 bytes from the front
     * of the buffer
     * @return true if removed
     */
    public byte[] remove() {
        byte[] temp = new byte[16];
        for (int i = 0; i < 16; i++) {
            temp[i] = bufferArray[i];
            bufferArray[i] = 0;
            size--;
        }
        
        return temp;
    }
    
    /**
     * insert 16 bytes at the front of the buffer
     * @param bytes the bytes to insert
     * @return the position it was inserted
     */
    public int insert(byte[] bytes) {
        int x = 0;
        if (size < BUFFER_LENGTH - 16) {
            for (int i = size; i < size + 16; i++) {
                bufferArray[i] = bytes[x];
                x++;
            }
            size = size + 16;
            return size;
        }
        return -1;
    }
    
}
