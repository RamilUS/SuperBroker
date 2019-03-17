package bell.usipov.broker.weatherservice.service;

import bell.usipov.broker.weatherservice.service.WeatherServiceImpl;
import bell.usipov.broker.dtomodule.model.DtoWeather;
import bell.usipov.broker.dtomodule.service.GetWeatherData;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {

    @Mock
    private GetWeatherData getWeatherData;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    /**
     * Тестирование получения погоды по называнию города
     */
    @Test
    public void getMethodTest(){

        String location = "Moscow";

        when(getWeatherData.getWeather(location)).thenReturn(new DtoWeather());

        weatherService.getWeather(location);

        verify(getWeatherData, times(1)).getWeather(location);
        Assert.assertNotNull(weatherService.getWeather(location));
    }

    /**
     * Тестирование при null параметре
     */
    @Test
    public void nullGetTest(){
        String nullLocation = null;

        assertEquals(weatherService.getWeather(nullLocation), new DtoWeather());
        verifyZeroInteractions(getWeatherData);
    }

    /**
     * Тестирование при пустом параметре
     */
    @Test
    public void emptyTest(){
        String emptyLocation = "";

        assertEquals(weatherService.getWeather(emptyLocation), new DtoWeather());
        verifyZeroInteractions(getWeatherData);
    }


}
