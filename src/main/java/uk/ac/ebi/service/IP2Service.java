package uk.ac.ebi.service;

import com.ip2location.IP2Location;
import com.ip2location.IPResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

/**
 * Instantiate IP2Location class, that uses configured DB file and license.key
 * Created by chojnasm on 08/06/2016.
 */

@Service
public class IP2Service{

    private final IP2Location ip2Location;
    private static final Logger log = LoggerFactory.getLogger(IP2Service.class);

    @Autowired
    public IP2Service(@Value("${spring.paths.db}") String dbPath,
                      @Value("${spring.paths.license}") String licensePath){

        ip2Location = new IP2Location();
        ip2Location.IPDatabasePath = dbPath;
        ip2Location.IPLicensePath = licensePath;
        ip2Location.UseMemoryMappedFile = true;
    }

    public IPResult getIPResult(String ip) {
        try {
            return ip2Location.IPQuery(ip);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
