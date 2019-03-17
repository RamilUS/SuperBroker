package bell.usipov.broker.admin.jms;


import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * Класс для отправки jms сообщений в очередь
 */
@ApplicationScoped
public class CitySenderImpl implements CitySender {

    /**
     * Очередь для jms сообщений, зарегистрированная на сервере по JNDI
     */
    @Resource(mappedName = "java:jboss/exported/jms/queue/city")
    private Queue queue;

    @Inject
    private JMSContext jmsContext;

    public CitySenderImpl() {

    }

    /**
     * Отправка полученного названия города в jms очередь на сервере
     * @param city - текст с названием города
     */
    public void send(String city) {

        if (city == null || city.isEmpty()) {
            return;
        }

        jmsContext.createProducer().send(queue, city);

    }
}
