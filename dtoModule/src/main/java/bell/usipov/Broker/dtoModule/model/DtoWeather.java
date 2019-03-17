package bell.usipov.broker.dtomodule.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Модель погоды согластно структуре ответа Yahoo
 */
public class DtoWeather implements Serializable {

    /**
     * Широта
     */
    private DtoLocation location;

    /**
     * Текущая погода
     */
    @JsonProperty("current_observation")
    private DtoObservation currentObservation;

    /**
     * Прогноз на неделю
     */
    private List<DtoForecast> forecasts;

    public DtoWeather() {
    }

    public DtoLocation getLocation() {
        return location;
    }

    public void setLocation(DtoLocation location) {
        this.location = location;
    }

    public DtoObservation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(DtoObservation currentObservation) {
        this.currentObservation = currentObservation;
    }

    public List<DtoForecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<DtoForecast> forecasts) {
        this.forecasts = forecasts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DtoWeather)) return false;
        DtoWeather that = (DtoWeather) o;
        return Objects.equals(location, that.location) &&
                Objects.equals(currentObservation, that.currentObservation) &&
                Objects.equals(forecasts, that.forecasts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, currentObservation, forecasts);
    }

    @Override
    public String toString() {
        return "DtoWeather{" +
                "location=" + location +
                ", currentObservation=" + currentObservation +
                ", forecasts=" + forecasts +
                '}';
    }
}

