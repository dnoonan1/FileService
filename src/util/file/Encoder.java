package util.file;

/**
 *
 * @author dnoonan1
 */
public interface Encoder<Data, Format> {
    /**
     * 
     * @param data
     * @return 
     */
    Format encode(Data data);
}
