package bell.usipov.Broker.admin.jms;
/**
 * Интерфейс для отправки сообщения
 */
public interface CitySender {
    /**
     * метод отправки сообщения
     * @param city - название города для отправки в очередь
     */

    void send (String city);
}
