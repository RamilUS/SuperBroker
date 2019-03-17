package bell.usipov.broker.dbmodule;

import bell.usipov.broker.dbmodule.jms.WeatherReceiver;
import bell.usipov.broker.dbmodule.service.WeatherService;
import bell.usipov.broker.dtomodule.model.DtoWeather;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.jms.JMSException;
import javax.jms.Message;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class WeatherReceiverTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherReceiver weatherReceiver;


    /**
     * Проверка инъекции
     */
    @Test
    public void checkNull(){
        Assert.assertNotNull(weatherService);
        Assert.assertNotNull(weatherReceiver);
    }

    /**
     * Проверка получения сообщения
     * @throws JMSException - исключения методов message
     */
    @Test
    public void receiveTest() throws JMSException {
        Message message = mock(Message.class);
        DtoWeather weather = mock(DtoWeather.class);

        when(message.isBodyAssignableTo(DtoWeather.class)).thenReturn(true);
        when(message.getBody(DtoWeather.class)).thenReturn(weather);

        weatherReceiver.onMessage(message);

        verify(message, times(1)).isBodyAssignableTo(DtoWeather.class);
        verify(message, times(1)).getBody(DtoWeather.class);
        verify(weatherService, times(1)).save(weather);
    }

    /**
     * Проверка пропуска сообщения, если сообщение не приводится к нужному типу
     * @throws JMSException исключения методов message
     */
    @Test
    public void noReceiveTest() throws JMSException {
        Message message = mock(Message.class);

        when(message.isBodyAssignableTo(DtoWeather.class)).thenReturn(false);

        try {
            weatherReceiver.onMessage(message);
        }catch (RuntimeException e){
            assertEquals(e.getMessage(), "Incorrect message body type");
        }
        verifyZeroInteractions(weatherService);
    }

    /**
     * Тестрование при null параметре
     */
    @Test
    public void nullMessageTest(){
        Message message = null;

        weatherReceiver.onMessage(message);

        verifyZeroInteractions(weatherService);
    }
}
