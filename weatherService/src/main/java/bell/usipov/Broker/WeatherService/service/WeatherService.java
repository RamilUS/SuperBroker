package bell.usipov.broker.weatherservice.service;


import bell.usipov.broker.weatherservice.view.View;
import bell.usipov.broker.dtomodule.model.DtoWeather;

import java.util.List;

/**
 * Сервис получения данных о погоде
 */
public interface WeatherService {


    /**
     * Получение погоды по названию города
     *
     * @param location название города
     * @return объект с данными о погоде
     */
    DtoWeather getWeather(String location);

    /**
     * Получение списка доступных городов
     *
     * @return List список названий городов
     */
    List<View> list();

}

