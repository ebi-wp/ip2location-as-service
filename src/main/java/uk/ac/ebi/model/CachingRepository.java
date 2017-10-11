package uk.ac.ebi.model;

import com.ip2location.IPResult;

/**
 * Created by chojnasm on 15/06/2016.
 */
public interface CachingRepository {
    IPResult getIPResult(String ip);
}
