package util;

import java.text.ParseException;

/**
 *
 * @author Dan
 */
public interface LineParser {
    Object parseLine(String line) throws ParseException;
}
