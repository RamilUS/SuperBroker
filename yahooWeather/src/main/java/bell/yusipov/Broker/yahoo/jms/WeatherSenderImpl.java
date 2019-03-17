package bell.yusipov.broker.yahoo.jms;


import bell.usipov.broker.dtomodule.model.DtoWeather;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.Queue;

/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class WeatherSenderImpl implements WeatherSender {

    @Resource(mappedName = "java:jboss/exported/jms/queue/weather")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    /**
     * {@inheritDoc}
     */
    @Override
    public void send(DtoWeather weather) {
        if (weather != null) {
            Message message = jmsContext.createObjectMessage(weather);
            jmsContext.createProducer().send(queue, message);
        } else {
            throw new RuntimeException("ERROR: You trying send message with weather==0");
        }
    }
}
