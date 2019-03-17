package bell.usipov.broker.dbmodule;

import bell.usipov.broker.dbmodule.jms.WeatherReceiver;
import bell.usipov.broker.dtomodule.service.GetWeatherData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;

/**
 * Файл конфигурации Spring
 */
@Configuration
@EnableJms
@EnableTransactionManagement
@ComponentScan(basePackages = {"bell.usipov.broker.dbmodule"})
public class SpringConf {

    @Autowired
    private GetWeatherData getWeatherData;

    @Resource(mappedName = "java:/ConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "java:jboss/exported/jms/queue/weather")
    private Queue queue;

    @Bean
    public WeatherReceiver receiver() {
        return new WeatherReceiver();
    }


    @Bean(name = "/WeatherService")
    public HessianServiceExporter hessianServiceExporter() {
        HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
        hessianServiceExporter.setService(getWeatherData);
        hessianServiceExporter.setServiceInterface(GetWeatherData.class);
        return hessianServiceExporter;
    }

    @Bean
    public DefaultMessageListenerContainer listenerContainerFactory() {
        DefaultMessageListenerContainer factory = new DefaultMessageListenerContainer();
        factory.setConnectionFactory(connectionFactory);
        factory.setDestination(queue);
        factory.setMessageListener(receiver());
        return factory;

    }

    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new JtaTransactionManager();
    }


}
