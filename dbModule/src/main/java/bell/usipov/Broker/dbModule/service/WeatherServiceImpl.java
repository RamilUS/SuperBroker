package bell.usipov.broker.dbmodule.service;

import bell.usipov.broker.dbmodule.dao.DaoWeather;
import bell.usipov.broker.dbmodule.maping.Mapper;
import bell.usipov.broker.dbmodule.model.Location;
import bell.usipov.broker.dbmodule.model.Weather;
import bell.usipov.broker.dtomodule.model.DtoWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@inheritDoc}
 */
import java.util.List;
@Service
public class WeatherServiceImpl implements  WeatherService {

    private final DaoWeather daoWeather;
    private final Mapper mapper;

    @Autowired
    public WeatherServiceImpl(DaoWeather daoWeather, Mapper mapper){
        this.daoWeather = daoWeather;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(DtoWeather dtoWeather) {

        if(checkNull(dtoWeather)){

            return;
        }

        Weather weather = mapper.map(dtoWeather, Weather.class);
        Location location = weather.getLocation();
        List<Location> locations = locationList();

        if(locations.contains(location)){

            update(dtoWeather);
        }else {
                daoWeather.save(weather);

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(DtoWeather dtoWeather) {

        if(checkNull(dtoWeather)){
            return;
        }

        Weather weather = daoWeather.get(dtoWeather.getLocation().getCity());
        mapper.map(dtoWeather, weather);
        daoWeather.update(weather);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> locationList() {

        return daoWeather.getLocationList();
    }

    /**
     * Проверка null значений в переданном объекте
     * @param weather - проверяемый объект
     * @return true, если найдено null значение, иначе - false
     */
    private boolean checkNull(DtoWeather weather){

        return weather == null || weather.getCurrentObservation() == null
                || weather.getCurrentObservation().getCondition() == null
                || weather.getCurrentObservation().getAtmosphere() == null
                || weather.getCurrentObservation().getAstronomy() == null
                || weather.getCurrentObservation().getWind() == null
                || weather.getForecasts() == null;
    }
}
