package uk.ac.ebi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.ac.ebi.service.FormatterService;

import java.io.IOException;
import java.util.Iterator;

/**
 * Created by chojnasm on 11/06/2016.
 */
public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);


    /**
     * Convert non-nested JSON array into CSV
     * If csvHeader=true and csvDelimiter=, than:
     * [{"city":"Cambridge","elevation":11},{"city":"Saffron Walden", elevation: 55}]
     *
     * gives:
     *
     * city,elevation
     * Cambridge, 11
     * Saffron Walden, 55
     *
     * Comma is replaced with space in all fields
     * @param json
     * @param csvHeader
     * @param csvDelimiter
     * @return
     */
    public static String convertFlatJsonArrayToCsv(String json, boolean csvHeader, Character csvDelimiter) {

        boolean firstLine = true;
        boolean firstField;

        StringBuilder stringBuilder = new StringBuilder();

        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode nodes = mapper.readTree(json);

            for (JsonNode node : nodes) {

                if (csvHeader && firstLine) {
                    firstLine = false;
                    firstField = true;

                    Iterator<String> fieldNamesIterator = node.fieldNames();
                    while (fieldNamesIterator.hasNext()) {
                        String field = fieldNamesIterator.next().replace(csvDelimiter.toString()," ");
                        firstField = isFirstField(csvDelimiter, firstField, stringBuilder);
                        stringBuilder.append(field);
                    }
                    stringBuilder.append("\n");
                }

                firstField = true;
                Iterator<JsonNode> elementsIterator = node.elements();
                while (elementsIterator.hasNext()) {

                    JsonNode nodeLocal = elementsIterator.next();
                    String element = nodeLocal.asText();

                    firstField = isFirstField(csvDelimiter, firstField, stringBuilder);
                    stringBuilder.append(element.replace(","," "));
                }
                stringBuilder.append("\n");
            }
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
        return stringBuilder.toString();
    }

    /**
     * Prefix each field (except the first one) with delimiter
     * @param csvDelimiter
     * @param firstField
     * @param stringBuilder
     * @return
     */
    private static boolean isFirstField(Character csvDelimiter, boolean firstField, StringBuilder stringBuilder) {
        if (firstField) {
            firstField = false;
        } else {
            stringBuilder.append(csvDelimiter);
        }
        return firstField;
    }

    public static String[] listOfOutputFormats() {
        FormatterService.OUTPUT_FORMAT[] formats = FormatterService.OUTPUT_FORMAT.values();
        String[] names = new String[formats.length];

        for (int i = 0; i < formats.length; i++) {
            names[i] = formats[i].name();
        }
        return names;
    }
}
