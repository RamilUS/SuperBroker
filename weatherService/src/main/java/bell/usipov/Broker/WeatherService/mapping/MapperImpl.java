package bell.usipov.broker.weatherservice.mapping;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class MapperImpl implements Mapper {

    private final MapperFactory mapperFactory;

    @Autowired
    public MapperImpl(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <O, T> T map(O object, Class<T> target) {
        try {
            return mapperFactory.getMapperFacade().map(object, target);
        } catch (Exception e) {
            throw new RuntimeException("Mapping ERROR", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <O, T> void map(O object, T target) {
        try {
            mapperFactory.getMapperFacade().map(object, target);
        } catch (Exception e) {
            throw new RuntimeException("Mapping ERROR", e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <L, T> List<T> mapAsList(Iterable<L> list, Class<T> target) {
        try {
            return mapperFactory.getMapperFacade().mapAsList(list, target);
        } catch (Exception e) {
            throw new RuntimeException("Mapping ERROR", e);
        }
    }
}

