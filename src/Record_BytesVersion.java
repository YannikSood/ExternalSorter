
public class Record_BytesVersion {
    private byte[] key;
    private byte[] value;
    
    /**
     * Constructor
     * 
     * @param Key key 
     * @param Value value
     */
    public Record_BytesVersion (byte[] K, byte[] V) {
        this.key = K;
        this.value = V;
    }
    
    /**
     * get the key
     * 
     * @return key
     */
    public byte[] getKey() {
        return key;
    }
    
    /**
     * get the value
     * 
     * @return value
     */
    public byte[] getValue() {
        return value;
    }

    /**
     * 
     * 
     * @param record
     * @return
     */
    public int compareTo(Record_BytesVersion record) {
        // TODO Auto-generated method stub
        return 0;
    }
}
