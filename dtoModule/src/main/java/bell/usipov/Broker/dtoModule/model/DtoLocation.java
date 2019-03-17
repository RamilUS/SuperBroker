package bell.usipov.broker.dtomodule.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Objects;

/**
 * Модель города
 */
public class DtoLocation implements Serializable {

    /**
     * Идентификатор
     */
    @JsonProperty("woeid")
    private Long woeId;

    /**
     * Название города
     */
    private String city;

    /**
     * Регион
     */
    private String region;

    /**
     * Страна
     */
    private String country;

    /**
     * Широта
     */
    private Double lat;

    /**
     * Долгота
     */
    @JsonProperty("long")

    private Double lon;

    /**
     * Часовой пояс
     */
    @JsonProperty("timezone_id")

    private String timezoneId;

    public DtoLocation() {

    }


    public Long getWoeId() {
        return woeId;
    }

    public void setWoeId(Long woeId) {
        this.woeId = woeId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getTimezoneId() {
        return timezoneId;
    }

    public void setTimezoneId(String timezoneId) {
        this.timezoneId = timezoneId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DtoLocation)) return false;
        DtoLocation that = (DtoLocation) o;
        return Objects.equals(woeId, that.woeId) &&
                Objects.equals(city, that.city) &&
                Objects.equals(region, that.region) &&
                Objects.equals(country, that.country) &&
                Objects.equals(lat, that.lat) &&
                Objects.equals(lon, that.lon) &&
                Objects.equals(timezoneId, that.timezoneId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(woeId, city, region, country, lat, lon, timezoneId);
    }

    @Override
    public String toString() {
        return "DtoLocation{" +
                "woeId=" + woeId +
                ", city='" + city + '\'' +
                ", region='" + region + '\'' +
                ", country='" + country + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", timezoneId='" + timezoneId + '\'' +
                '}';
    }
}
