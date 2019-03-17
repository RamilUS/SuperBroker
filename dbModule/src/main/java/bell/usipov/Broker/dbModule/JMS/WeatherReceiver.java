package bell.usipov.broker.dbmodule.jms;

import bell.usipov.broker.dbmodule.service.WeatherService;
import bell.usipov.broker.dtomodule.model.DtoWeather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * Слушатель Jms очереди jms/queue/weather
 */
@Component
public class WeatherReceiver implements MessageListener {

    @Autowired
    private WeatherService weatherService;

    public WeatherReceiver() {

    }

    @Override
    public void onMessage(Message message) {

        if (message == null) {
            return;
        }

        try {
            if (message.isBodyAssignableTo(DtoWeather.class)) {
                DtoWeather weather = message.getBody(DtoWeather.class);
                weatherService.save(weather);

            } else {
                throw new RuntimeException("Incorrect message body type");
            }
        } catch (JMSException e) {
            throw new RuntimeException("Jms message error", e);
        }
    }

}

