package bell.usipov.broker.dbmodule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Астрономия
 */
@Entity
@Table(name = "astronomy")
public class Astronomy implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @Version
    private Integer version;


    /**
     * Рассвет
     */
    @Column(name = "sunrise")
    private String sunrise;

    /**
     * Закат
     */
    @Column(name = "sunset")
    private String sunset;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "obs_id")
    private Observation observation;


    public Astronomy() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public Observation getObservation() {
        return observation;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
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
    public String toString() {
        return "Astronomy{" +
                "sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Astronomy astronomy = (Astronomy) o;
        return Objects.equals(id, astronomy.id) &&
                Objects.equals(version, astronomy.version) &&
                Objects.equals(sunrise, astronomy.sunrise) &&
                Objects.equals(sunset, astronomy.sunset) &&
                Objects.equals(observation, astronomy.observation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, sunrise, sunset, observation);
    }
}