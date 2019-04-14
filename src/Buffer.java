/**
 * A buffer. Read from and write to capabilities.
 *
 * @author <Yannik Sood> <yannik24>, Daniel Almeida <adaniel1>
 * @version 04.14.19
 *
 */
public class Buffer {
    private byte[] bufferArray;
    private static final int BUFFER_LENGTH = 8192;
    private int size;
    private int removeIndex;


    // -------------------------------------------------------------------------

    /**
     * Buffer constructor initializes buffer array and size.
    */
    public Buffer() {
        this.size = 0;
        bufferArray = new byte[BUFFER_LENGTH];
    }
    
    /**
     * Buffer constructor for optional pre-loading of a specific byte array.
     * 
     * @param buff      byte array to be loaded
     * @param length    number of elements in buff
     */
    public Buffer(byte[] buff, int length) {
        this.size = 0;

        if (buff != null && length <= BUFFER_LENGTH) {
            bufferArray = new byte[BUFFER_LENGTH];
            setArray(buff, length);
            this.removeIndex = 0;
        }
        else {
            this.bufferArray = null; // indicate bad input
        }
    }

    /**
     * This loads an array to the buffer.
     * 
     * @param buff      array to be loaded into the buffer
     * @param length    number of elements in the array
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
     * Return's this buffer's array.
     * 
     * @return      buffer array reference
     */
    public byte[] getArray() {
        return this.bufferArray;
    }


    /**
     * Return number of elements in the buffer currently.
     * 
     * @return the current number of elements in buffer
     */
    public int getSize() {
        return this.size;
    }
    
    /**
     * Returns number of records which is the totalBytes / 16.
     * 
     * @return num of pairs that make up a record
     */
    public int getNumRec() {
        return this.size / 16;
    }


    /**
     * Return the first record.
     * 
     * @return the record in a byte array.
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
     * of the buffer.
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
     * Insert 16 bytes at the front of the buffer
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
     * Clear buffer and return the buffer's 
     * state prior to (this is for outBuff)
     * 
     * @return      previous contents before clear
     */
    public byte[] clear() {
        // inefficiency of this copying grows as buffer has more data...
        byte[] temp = new byte[this.size];
        for (int i = 0; i < this.size; i++) {
            temp[i] = this.bufferArray[i];
        }
        
        this.bufferArray = new byte[BUFFER_LENGTH];
        this.size = 0;
        return temp;
    }

}
