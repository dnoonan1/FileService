package util;

/**
 *
 * @author dnoonan1
 */
public interface Decoder<Encoded, Decoded> {
    /**
     * 
     * @param data
     * @return 
     * @throws java.lang.Exception 
     */
    Decoded decode(Encoded data) throws Exception;
}
