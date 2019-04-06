
public class BufferPool implements BufferPoolADT {
  
    private int size;
    private boolean dirty; //Maybe Unnecessary
    /**
     * 
     */
    public BufferPool() {
        size = 0;
    }
    
 // Tell the size of a buffer
    public int blocksize() {
        return size;
    }
    
    // Return pointer to the requested block
    public byte[] getblock(int block) {
        return null;
    }

    // Set the dirty bit for the buffer holding "block"
    public void dirtyblock(int block) {
        //Should we use it??
    }

    @Override
    public Buffer acquireBuffer(int block) {
        // TODO Auto-generated method stub
        return null;
    }

    
}
