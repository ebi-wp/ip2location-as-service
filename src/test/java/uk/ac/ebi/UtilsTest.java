package uk.ac.ebi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by chojnasm on 13/06/2016.
 */
public class UtilsTest {
    @Test
    public void convertFlatJsonArrayToCsv() throws Exception {

        String inputJSON = "" +
                "[{\"city\":\"Cambridge\",\"elevation\":11}," +
                "{\"city\":\"Saffron Walden\", \"elevation\": 55}]";
        String outputCSV = "" +
                "city,elevation\n" +
                "Cambridge,11\n" +
                "Saffron Walden,55\n";

        assertEquals("JSON1 conversion is not correct", Utils.convertFlatJsonArrayToCsv(inputJSON,true, ','), outputCSV);
        assertEquals("JSON2 conversion is not correct", Utils.convertFlatJsonArrayToCsv(inputJSON,true, ','), outputCSV);
    }

}