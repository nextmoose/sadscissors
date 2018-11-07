package sadscissors;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Queue;
import javax.jms.JMSException;
import static javax.jms.Session.AUTO_ACKNOWLEDGE;

/**
 * @{inheritDoc}.
 **/
final class SmartFridgeManagerImpl implements SmartFridgeManager {
    /**
     * @{inheritDoc}.
     *
     * @param itemUUID {@inheritDoc}
     **/
    public void handleItemRemoved(final String itemUUID) {
        throw new RuntimeException("dasfdasfa213r3");
    }

    /**
     * @{inheritDoc}.
     *
     * @param itemType @{inheritDoc}
     * @param itemUUID @{inheritDoc}
     * @param name @{inheritDoc}
     * @param fillFactor @{inheritDoc}
     **/
    public void handleItemAdded(
                                final long itemType,
                                final String itemUUID,
                                final String name,
                                final Double fillFactor) {
        throw new RuntimeException("dfadsfads");
    }

    /**
     * @{inheritDoc}.
     *
     * @param fillFactor @{inheritDoc}
     * @return an array of arrays containing [ itemType, fillFactor ]
     **/
    public Object[] getItems(final Double fillFactor) {
        return null;
    }

    /**
     * @{inheritDoc}.
     *
     * @param itemType @{inheritDoc}
     * @return @{inheritDoc}
     **/
    public Double getFillFactor(final long itemType) {
        throw new RuntimeException("adfadsfadsfd832");
    }

    /**
     * @{inheritDoc}.
     *
     * @param itemType @{inheritDoc}
     */
    public void forgetItem(final long itemType) {
        throw new RuntimeException("fdsafa32012");
    }

    /**
     * WTF.
     *
     * @throws JMSException never
     **/
    private void doit() throws JMSException {
        ConnectionFactory factory =
            new ActiveMQConnectionFactory("vm://localhost");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
        Queue queue  = session.createQueue("fridge");
    }
}
