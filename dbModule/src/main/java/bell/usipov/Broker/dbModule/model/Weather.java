package bell.usipov.broker.dbmodule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Модель погоды по шаблону ответа Yahoo WeatherDto
 */
@Entity
@Table(name = "weather")
public class Weather implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @Version
    private Integer version;

    @JsonIgnore
    @Column(name = "location", length = 50)
    private String cityName;

    /**
     * Город
     */
    @OneToOne(mappedBy = "weather", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Location location;

    /**
     * Текущая погода
     */
    @JsonProperty("current_observation")
    @OneToOne(mappedBy = "weather", cascade = CascadeType.ALL)
    private Observation currentObservation;

    /**
     * Прогноз на неделю
     */
    @OneToMany(mappedBy = "weather", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Forecast> forecasts;

    public Weather() {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Observation getCurrentObservation() {
        return currentObservation;
    }

    public void setCurrentObservation(Observation currentObservation) {
        this.currentObservation = currentObservation;
    }

    public List<Forecast> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<Forecast> forecasts) {
        this.forecasts = forecasts;
    }

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public String getCityName() {
        return cityName;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "WeatherDto{" +
                "location=" + location +
                ", currentObservation=" + currentObservation +
                ", forecasts=" + forecasts +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weather weather = (Weather) o;
        return Objects.equals(location, weather.location) &&
                Objects.equals(currentObservation, weather.currentObservation) &&
                Objects.equals(forecasts, weather.forecasts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location, currentObservation, forecasts);
    }
}
