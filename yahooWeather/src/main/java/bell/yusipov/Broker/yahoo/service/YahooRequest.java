package bell.yusipov.broker.yahoo.service;

/**
 * Сервис запроса в YAHOO
 */
public interface YahooRequest {

    /**
     * Отправка запроса в YAHOO
     * @param location - название города
     * @return - строка json
     */
    String requestToYahoo(String location);

}
