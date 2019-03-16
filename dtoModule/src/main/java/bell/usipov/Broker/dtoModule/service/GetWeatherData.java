package bell.usipov.Broker.dtoModule.service;

import bell.usipov.Broker.dtoModule.model.DtoLocation;
import bell.usipov.Broker.dtoModule.model.DtoWeather;

import java.util.List;

/**
 * Интерфейс получения данных о погоде
 */
public interface GetWeatherData {
    /**
     * Получение данных о погоде по названию города
     * @param location - название города
     * @return - объект с данными о погоде
     */
    DtoWeather getWeather(String location);

    /**
     * Получение списка доступных городов
     * @return - List<LocationDto>
     */
    List<DtoLocation> list();
}

