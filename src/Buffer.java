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
public class Buffer {
    private byte[] bufferArray;
    private static final int BUFFER_LENGTH = 8192;
    private int size;
    private int removeIndex;


    // -------------------------------------------------------------------------

    /**
     * 
    */
    public Buffer() {
        this.size = 0;
        bufferArray = new byte[BUFFER_LENGTH];
    }


    /**
     * 
     * 
     * @param buff
     * @param length
     */
    public Buffer(byte[] buff, int length) {
        this.size = 0;

        if (buff != null && length <= BUFFER_LENGTH) {
            bufferArray = new byte[BUFFER_LENGTH];
            setArray(buff, length);
            this.removeIndex = 0;
        }
    }


    /**
     * 
     * 
     * @param buff
     * @param length
     */
    public void setArray(byte[] buff, int length) {
        if (length <= BUFFER_LENGTH) {
            for (int i = 0; i < length; i++) {
                bufferArray[i] = buff[i];
            }
            size = length;
            removeIndex = 0;
        }
    }


    /**
     * @return reference to the buffer the array
     */
    public byte[] getArray() {
        return this.bufferArray;
    }


    /**
     * @return the current number of elements in buffer
     */
    public int getSize() {
        return this.size;
    }


    /**
     * @return num of pairs that make up a record
     */
    public int getNumRec() {
        return this.size / 16;
    }


    /**
     * return the first record
     * 
     * @return the record
     */
    public byte[] getRecord() {
        byte[] temp = new byte[16];
        int x = 0;
        for (int i = removeIndex; i < removeIndex + 16; i++) {
            temp[x] = bufferArray[i];
            x++;
        }
        return temp;
    }


    /**
     * Remove 16 bytes from the front
     * of the buffer
     * 
     * @return bytes removed
     */
    public byte[] remove() {
        if (size == 0) {
            return null;
        }

        byte[] temp = new byte[16];
        int j = 0;

        for (int i = removeIndex; i < removeIndex + 16; i++) {
            temp[j] = bufferArray[i];
            bufferArray[i] = 0;
            j++;

        }

        removeIndex = removeIndex + 16;
        size = size - 16;
        return temp;
    }


    /**
     * insert 16 bytes at the front of the buffer
     * 
     * @param bytes
     *            the bytes to insert
     * @return the position it was inserted
     */
    public int insert(byte[] bytes) {
        int x = 0;
        if (bytes.length + size <= BUFFER_LENGTH) {
            for (int i = size; i < size + 16; i++) {
                bufferArray[i] = bytes[x];
                x++;
            }
            size = size + bytes.length;
            return size;
        }

        return -1;

    }


    /**
     * Clear buffer and return the buffer's state prior to (this is for outBuff)
     */
    public byte[] clear() {
        byte[] temp = this.bufferArray;
        this.bufferArray = new byte[BUFFER_LENGTH];
        this.size = 0;
        return temp;
    }

}
