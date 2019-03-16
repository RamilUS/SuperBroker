package bell.usipov.Broker.dbModule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Атмосфера
 */
@Entity
@Table(name = "atmosphere")
public class Atmosphere implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @Version
    private Integer version;



    /**
     * Влажность
     */
    @Column(name = "humidity")
    private Integer humidity;

    /**
     * Видимость
     */
    @Column(name = "visibility")
    private Integer visibility;

    /**
     * Давление
     */
    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "rising")
    private Integer rising;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "obs_id")
    private Observation observation;


    public Atmosphere() {
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

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Integer getRising() {
        return rising;
    }

    public void setRising(Integer rising) {
        this.rising = rising;
    }

    @Override
    public String toString() {
        return "AtmosphereDto{" +
                "humidity=" + humidity +
                ", visibility=" + visibility +
                ", pressure=" + pressure +
                ", rising=" + rising +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atmosphere that = (Atmosphere) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(version, that.version) &&
                Objects.equals(humidity, that.humidity) &&
                Objects.equals(visibility, that.visibility) &&
                Objects.equals(pressure, that.pressure) &&
                Objects.equals(rising, that.rising) &&
                Objects.equals(observation, that.observation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, humidity, visibility, pressure, rising, observation);
    }
}