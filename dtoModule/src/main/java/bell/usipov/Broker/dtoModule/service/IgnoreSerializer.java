package bell.usipov.Broker.dtoModule.service;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Интерфейс игнорирования перевода json в объект
 */
public class IgnoreSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String s,
                          JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException, JsonProcessingException {
        if(!s.equals("IGNORE")) {
            jsonGenerator.writeObject(s);
        }
    }
}
