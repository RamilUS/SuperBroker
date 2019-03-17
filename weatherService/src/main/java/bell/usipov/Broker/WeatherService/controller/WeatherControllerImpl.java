package bell.usipov.broker.weatherservice.controller;

import bell.usipov.broker.weatherservice.view.View;
import bell.usipov.broker.weatherservice.service.WeatherService;
import bell.usipov.broker.dtomodule.model.DtoWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * {@inheritDoc}
 */
@RestController
@RequestMapping(produces = APPLICATION_JSON_VALUE)
public class WeatherControllerImpl implements WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherControllerImpl(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * {@inheritDoc}
     */
    @RequestMapping(value = "/{location}", method = {GET})
    public DtoWeather getWeather(@PathVariable("location") String location) {

        View view = new View(location);

        if (!list().contains(view)) {
            throw new RuntimeException("This no forecast for this location");
        } else {
            return weatherService.getWeather(location);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(value = "/locations", method = {GET})
    public List<View> list() {
        return weatherService.list();
    }
}
