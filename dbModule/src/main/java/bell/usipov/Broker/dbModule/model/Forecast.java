package bell.usipov.broker.dbmodule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Прогноз
 */
@Entity
@Table(name = "forecast")
public class Forecast implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonIgnore
    @Version
    private Integer version;

    /**
     * День недели
     */
    @Column(name = "day", length = 10)
    private String day;

    /**
     * Дата
     */
    @Column(name = "date")
    private Long date;

    /**
     * Макс. температура
     */
    @Column(name = "low")
    private Integer low;

    /**
     * Мин. температура
     */
    @Column(name = "high")
    private Integer high;

    /**
     * Описание
     */
    @Column(name = "text", length = 50)
    private String text;

    /**
     * Код
     */
    @Column(name = "code")
    private Integer code;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "weather_id")
    private Weather weather;


    public Forecast() {
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Integer getLow() {
        return low;
    }

    public void setLow(Integer low) {
        this.low = low;
    }

    public Integer getHigh() {
        return high;
    }

    public void setHigh(Integer high) {
        this.high = high;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ForecastDto{" +
                "day='" + day + '\'' +
                ", date=" + date +
                ", low=" + low +
                ", high=" + high +
                ", text='" + text + '\'' +
                ", code=" + code +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forecast forecast = (Forecast) o;
        return Objects.equals(id, forecast.id) &&
                Objects.equals(version, forecast.version) &&
                Objects.equals(day, forecast.day) &&
                Objects.equals(date, forecast.date) &&
                Objects.equals(low, forecast.low) &&
                Objects.equals(high, forecast.high) &&
                Objects.equals(text, forecast.text) &&
                Objects.equals(code, forecast.code) &&
                Objects.equals(weather, forecast.weather);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, version, day, date, low, high, text, code, weather);
    }
}
