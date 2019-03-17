package bell.usipov.broker.weatherservice.controller;

import bell.usipov.broker.weatherservice.view.View;
import bell.usipov.broker.dtomodule.model.DtoWeather;

import java.util.List;

/**
 * Интерфейс контроллера получения погоды
 */
public interface WeatherController {
    /**
     * Получение данных о погоде города
     *
     * @param location - город
     * @return объект с информацией о погоде
     */
    DtoWeather getWeather(String location);

    /**
     * Получение списка горов
     *
     * @return List список с названиями городов
     */
    List<View> list();
}

