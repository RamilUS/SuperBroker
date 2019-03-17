package bell.yusipov.broker.yahoo.service;

import bell.usipov.broker.dtomodule.model.DtoWeather;

/**
 * Сервис модуля
 */
public interface YahooService {

    /**
     * Построение запроса в YAHOO
     *
     * @param location - название города
     */
    void request(String location);

    /**
     * Конвектирование json строки в java-объект
     */
    DtoWeather jsonToObject(String jsonStr);

    /**
     * Отправка погоды в dbModule
     */
    void sendWeather(DtoWeather weather);
}
