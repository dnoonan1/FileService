package util;

/**
 *
 * @author dnoonan1
 */
public interface Encoder<Decoded, Encoded> {
    /**
     * 
     * @param data
     * @return 
     */
    Encoded encode(Decoded data);
}
