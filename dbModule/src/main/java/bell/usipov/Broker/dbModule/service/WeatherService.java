package bell.usipov.broker.dbmodule.service;

import bell.usipov.broker.dbmodule.model.Location;
import bell.usipov.broker.dtomodule.model.DtoWeather;

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

