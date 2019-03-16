package bell.yusipov.Broker.yahoo.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class  YahooRequestTest{

private YahooRequest yahooRequeste = new YahooRequestImpl();

@Before
public void checkNull(){
        Assert.assertNotNull( yahooRequeste);
        }

/**
 * Тестирование отправки запроса
 */
@Test
public void createRequestTest() {

        String location = "Moscow";

        String response =  yahooRequeste.requestToYahoo(location);

        Assert.assertNotNull(response);
        Assert.assertTrue(response.contains("location"));
        Assert.assertTrue(response.contains("wind"));
        Assert.assertTrue(response.contains("current_observation"));
        Assert.assertTrue(response.contains("forecasts"));
        Assert.assertTrue(response.contains("atmosphere"));
        Assert.assertTrue(response.contains("astronomy"));
        Assert.assertTrue(response.contains("condition"));
        }

/**
 * Тестирование игнорирования пустой строки
 */
@Test
public void ignoreEmptyString(){
        String location = "";

        String response = yahooRequeste.requestToYahoo(location);

        assertEquals(response, location);
        }

/**
 * Тестирование при null параметре
 */
@Test
public void ignoreNullString(){
        String location = null;

        String response = yahooRequeste.requestToYahoo(location);

        assertEquals(response, "");
        }
}
