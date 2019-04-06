/**
 * 
 * The Abstract Class for the buffer, taken 
 * from the openDSA notes
 * 
 * @author Yannik Sood <yannik24>
 *
 */
public interface BufferADT {
    // Read the associated block from disk (if necessary) and return a
    // pointer to the data
    public byte[] readBlock();

    // Return a pointer to the buffer's data array (without reading from disk)
    public byte[] getDataPointer();

    // Flag buffer's contents as having changed, so that flushing the
    // block will write it back to disk
    public void markDirty();

    // Release the block's access to this buffer. Further accesses to
    // this buffer are illegal
    public void releaseBuffer();
}
