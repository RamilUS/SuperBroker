package bell.usipov.Broker.dbModule.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
/**
 * Условия
 */
@Entity
@Table(name = "condition")
public class Condition implements Serializable {

        @JsonIgnore
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @JsonIgnore
        @Version
        private Integer version;



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

        /**
         * Температура
         */
        @Column(name = "temperature")
        private Integer temperature;

        @JsonIgnore
        @OneToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "obs_id")
        private Observation observation;

         public Condition() {

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

        public Integer getTemperature() {
        return temperature;
    }

        public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

        @Override
        public String toString() {
        return "ConditionDto{" +
                "text='" + text + '\'' +
                ", code=" + code +
                ", temperature=" + temperature +
                '}';
    }

        @Override
        public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Condition condition = (Condition) o;
        return Objects.equals(id, condition.id) &&
                Objects.equals(version, condition.version) &&
                Objects.equals(text, condition.text) &&
                Objects.equals(code, condition.code) &&
                Objects.equals(temperature, condition.temperature) &&
                Objects.equals(observation, condition.observation);
    }

        @Override
        public int hashCode() {
        return Objects.hash(id, version, text, code, temperature, observation);
    }
    }