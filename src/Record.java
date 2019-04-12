
public class Record {
    private byte[] key;
    private byte[] value;
    
    /**
     * Constructor
     * 
     * @param Key key 
     * @param Value value
     */
    public Record (byte[] K, byte[] V) {
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
    public int compareTo(Record record) {
        // TODO Auto-generated method stub
        return 0;
        // conversions
        char[] temp = Hex.encodeHex(this.value);
        String thisKey = temp.toString();
        
        temp = Hex.encodeHex(record.getKey());
        String recordKey = temp.toString();
        
        // comparison
        return thisKey.compareTo(recordKey);
    }
}
