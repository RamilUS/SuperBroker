package bell.yusipov.broker.yahoo.jms;

import bell.usipov.broker.dtomodule.model.DtoWeather;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Тестирование отправки погоды
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherSenderTest {

    @Mock
    private JMSContext jmsContext;
    @Mock
    private Queue queue;

    @InjectMocks
    private WeatherSenderImpl weatherSender;

    /**
     * Проверка на null
     */
    @Before
    public void checkNull(){
        Assert.assertNotNull(weatherSender);
        Assert.assertNotNull(jmsContext);
        Assert.assertNotNull(queue);
    }

    /**
     * Проверка отправуи погоды в очередь
     */
    @Test
    public void senderTest(){

        ObjectMessage message = mock(ObjectMessage.class);
        DtoWeather weather = mock(DtoWeather.class);
        JMSProducer producer = mock(JMSProducer.class);

        when(jmsContext.createObjectMessage(weather)).thenReturn(message);
        when(jmsContext.createProducer()).thenReturn(producer);

        weatherSender.send(weather);

        verify(jmsContext, atLeast(1)).createObjectMessage(weather);
        verify(jmsContext, atLeast(1)).createProducer();
        verify(producer, atLeast(1)).send(queue, message);
    }

    /**
     * Проверка невозможности отправки null
     */
    @Test
    public void noSendTest(){
        DtoWeather weatherDto = null;

        try {
            weatherSender.send(weatherDto);
        }catch (RuntimeException e){
            assertEquals(e.getMessage(), "ERROR: You trying send message with weather==0");
        }
        verifyZeroInteractions(jmsContext);
    }
}
