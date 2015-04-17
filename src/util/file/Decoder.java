package util.file;

/**
 *
 * @author dnoonan1
 */
public interface Decoder<Data, Format> {
    /**
     * 
     * @param data
     * @return 
     */
    Format decode(Data data);
}
