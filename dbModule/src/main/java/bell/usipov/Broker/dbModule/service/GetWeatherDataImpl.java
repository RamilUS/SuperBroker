package bell.usipov.broker.dbmodule.service;

import bell.usipov.broker.dbmodule.dao.DaoWeather;
import bell.usipov.broker.dbmodule.model.Location;
import bell.usipov.broker.dbmodule.model.Weather;
import bell.usipov.broker.dtomodule.model.DtoLocation;
import bell.usipov.broker.dtomodule.model.DtoWeather;
import bell.usipov.broker.dtomodule.service.GetWeatherData;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class GetWeatherDataImpl implements GetWeatherData {
    private final DaoWeather daoWeather;

    private final MapperFactory mapperFactory;

    @Autowired
    public GetWeatherDataImpl(DaoWeather daoWeather, MapperFactory mapperFactory) {
        this.daoWeather = daoWeather;
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DtoWeather getWeather(String location) {

        if (location == null || location.isEmpty()) {
            return new DtoWeather();
        }

        Weather weather = daoWeather.get(location);

        try {
            return mapperFactory.getMapperFacade().map(weather, DtoWeather.class);
        } catch (Exception e) {
            throw new RuntimeException("Mapper Error");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DtoLocation> list() {

        List<Location> locations = daoWeather.getLocationList();
        return mapperFactory.getMapperFacade().mapAsList(locations, DtoLocation.class);
    }
}
