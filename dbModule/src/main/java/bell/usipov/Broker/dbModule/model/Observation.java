package bell.usipov.Broker.dbModule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
/**
 * Текущая погода
 */
@Entity
@Table(name = "current_observation")
public class Observation implements Serializable {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @Version
    private Integer version;



    /**
     * Время наблюдения
     */
    @Column(name = "pubDate")
    private Long pubDate;

    /**
     * Ветер
     */
    @OneToOne(mappedBy = "observation", cascade = CascadeType.PERSIST)
    private Wind wind;

    /**
     * Атмосфера
     */
    @OneToOne(mappedBy = "observation", cascade = CascadeType.ALL)
    private Atmosphere atmosphere;

    /**
     * Астрономия
     */
    @OneToOne(mappedBy = "observation", cascade = CascadeType.ALL)
    private Astronomy astronomy;

    /**
     * Погодные условия
     */
    @OneToOne(mappedBy = "observation", cascade = CascadeType.ALL)
    private Condition condition;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_id")
    private Weather weather;


    public Observation() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getVersion() {
        return version;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Atmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(Atmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public Astronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(Astronomy astronomy) {
        this.astronomy = astronomy;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Long getPubDate() {
        return pubDate;
    }

    public void setPubDate(Long pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "ObservationDto{" +
                "wind=" + wind +
                ", atmosphere=" + atmosphere +
                ", astronomy=" + astronomy +
                ", condition=" + condition +
                ", pubDate=" + pubDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Observation that = (Observation) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(version, that.version) &&
                Objects.equals(pubDate, that.pubDate) &&
                Objects.equals(wind, that.wind) &&
                Objects.equals(atmosphere, that.atmosphere) &&
                Objects.equals(astronomy, that.astronomy) &&
                Objects.equals(condition, that.condition) &&
                Objects.equals(weather, that.weather);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, pubDate, wind, atmosphere, astronomy, condition, weather);
    }
}
