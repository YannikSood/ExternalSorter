import java.nio.ByteBuffer;

/**
 * This class represents a Record. It can store byte arrays for key and value.
 * 
 * @author adaniel1 (Daniel Almeida), yannik24 (Yannik Sood)
 * @version 4.14.19
 */
public class Record {
    private byte[] key;
    private byte[] value;
    
    /**
     * Constructor initiates key and value byte arrays
     * 
     * @param k     record key 
     * @param v     record value
     */
    public Record(byte[] k, byte[] v) {
        // switched around because project specification unclear
        this.key = k;
        this.value = v;
    }
    
    /**
     * get the record's key
     * 
     * @return      record's key byte array
     */
    public byte[] getKey() {
        return key;
    }
    
    /**
     * Get the record's value
     * 
     * @return      record's value byte array
     */
    public byte[] getValue() {
        return value;
    }
    
    /**
     * Get the complete Record in byte array.
     * 
     * @return      16 byte array with key and value
     */
    public byte[] getRecord() {
        byte[] temp = new byte[16];
        
        for (int i = 0; i < 8; i++) {
            temp[i] = key[i];
            temp[i + 8] = value[i];
        }
        
        return temp;
    }

    /**
     * Compares two records' key values for sorting.
     * 
     * @param rec    Record to be compared to
     * @return          If this record > rec then positive value
     *                  If this record < rec then negative value
     *                  If equal, then 0
     */
    public int compareTo(Record rec) {
        long thisK = ByteBuffer.wrap(this.value).getLong();
        long recK = ByteBuffer.wrap(rec.getValue()).getLong();
        
        // comparison
        if (thisK > recK) {
            return 5;
        }
        else if (thisK < recK) {
            return -4;
        }
        
        return 0;
    }
}
