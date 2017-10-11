package uk.ac.ebi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.ac.ebi.Utils;
import uk.ac.ebi.model.CachingRepository;
import uk.ac.ebi.model.IP2Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Format results with either Jackson's mapper or custom CSV formatter.
 * Jackson's mappers are invoked for JSON, YAML and XML formats.
 * TSV format is in fact CSV with tab as delimiter
 *
 * Created by chojnasm on 11/06/2016.
 */

@Service
public class FormatterService {

    @Autowired
    private CachingRepository cachedMappings;

    private static final Logger log = LoggerFactory.getLogger(FormatterService.class);

    private final static String delimitPattern = "\\r?\\n|,|;";
    public enum OUTPUT_FORMAT {
        CSV, JSON, XML, YAML, TSV
    }

    public String getFormattedOutput(String[] fieldsArray,
                                     String ips,
                                     OUTPUT_FORMAT format,
                                     boolean csvHeader,
                                     Character csvDelimiter,
                                     boolean ifRemoveDuplicates){

        String formattedOutput;
        String[] ipsArray = ips.split(delimitPattern);
        List<IP2Model> ip2Models = new ArrayList<>();

        Set<String> alreadyRetrievedIPs = new HashSet<>();

        for (int i = 0; i < ipsArray.length; i++) {

            // skip IP addresses that where already processed
            if(ifRemoveDuplicates && alreadyRetrievedIPs.contains(ipsArray[i])){
                continue;
            }
            alreadyRetrievedIPs.add(ipsArray[i]);

            ip2Models.add(new IP2Model(ipsArray[i], fieldsArray, cachedMappings));
        }

        ObjectMapper mapper = null;

        switch (format) {
            case JSON:
                mapper = new ObjectMapper();
                break;
            case XML:
                mapper = new XmlMapper();
                break;
            case YAML:
                mapper = new YAMLMapper();
                break;
            case CSV:
                break;
            case TSV:
                format = OUTPUT_FORMAT.CSV;
                csvDelimiter = '\t';
                break;
//            set format as csv if none of available was specified
            default:
                format = OUTPUT_FORMAT.CSV;
                break;
        }

        // Use JSON mapper to build CSV, JSON already has user filtered fields
        if (format.equals(OUTPUT_FORMAT.CSV)) {
            mapper = new ObjectMapper();
        }

        String mapperOutput;

        try {
            mapperOutput = mapper.writeValueAsString(ip2Models);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }

        if (!format.equals(OUTPUT_FORMAT.CSV)) {
            formattedOutput = mapperOutput;
        } else {
            formattedOutput = Utils.convertFlatJsonArrayToCsv(mapperOutput, csvHeader, csvDelimiter);
        }
        return formattedOutput;
    }

    public String getFormattedOutput(String fields,
                                     String ips,
                                     OUTPUT_FORMAT format,
                                     boolean csvHeader,
                                     Character csvDelimiter,
                                     boolean ifRemoveDuplicates) {

        String[] fieldsArray = fields.split(delimitPattern);

        return getFormattedOutput(fieldsArray,ips, format, csvHeader, csvDelimiter, ifRemoveDuplicates);
    }
}
