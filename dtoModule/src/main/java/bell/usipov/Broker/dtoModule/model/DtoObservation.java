package bell.usipov.Broker.dtoModule.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Текущая погода
 */
public class DtoObservation implements Serializable {

    public DtoObservation() {

    }

    /**
     * Время наблюдения
     */
    private Long pubDate;

    /**
     * Ветер
     */
    private DtoWind wind;

    /**
     * Атмосфера
     */
    private DtoAtmosphere atmosphere;

    /**
     * Астрономия
     */
    private DtoAstronomy astronomy;

    /**
     * Погодные условия
     */
    private DtoCondition condition;

    public Long getPubDate() {
        return pubDate;
    }

    public void setPubDate(Long pubDate) {
        this.pubDate = pubDate;
    }

    public DtoWind getWind() {
        return wind;
    }

    public void setWind(DtoWind wind) {
        this.wind = wind;
    }

    public DtoAtmosphere getAtmosphere() {
        return atmosphere;
    }

    public void setAtmosphere(DtoAtmosphere atmosphere) {
        this.atmosphere = atmosphere;
    }

    public DtoAstronomy getAstronomy() {
        return astronomy;
    }

    public void setAstronomy(DtoAstronomy astronomy) {
        this.astronomy = astronomy;
    }

    public DtoCondition getCondition() {
        return condition;
    }

    public void setCondition(DtoCondition condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DtoObservation)) return false;
        DtoObservation that = (DtoObservation) o;
        return Objects.equals(pubDate, that.pubDate) &&
                Objects.equals(wind, that.wind) &&
                Objects.equals(atmosphere, that.atmosphere) &&
                Objects.equals(astronomy, that.astronomy) &&
                Objects.equals(condition, that.condition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pubDate, wind, atmosphere, astronomy, condition);
    }

    @Override
    public String toString() {
        return "DtoObservation{" +
                "pubDate=" + pubDate +
                ", wind=" + wind +
                ", atmosphere=" + atmosphere +
                ", astronomy=" + astronomy +
                ", condition=" + condition +
                '}';
    }
}
