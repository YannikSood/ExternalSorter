/**
 * 
 * The Abstract Class for the bufferpool, taken 
 * from the openDSA notes
 * 
 * @author Yannik Sood <yannik24>
 *
 */
public interface BufferPoolADT {
    // Return pointer to the requested block
    public byte[] getblock(int block);

    // Set the dirty bit for the buffer holding "block"
    public void dirtyblock(int block);

    // Tell the size of a buffer
    public int blocksize();
    
 // Relate a block to a buffer, returning a pointer to a buffer object
    Buffer acquireBuffer(int block);
}