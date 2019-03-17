package bell.usipov.broker.dtomodule.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Астрономия
 */
public class DtoAstronomy implements Serializable {
    /**
     * Рассвет
     */
    private String sunrise;

    /**
     * Закат
     */
    private String sunset;

    public DtoAstronomy() {
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DtoAstronomy)) return false;
        DtoAstronomy that = (DtoAstronomy) o;
        return Objects.equals(sunrise, that.sunrise) &&
                Objects.equals(sunset, that.sunset);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sunrise, sunset);
    }

    @Override
    public String toString() {
        return "DtoAstronomy{" +
                "sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }
}
