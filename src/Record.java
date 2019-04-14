import org.apache.commons.codec.binary.Hex;

public class Record {
    private byte[] key;
    private byte[] value;
    private int index;
    
    /**
     * Constructor
     * 
     * @param Key key 
     * @param Value value
     */
    public Record (byte[] K, byte[] V) {
        this.key = K;
        this.value = V;
        index = 0;
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
     * Get the value
     * 
     * @return value
     */
    public byte[] getValue() {
        return value;
    }
    
    /**
     * get the index
     * 
     * @return index
     */
    public int getIndex() {
        return index;
    }
    
    /**
     * Set the index
     * @param i new index
     */
    public void setIndex(int i) {
        index = i;
    }
    
    /**
     * Get the complete Record in byte array.
     */
    public byte[] getRecord() {
        byte[] temp = new byte[16];
        
        for (int i = 0; i < 8; i++) {
            temp[i] = key[i];
            temp[i+8] = value[i];
        }
        
        return temp;
    }

    /**
     * Compares two records' key values for sorting.
     * 
     * @param record    Record to be compared to
     * @return          If this record > rec then positive value
     *                  If this record < rec then negative value
     *                  If equal, then 0
     */
    public int compareTo(Record rec) {
        // conversions
        char[] temp = Hex.encodeHex(this.key);
        String thisKey = String.valueOf(temp);
        
        //System.out.print("Key: ");
        //System.out.println(thisKey);
        
        temp = Hex.encodeHex(rec.getKey());
        String recordKey = String.valueOf(temp);
        
        //System.out.print("Value: ");
        //System.out.println(recordKey);
        
        // comparison
        return thisKey.compareTo(recordKey);
    }
}
