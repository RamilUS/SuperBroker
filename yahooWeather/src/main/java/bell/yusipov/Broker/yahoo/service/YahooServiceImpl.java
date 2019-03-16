package bell.yusipov.Broker.yahoo.service;


import bell.usipov.Broker.dtoModule.model.DtoWeather;
import bell.yusipov.Broker.yahoo.JSM.WeatherSender;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
/**
 * {@inheritDoc}
 */
@ApplicationScoped
public class YahooServiceImpl implements YahooService{

    private final YahooRequest yahooRequest;
    private final WeatherSender weatherSender;

    @Inject
    public YahooServiceImpl(YahooRequest yahooRequest, WeatherSender weatherSender){
        this.yahooRequest=yahooRequest;
        this.weatherSender= weatherSender;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void request(String location){

        if (location ==null || location.isEmpty()){
            return;
        }
        String jsonStr=yahooRequest.requestToYahoo(location);
        DtoWeather weather=jsonToObject(jsonStr);

        sendWeather(weather);

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DtoWeather jsonToObject( String jsonStr){
        if(jsonStr == null || jsonStr.isEmpty()){
            return new DtoWeather();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        DtoWeather weather;

        try {
            weather = objectMapper.readValue(jsonStr, DtoWeather.class);
            return weather;
        } catch (IOException e) {
            throw  new RuntimeException("Mapper read error", e);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void sendWeather (DtoWeather weather){
        if(weather !=null){
        weatherSender.send(weather);
        }
    }

}
