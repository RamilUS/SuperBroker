package bell.usipov.Broker.dbModule.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Ветер
 */
@Entity
@Table(name = "wind")
public class Wind implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @Version
    private Integer version;




    /**
     * Сила ветра
     */
    @Column(name = "chill")
    private Integer chill;

    /**
     * Направление
     */
    @Column(name = "direction")
    private Integer direction;

    /**
     * Скорость
     */
    @Column(name = "speed")
    private Double speed;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "obs_id", referencedColumnName = "id")
    private Observation observation;

    public Wind() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

    public Observation getObservation() {
        return observation;
    }

    public Integer getChill() {
        return chill;
    }

    public void setChill(Integer chill) {
        this.chill = chill;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "WindDto{" +
                "chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wind wind = (Wind) o;
        return Objects.equals(id, wind.id) &&
                Objects.equals(version, wind.version) &&
                Objects.equals(chill, wind.chill) &&
                Objects.equals(direction, wind.direction) &&
                Objects.equals(speed, wind.speed) &&
                Objects.equals(observation, wind.observation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, chill, direction, speed, observation);
    }
}
