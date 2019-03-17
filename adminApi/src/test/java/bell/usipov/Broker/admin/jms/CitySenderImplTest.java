package bell.usipov.broker.admin.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;


/**
 * Проверка CitySender
 */
@RunWith(MockitoJUnitRunner.class)
public class CitySenderImplTest {

    @Mock
    private JMSContext jmsContext;
    @Mock
    private Queue queue;

    @InjectMocks
    private CitySenderImpl citySenderImpl;

    /**
     * Проверка отправки сообщения
     */
    @Test
    public void sendTest() {

        String city = "City";
        JMSProducer jmsProducer = mock(JMSProducer.class);

        when(jmsContext.createProducer()).thenReturn(jmsProducer);

        citySenderImpl.send(city);

        verify(jmsContext, atLeast(1)).createProducer();
        verify(jmsProducer, atLeast(1)).send(queue, city);
    }

    /**
     * Проверка при null сообщении. Сообщение не должно быть отправленно
     */
    @Test
    public void nullSendTest() {
        String city = null;
        JMSProducer producer = mock(JMSProducer.class);

        when(jmsContext.createProducer()).thenReturn(producer);

        citySenderImpl.send(city);

        verifyZeroInteractions(jmsContext);
        verifyZeroInteractions(producer);
    }

    /**
     * Проверка при пустом сообщении
     */
    @Test
    public void emptyMessageTest() {
        String city = "";
        JMSProducer producer = mock(JMSProducer.class);

        when(jmsContext.createProducer()).thenReturn(producer);

        citySenderImpl.send(city);

        verifyZeroInteractions(jmsContext);
        verifyZeroInteractions(producer);
    }
}

