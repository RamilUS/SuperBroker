package bell.yusipov.Broker.yahoo.service;


import bell.usipov.Broker.dtoModule.model.DtoWeather;

public interface YahooService {

   void request (String location);

    DtoWeather jsonToObject(String jsonStr);

    void sendWeather (DtoWeather weather);
}
