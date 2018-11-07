package sadscissors;

import org.apache.activemq.ActiveMQConnectionFactory;
import java.util.Collection;
import java.util.ArrayList;
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
     * The current data.
     **/
    private final Collection<Tuple> tuples = new ArrayList<>();

    /**
     * @{inheritDoc}.
     *
     * @param itemUUID {@inheritDoc}
     **/
    public void handleItemRemoved(final String itemUUID) {
        try {
            ConnectionFactory factory =
                new ActiveMQConnectionFactory("vm://localhost");
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
            Queue queue  = session.createQueue("fridge");
            MessageProducer producer = session.createProducer(queue);
            MapMessage message = session.createMapMessage();
            message.setString("type", "handleItemRemoved");
            message.setString("itemUUID", itemUUID);
            producer.send(message);
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
        try {
            ConnectionFactory factory =
                new ActiveMQConnectionFactory("vm://localhost");
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
            Queue queue  = session.createQueue("fridge");
            MessageProducer producer = session.createProducer(queue);
            MapMessage message = session.createMapMessage();
            message.setString("type", "handleItemAdded");
            message.setLong("itemType", itemType);
            message.setString("itemUUID", itemUUID);
            message.setString("name", name);
            message.setDouble("fillFactor", fillFactor);
            producer.send(message);
            connection.close();
        } catch (JMSException cause) {
            throw new RuntimeException(cause);
        }
    }

    /**
     * @{inheritDoc}.
     *
     * @param fillFactor @{inheritDoc}
     * @return an array of arrays containing [ itemType, fillFactor ]
     **/
    public Object[] getItems(final Double fillFactor) {
	read();
	return tuples.stream()
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
        try {
            ConnectionFactory factory =
                new ActiveMQConnectionFactory("vm://localhost");
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
            Queue queue  = session.createQueue("fridge");
            MessageProducer producer = session.createProducer(queue);
            MapMessage message = session.createMapMessage();
            message.setString("type", "forgetItem");
            message.setLong("itemType", itemType);
            producer.send(message);
            connection.close();
        } catch (JMSException cause) {
            throw new RuntimeException(cause);
        }
    }

    public void read() {
        try {
            ConnectionFactory factory =
                new ActiveMQConnectionFactory("vm://localhost");
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, AUTO_ACKNOWLEDGE);
            Queue queue  = session.createQueue("fridge");
            MessageConsumer consumer = session.createConsumer(queue);
            Object[] obj = new Object[1];
            for (
                 Message message = consumer.receive(SECOND);
                 message != null;
                 message = consumer.receive(SECOND)
                 ) {
                final MapMessage mapMessage = (MapMessage) message;
		if(mapMessage.getString("type")=="handleItemAdded") {
		    tuples.add(new Tuple() {
			    @Override
			    public long getItemUUID() {
				return mapMessage.getString("itemUUID");
			    }

			    @Override
			    public long getItemType() {
				return mapMessage.getLong("itemType");
			    }
			    
			    @Override
			    public double getFillFactor() {
				return mapMessage.getDouble("fillFactor");
			    }
			});
		}else if(mapMessage.getString("type")=="handleItemRemoved") {
		    tuples.removeIf(tuple -> tuple.getItemUUID()==mapMessage.getString("itemUUID"));
		} else if(mapMessage.getString("type")=="forgetItem") {
		    tuples.removeIf(tuple -> tuple.getItemType()==mapMessage.getLong("itemType");
		}
            }
            connection.close();
            return obj;
        } catch (final JMSException cause) {
            throw new RuntimeException(cause);
        }
    }
}
