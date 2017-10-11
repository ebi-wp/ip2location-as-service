package uk.ac.ebi.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ip2location.IPResult;

import java.util.Arrays;
import java.util.List;

/**
 * Created by chojnasm on 08/06/2016.
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class IP2Model {


    private static final String[] ALL_FIELDS = new String[]{
            "ip_address", "country_short", "country_long", "region", "city", "isp", "latitude", "longitude", "domain",
            "zipcode", "netspeed", "timezone", "iddcode", "areacode", "weatherstationcode", "weatherstationname", "mcc",
            "mnc", "mobilebrand", "elevation", "usagetype", "status"};

    public static List<String> getAllFields(){
        return Arrays.asList(ALL_FIELDS);
    }

    String ip_address;
    String country_short;
    String country_long;
    String region;
    String city;
    String isp;
    Float latitude;
    Float longitude;
    String domain;
    String zipcode;
    String netspeed;
    String timezone;
    String iddcode;
    String areacode;
    String weatherstationcode;
    String weatherstationname;
    String mcc;
    String mnc;
    String mobilebrand;
    Float elevation;
    String usagetype;
    String status;

    public IP2Model(String ip_address, String[] fields, CachingRepository cachedMappings) {
        this.ip_address = ip_address;
        IPResult ipResult = cachedMappings.getIPResult(ip_address);

        if (fields[0].equals("all")) {
            fields = ALL_FIELDS;
        }

        for (String field : fields) {
            switch (field) {
                case "country_short":
                    this.setCountry_short(ipResult.getCountryShort());
                    break;
                case "country_long":
                    this.setCountry_long(ipResult.getCountryLong());
                    break;
                case "region":
                    this.setRegion(ipResult.getRegion());
                    break;
                case "city":
                    this.setCity(ipResult.getCity());
                    break;
                case "isp":
                    this.setIsp(ipResult.getISP());
                    break;
                case "latitude":
                    float latitude = ipResult.getLatitude();
                    this.setLatitude(latitude);
                    break;
                case "longitude":
                    float longitude = ipResult.getLongitude();
                    this.setLongitude(longitude);
                    break;
                case "domain":
                    this.setDomain(ipResult.getDomain());
                    break;
                case "zipcode":
                    this.setZipcode(ipResult.getZipCode());
                    break;
                case "netspeed":
                    this.setNetspeed(ipResult.getNetSpeed());
                    break;
                case "timezone":
                    this.setTimezone(ipResult.getTimeZone());
                    break;
                case "iddcode":
                    this.setIddcode(ipResult.getIDDCode());
                    break;
                case "areacode":
                    this.setAreacode(ipResult.getAreaCode());
                    break;
                case "weatherstationcode":
                    this.setWeatherstationcode(ipResult.getWeatherStationCode());
                    break;
                case "weatherstationname":
                    this.setWeatherstationname(ipResult.getWeatherStationName());
                    break;
                case "mcc":
                    this.setMcc(ipResult.getMCC());
                    break;
                case "mnc":
                    this.setMnc(ipResult.getMNC());
                    break;
                case "mobilebrand":
                    this.setMobilebrand(ipResult.getMobileBrand());
                    break;
                case "elevation":
                    float elevation = ipResult.getElevation();
                    this.setElevation(elevation);
                    break;
                case "usagetype":
                    this.setUsagetype(ipResult.getUsageType());
                    break;
                case "status":
                    this.setStatus(ipResult.getStatus());
                    break;
            }
        }
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getElevation() {
        return elevation;
    }

    public void setElevation(Float elevation) {
        this.elevation = elevation;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getCountry_short() {
        return country_short;
    }

    public void setCountry_short(String country_short) {
        this.country_short = country_short;
    }

    public String getCountry_long() {
        return country_long;
    }

    public void setCountry_long(String country_long) {
        this.country_long = country_long;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }


    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getNetspeed() {
        return netspeed;
    }

    public void setNetspeed(String netspeed) {
        this.netspeed = netspeed;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getIddcode() {
        return iddcode;
    }

    public void setIddcode(String iddcode) {
        this.iddcode = iddcode;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getWeatherstationcode() {
        return weatherstationcode;
    }

    public void setWeatherstationcode(String weatherstationcode) {
        this.weatherstationcode = weatherstationcode;
    }

    public String getWeatherstationname() {
        return weatherstationname;
    }

    public void setWeatherstationname(String weatherstationname) {
        this.weatherstationname = weatherstationname;
    }

    public String getMcc() {
        return mcc;
    }

    public void setMcc(String mcc) {
        this.mcc = mcc;
    }

    public String getMnc() {
        return mnc;
    }

    public void setMnc(String mnc) {
        this.mnc = mnc;
    }

    public String getMobilebrand() {
        return mobilebrand;
    }

    public void setMobilebrand(String mobilebrand) {
        this.mobilebrand = mobilebrand;
    }


    public String getUsagetype() {
        return usagetype;
    }

    public void setUsagetype(String usagetype) {
        this.usagetype = usagetype;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
