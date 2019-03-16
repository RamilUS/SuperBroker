package bell.usipov.Broker.WeatherService.controller;

import bell.usipov.Broker.WeatherService.view.View;
import bell.usipov.Broker.WeatherService.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherControllerImpl weatherController;

    /**
     * Тестирование получение данных
     */
    @Test
    public void getTest() {

        String location = "Berlin";

        View view = new View("Berlin");
        List<View> list = new ArrayList<>();
        list.add(view);

        when(weatherService.list()).thenReturn(list);

        weatherController.getWeather(location);

        verify(weatherService, times(1)).getWeather(location);
    }


    /**
     * Тестирование отсутствия информации
     */
    @Test
    public void noneForecastTest() {

        String location = "Berlin";

        View view = new View(location);
        List<View> list = new ArrayList<>();

        when(weatherService.list()).thenReturn(list);

        try {
            weatherController.getWeather(location);
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "This no forecast for this location");
        }
    }

    /**
     * Тестирование получение списка доступных городов
     */
    @Test
    public void listTest() {
        List list = mock(List.class);

        when(weatherService.list()).thenReturn(list);

        assertEquals(weatherController.list(), list);
        verify(weatherService, times(1)).list();
    }
}
