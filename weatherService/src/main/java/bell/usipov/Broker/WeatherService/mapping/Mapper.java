package bell.usipov.broker.weatherservice.mapping;


import java.util.List;

/**
 * Маппер для преобразования классов
 */
public interface Mapper {

    /**
     * Преобразование объекта к указанному классу
     * @param object - преобразуемый объект
     * @param target - целевой класс преобразования
     * @return terget оьъект
     */
    <O, T> T map(O object, Class<T> target);

    <O, T> void map(O object, T target);

    /**
     * Преобразование объектов списка к указанному типу
     * @param list   - преобразуемый список
     * @param target - целевой класс
     * @param <L>    - класс преобразуемого списка
     * @param <T>    - целевой класс
     * @return преобразованный список
     */
    <L, T> List<T> mapAsList(Iterable<L> list, Class<T> target);

}
