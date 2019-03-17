package bell.usipov.broker.dbmodule.service;

import bell.usipov.broker.dbmodule.dao.DaoWeather;
import bell.usipov.broker.dbmodule.model.Location;
import bell.usipov.broker.dbmodule.model.Weather;
import bell.usipov.broker.dtomodule.model.*;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Тестирование сервиса
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTest {
    @Mock
    private DaoWeather daoWeather;

    @Mock
    private MapperFacade mapperFacade;

    @Spy
    @InjectMocks
    private WeatherServiceImpl weatherService;

    private Weather weather;
    private DtoWeather dtoWeather;
    private DtoLocation dtoLocation;
    private DtoObservation dtoObservation;
    private DtoAstronomy dtoAstronomy;
    private DtoAtmosphere dtoAtmosphere;
    private DtoWind dtoWind;
    private DtoForecast dtoForecast;
    private DtoCondition dtoCondition;
    private List list;
    private Iterator iterator;

    @Before
    public void set() {
        weather = mock(Weather.class);
        dtoWeather = mock(DtoWeather.class);
        dtoLocation = mock(DtoLocation.class);
        dtoObservation = mock(DtoObservation.class);
        dtoAstronomy = mock(DtoAstronomy.class);
        dtoAtmosphere = mock(DtoAtmosphere.class);
        dtoWind = mock(DtoWind.class);
        dtoForecast = mock(DtoForecast.class);
        dtoCondition = mock(DtoCondition.class);
        list = mock(List.class);
        iterator = mock(Iterator.class);


        when(dtoWeather.getLocation()).thenReturn(dtoLocation);
        when(dtoWeather.getCurrentObservation()).thenReturn(dtoObservation);
        when(dtoObservation.getAstronomy()).thenReturn(dtoAstronomy);
        when(dtoObservation.getAtmosphere()).thenReturn(dtoAtmosphere);
        when(dtoObservation.getWind()).thenReturn(dtoWind);
        when(dtoObservation.getCondition()).thenReturn(dtoCondition);
        when(dtoWeather.getForecasts()).thenReturn(list);
        when(list.iterator()).thenReturn(iterator);
        when(iterator.next()).thenReturn(dtoForecast);
        when(weather.getLocation()).thenReturn(mock(Location.class));

    }

    /**
     * Проверка на null
     */
    @Test
    public void checkNull() {
        assertNotNull(daoWeather);
        assertNotNull(mapperFacade);
        assertNotNull(weatherService);
    }

    /**
     * Тест сохранения null
     */
    @Test
    public void nullSaveTest() {
        DtoWeather dtoWeather = null;

        weatherService.save(dtoWeather);

        verifyZeroInteractions(daoWeather);
    }

    /**
     * Тест обновления при null
     */
    @Test
    public void nullUpdateTest() {
        DtoWeather dtoWeather = null;

        weatherService.update(dtoWeather);

        verifyZeroInteractions(daoWeather);
    }

    /**
     * Проверка получения списка доступных городов
     */
    @Test
    public void listTest() {
        List<Location> list = mock(List.class);

        when(daoWeather.getLocationList()).thenReturn(list);

        assertEquals(list, weatherService.locationList());
        verify(daoWeather, times(1)).getLocationList();
    }
}
