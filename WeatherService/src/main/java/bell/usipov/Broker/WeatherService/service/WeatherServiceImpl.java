package bell.usipov.Broker.WeatherService.service;

import bell.usipov.Broker.WeatherService.view.View;
import bell.usipov.Broker.dtoModule.DtoLocation;
import bell.usipov.Broker.dtoModule.DtoWeather;
import bell.usipov.Broker.dtoModule.GetWeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService{
    private final GetWeatherData getWeatherData;

    @Autowired
    public WeatherServiceImpl(GetWeatherData getWeatherData) {
        this.getWeatherData = getWeatherData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DtoWeather getWeather(String location) {

        if(location == null || location.isEmpty()){
            return new DtoWeather();
        }
        return getWeatherData.getWeather(location);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<View> list() {

        List<DtoLocation> dtoList = getWeatherData.list();
        List<View> views = new ArrayList<>();

        for (DtoLocation dto : dtoList){
            views.add(new View(dto.getCity()));
        }

        return views;
    }

}
