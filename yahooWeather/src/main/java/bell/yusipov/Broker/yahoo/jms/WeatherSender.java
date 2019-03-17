package bell.yusipov.broker.yahoo.jms;


import bell.usipov.broker.dtomodule.model.DtoWeather;

/**
 * Интерфейс отправки погоды в jms очередь
 */
public interface WeatherSender {

    /**
     * Отправка сообщения jms
     *
     * @param weather -объект сообщения
     */

    void send(DtoWeather weather);
}
