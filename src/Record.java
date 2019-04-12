import org.apache.commons.codec.binary.Hex;

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
        return this.key;
    }
    
    /**
     * get the value
     * 
     * @return value
     */
    public byte[] getValue() {
        return this.value;
    }

    /**
     * Compares two records' keys for sorting.
     * If this > record, positive number.
     * If this < record, negative number.
     * If equal, 0.
     * 
     * @param record
     * @return
     */
    public int compareTo(Record record) {
        // conversions
        char[] temp = Hex.encodeHex(this.value);
        String thisKey = temp.toString();
        
        temp = Hex.encodeHex(record.getKey());
        String recordKey = temp.toString();
        
        // comparison
        return thisKey.compareTo(recordKey);
    }
}
