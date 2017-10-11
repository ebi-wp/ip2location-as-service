package uk.ac.ebi.model;

import com.ip2location.IPResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import uk.ac.ebi.service.IP2Service;

/**
 * Created by chojnasm on 15/06/2016.
 */

@Component
public class CachingRepositoryImpl implements CachingRepository {

    @Autowired
    private IP2Service ip2Service;

    @Cacheable("addresses")
    @Override
    public IPResult getIPResult(String ip) {
        return ip2Service.getIPResult(ip);
    }
}
