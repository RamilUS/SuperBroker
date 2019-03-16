package bell.usipov.Broker.dbModule.service;

import bell.usipov.Broker.dbModule.model.Location;
import bell.usipov.Broker.dtoModule.model.DtoWeather;

import java.util.List;

/**
 * Интерфейс модуля базы данных
 */
public interface WeatherService {

    /**
     * Добавить новую информацию о погоде
     * @param weather - объект с информацией
     */
    void save(DtoWeather weather);

    /**
     * Обновить информацию о погоде
     * @param weather - объект с новой информацией о погоде
     */
    void update(DtoWeather weather);

    /**
     * Получение списка городов, имеющих информацию о погоде
     * @return список Location
     */
    List<Location> locationList();
}

