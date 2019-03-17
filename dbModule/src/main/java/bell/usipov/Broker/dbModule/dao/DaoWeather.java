package bell.usipov.broker.dbmodule.dao;

import bell.usipov.broker.dbmodule.model.Location;
import bell.usipov.broker.dbmodule.model.Weather;

import java.util.List;

/**
 * Dao для работы с базой данных
 */
public interface DaoWeather {

    /**
     * Сохранение новых данных в базу данных
     * @param weather - объект с данными о погоде
     */
    void save(Weather weather);

    /**
     * Обновение данных о погоде
     * @param weather - объект с данными о погоде
     */
    void update(Weather weather);

    /**
     * Получение информации о погоде по названию города
     * @param location - название города
     * @return DtoWeather объект с инфрмацией о погоде
     */
    Weather get(String location);

    /**
     * Получение списка городов в базе данных
     * @return Список доступных городов
     */
    List<Location> getLocationList();
}
