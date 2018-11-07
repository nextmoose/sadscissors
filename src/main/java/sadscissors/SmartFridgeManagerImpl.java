package sadscissors;

import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.ConnectionFactory;
import javax.jms.Connection;
import javax.jms.Session;
import javax.jms.Queue;
import javax.jms.MessageProducer;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MapMessage;
import javax.jms.MessageConsumer;
import static javax.jms.Session.AUTO_ACKNOWLEDGE;

/**
 * @{inheritDoc}.
 **/
final class SmartFridgeManagerImpl implements SmartFridgeManager {
    /**
     * WTF.
     **/
    private static final long SECOND = 1000;

    /**
     * @{inheritDoc}.
     *
     * @param itemUUID {@inheritDoc}
     **/
    public void handleItemRemoved(final String itemUUID) {
        try {
            doit();
        } catch (JMSException cause) {
            throw new RuntimeException(cause);
        }
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
        try {
            return recv();
        } catch (final JMSException cause) {
            throw new RuntimeException(cause);
        }
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
        MessageProducer producer = session.createProducer(queue);
        MapMessage message = session.createMapMessage();
        message.setString("foo", "bar");
        producer.send(message);
    }

    /**
     * WTF.
     *
     * @return wtf
     * @throws JMSException never
     **/
    private Object[] recv() throws JMSException {
        ConnectionFactory factory =
            new ActiveMQConnectionFactory("vm://localhost");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
        Queue queue  = session.createQueue("fridge");
        MessageConsumer consumer = session.createConsumer(queue);
        Message message = consumer.receive(SECOND);
        MapMessage mapMessage = (MapMessage) message;
        Object[] obj = new Object[1];
        System.out.println("AAAAAAAAAAAAAAAAAAA");
        System.out.println(mapMessage.getString("foo"));
        obj[0] = mapMessage.getString("foo");
        return obj;
    }
}
