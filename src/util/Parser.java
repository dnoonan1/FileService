package util;

import java.text.ParseException;

/**
 *
 * @author Dan
 */
public interface Parser {
    Object parse(String s) throws ParseException;
}
