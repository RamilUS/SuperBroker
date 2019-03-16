package bell.yusipov.Broker.yahoo.JSM;


/**
 * Слушатель Jms очереди java:jboss/exported/jms/queue/city
 */
import bell.yusipov.Broker.yahoo.service.YahooService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(name = "CityReceiver", activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationLookup",
                propertyValue = "java:jboss/exported/jms/queue/city"),
        @ActivationConfigProperty(propertyName = "destinationType",
                propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "acknowledgeMode",
                propertyValue = "Auto-acknowledge")})
public class CityReceiver implements MessageListener {

    @Inject
    private YahooService yahooService;

    public CityReceiver(){
    }

    @Override
    public void onMessage(Message city){

        if(city==null){
            return;
        }
        try {
            if(city.isBodyAssignableTo(String.class)) {
                String location = city.getBody(String.class);
                yahooService.request(location);
            }else{
                throw new RuntimeException("Body type is not correct");
            }
        } catch (JMSException e){
            throw new RuntimeException(" JMS Error ", e);
        }

    }
}
