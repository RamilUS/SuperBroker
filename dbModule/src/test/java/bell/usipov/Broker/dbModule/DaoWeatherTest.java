package bell.usipov.broker.dbmodule;

import bell.usipov.broker.dbmodule.dao.DaoWeatherImpl;

import bell.usipov.broker.dbmodule.model.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class DaoWeatherTest {
    @Mock
    private EntityManager entityManager;

    @Spy
    @InjectMocks
    private DaoWeatherImpl DaoWeather;

    private Weather weather;
    private Location location;
    private Observation observation;
    private Astronomy astronomy;
    private Atmosphere atmosphere;
    private Wind wind;
    private Condition condition;

    @Before
    public void set(){
        weather = mock(Weather.class);
        observation = mock(Observation.class);
        astronomy = mock(Astronomy.class);
        atmosphere = mock(Atmosphere.class);
        wind = mock(Wind.class);
        condition = mock(Condition.class);
        location = mock(Location.class);
        List list = mock(List.class);
        Iterator iterator = mock(Iterator.class);
        Forecast forecast = mock(Forecast.class);

        when(weather.getLocation()).thenReturn(location);
        when(weather.getCurrentObservation()).thenReturn(observation);
        when(observation.getAstronomy()).thenReturn(astronomy);
        when(observation.getAtmosphere()).thenReturn(atmosphere);
        when(observation.getWind()).thenReturn(wind);
        when(observation.getCondition()).thenReturn(condition);
        when(weather.getForecasts()).thenReturn(list);
        when(list.iterator()).thenReturn(iterator);
        when(iterator.next()).thenReturn(forecast);

    }

    /**
     * Проверка инъекций
     */
    @Test
    public void checkNull(){
        assertNotNull(entityManager);
        assertNotNull(DaoWeather);
    }

    /**
     * Тестирование метода сохранения данных в БД
     */
    @Test
    public void saveTest(){

        DaoWeather.save(weather);

        verify(location, times(1)).setWeather(weather);
        verify(observation).setWeather(weather);
        verify(astronomy).setObservation(observation);
        verify(atmosphere).setObservation(observation);
        verify(wind).setObservation(observation);
        verify(condition).setObservation(observation);
        verify(entityManager, times(1)).persist(weather);
    }

    /**
     * Тестирование добавления при null параметре
     */
    @Test
    public void nullSaveTest(){
        Weather weather = null;

        DaoWeather.save(weather);

        verifyZeroInteractions(entityManager);
    }

    /**
     * Тестирование обновления данных
     */
    @Test
    public void updateTest(){

        DaoWeather.update(weather);

        verify(entityManager, times(1)).merge(weather);
    }

    /**
     * Тестирование обновления при null параметре
     */
    @Test
    public void nullUpdateTest(){
        Weather weather = null;

        DaoWeather.update(weather);

        verifyZeroInteractions(entityManager);
    }

    /**
     * Тестирование получение данных по нахванию города
     */
    @Test
    public void getTest(){
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery criteriaQuery = mock(CriteriaQuery.class);
        Root root = mock(Root.class);
        Weather weather = mock(Weather.class);
        TypedQuery query = mock(TypedQuery.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Weather.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Weather.class)).thenReturn(root);
        when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(weather);

        DaoWeather.get("Moscow");

        verify(entityManager, times(1)).getCriteriaBuilder();
        verify(criteriaBuilder, times(1)).createQuery(Weather.class);
        verify(criteriaQuery, times(1)).from(Weather.class);
        verify(entityManager, times(1)).createQuery(criteriaQuery);
        verify(query, times(1)).getSingleResult();
    }

    /**
     * Тестирование получения данных при null параметре
     */
    @Test
    public void nullGetTest(){
        String nullLocation = null;

        Weather weather = DaoWeather.get(nullLocation);

        assertEquals(weather, new Weather());
        verifyZeroInteractions(entityManager);
    }

    /**
     * Тестирование получения данных при пустом параметре
     */
    @Test
    public void emptyTest(){
        String emptyLocation = "";

        Weather weather = DaoWeather.get(emptyLocation);

        assertEquals(weather, new Weather());
        verifyZeroInteractions(entityManager);
    }

    /**
     * Тестирование получение списка доступных городов
     */
    @Test
    public void listTest(){
        CriteriaBuilder criteriaBuilder = mock(CriteriaBuilder.class);
        CriteriaQuery criteriaQuery = mock(CriteriaQuery.class);
        Root root = mock(Root.class);
        TypedQuery query = mock(TypedQuery.class);
        List list = mock(List.class);

        when(entityManager.getCriteriaBuilder()).thenReturn(criteriaBuilder);
        when(criteriaBuilder.createQuery(Location.class)).thenReturn(criteriaQuery);
        when(criteriaQuery.from(Location.class)).thenReturn(root);

        when(entityManager.createQuery(criteriaQuery)).thenReturn(query);
        when(query.getResultList()).thenReturn(list);

        DaoWeather.getLocationList();

        verify(entityManager, times(1)).getCriteriaBuilder();
        verify(criteriaBuilder, times(1)).createQuery(Location.class);
        verify(criteriaQuery, times(1)).from(Location.class);
        verify(entityManager, times(1)).createQuery(criteriaQuery);
        verify(query, times(1)).getResultList();
    }
}
