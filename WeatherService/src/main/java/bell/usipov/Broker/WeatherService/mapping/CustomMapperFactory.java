package bell.usipov.Broker.WeatherService.mapping;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * Кастомный mapperFactory
 */
@Component
public class CustomMapperFactory implements FactoryBean<MapperFactory> {

    @Override
    public MapperFactory getObject(){
        return new DefaultMapperFactory.Builder()
                .mapNulls(false)
                .constructorResolverStrategy(null)
                .build();
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
