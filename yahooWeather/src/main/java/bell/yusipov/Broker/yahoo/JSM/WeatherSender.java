package bell.yusipov.Broker.yahoo.JSM;


import bell.usipov.Broker.dtoModule.model.DtoWeather;

/**
 * Интерфейс отправки погоды в JMS очередь
 */
public interface WeatherSender {

    /**
     * Отправка сообщения JMS
     * @param weather -объект сообщения
     */

    void send (DtoWeather weather);
}
