package bell.usipov.Broker.dtoModule.model;

import java.io.Serializable;
import java.util.Objects;
/**
 * Ветер
 */
public class DtoWind implements Serializable {

    public DtoWind(){

    }

    /**
     *
     */
    private Integer chill;

    /**
     * Направление
     */
    private Integer direction;

    /**
     * Скорость
     */
    private Double speed;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DtoWind)) return false;
        DtoWind dtoWind = (DtoWind) o;
        return Objects.equals(chill, dtoWind.chill) &&
                Objects.equals(direction, dtoWind.direction) &&
                Objects.equals(speed, dtoWind.speed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chill, direction, speed);
    }

    @Override
    public String toString() {
        return "DtoWind{" +
                "chill=" + chill +
                ", direction=" + direction +
                ", speed=" + speed +
                '}';
    }
}
