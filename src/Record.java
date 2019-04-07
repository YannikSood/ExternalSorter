
public class Record {
    private long key;
    private double value;
    
    /**
     * Constructor
     * @param Key key 
     * @param Value value
     */
    public Record (long Key, double Value) {
        key = Key;
        value = Value;
    }
    
    /**
     * get the key
     * @return key
     */
    public long getKey() {
        return key;
    }
    
    /**
     * get the value
     * @return value
     */
    public double getValue() {
        return value;
    }

    public int compareTo(Record record) {
        // TODO Auto-generated method stub
        return 0;
    }
}
