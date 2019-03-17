package bell.usipov.broker.dbmodule.service;

import bell.usipov.broker.dbmodule.dao.DaoWeather;
import bell.usipov.broker.dbmodule.model.Location;
import bell.usipov.broker.dbmodule.model.Weather;
import bell.usipov.broker.dtomodule.model.DtoLocation;
import bell.usipov.broker.dtomodule.model.DtoWeather;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Тестирование GetWeatherDataImpl
 */
@RunWith(MockitoJUnitRunner.class)
public class GetWeatherDataTest {

    @Mock
    private DaoWeather daoWeather;

    @Mock
    private MapperFactory mapperFactory;

    @Spy
    @InjectMocks
    private GetWeatherDataImpl remoteProxy;

    /**
     * Проверка объектов теста на null
     */
    @Test
    public void checkNull() {
        assertNotNull(daoWeather);
        assertNotNull(mapperFactory);
        assertNotNull(remoteProxy);
    }

    /**
     * Тестирование получения погоды
     */
    @Test
    public void getWeather() {
        Weather weather = mock(Weather.class);
        String location = "Moscow";
        DtoWeather dtoWeather = mock(DtoWeather.class);
        MapperFacade mapperFacade = mock(MapperFacade.class);

        when(daoWeather.get(location)).thenReturn(weather);
        when(mapperFactory.getMapperFacade()).thenReturn(mapperFacade);
        when(mapperFacade.map(weather, DtoWeather.class)).thenReturn(dtoWeather);

        DtoWeather weatherDto1 = remoteProxy.getWeather(location);

        verify(daoWeather).get(location);
        verify(mapperFactory).getMapperFacade();
        verify(mapperFacade).map(weather, DtoWeather.class);

        assertEquals(dtoWeather, weatherDto1);
    }

    /**
     * Тестирование при null параметре
     */
    @Test
    public void nullTest() {
        String nullLocation = null;

        DtoWeather dtoWeather = remoteProxy.getWeather(nullLocation);

        assertEquals(dtoWeather, new DtoWeather());
    }

    /**
     * Тестирование при пустом параметре
     */
    @Test
    public void emptyTest() {
        String emptyLocation = "";

        DtoWeather dtoWeather = remoteProxy.getWeather(emptyLocation);

        assertEquals(dtoWeather, new DtoWeather());
    }

    /**
     * Тестирование получения списка доступных городов
     */
    @Test
    public void listTest() {
        List<Location> list = mock(List.class);
        List<DtoLocation> dtoList = mock(List.class);
        MapperFacade mapperFacade = mock(MapperFacade.class);

        when(daoWeather.getLocationList()).thenReturn(list);
        when(mapperFactory.getMapperFacade()).thenReturn(mapperFacade);
        when(mapperFacade.mapAsList(list, DtoLocation.class)).thenReturn(dtoList);

        List<DtoLocation> targetList = remoteProxy.list();

        verify(daoWeather, times(1)).getLocationList();
        verify(mapperFactory, times(1)).getMapperFacade();
        verify(mapperFacade, times(1)).mapAsList(list, DtoLocation.class);

        assertEquals(targetList, dtoList);
    }
}
