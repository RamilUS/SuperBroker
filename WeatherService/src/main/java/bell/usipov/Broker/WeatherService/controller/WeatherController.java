package bell.usipov.Broker.WeatherService.controller;

import bell.usipov.Broker.WeatherService.view.View;
import bell.usipov.Broker.dtoModule.DtoWeather;

import java.util.List;

/**
 * Интерфейс контроллера получения погоды
 */
public interface WeatherController {
    /**
     * Получение данных о погоде города
     * @param location - город
     * @return объект с информацией о погоде
     */
    DtoWeather getWeather(String location);

    /**
     * Получение списка горов
     * @return List список с названиями городов
     */
    List<View> list();
}

