package sadscissors;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @{inheritDoc}.
 **/
final class SmartFridgeManagerImpl implements SmartFridgeManager {
    /**
     * The data.
     **/
    private Collection<Tuple> tuples = new ArrayList<>();

    /**
     * @{inheritDoc}.
     *
     * @param itemUUID {@inheritDoc}
     **/
    public void handleItemRemoved(final String itemUUID) {
        synchronized (tuples) {
            tuples.removeIf(tuple -> tuple.getItemUUID() == itemUUID);
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
        if (fillFactor < 0) {
            throw
                new IllegalArgumentException("Negative Fill Factor");
        } else if (fillFactor > 1) {
            throw
                new IllegalArgumentException("Over Fill");
        }
        synchronized (tuples) {
            this.tuples.add(new Tuple() {
                    @Override
                    public long getItemType() {
                        return itemType;
                    }

                    @Override
                    public String getItemUUID() {
                        return itemUUID;
                    }

                    @Override
                    public Double getFillFactor() {
                        return fillFactor;
                    }
                });
        }
    }

    /**
     * Gets the item.
     *
     * @param tuple input
     * @return the item
     **/
    private Object[] getItem(final Tuple tuple) {
        Object[] item = {tuple.getItemType(), tuple.getFillFactor()};
        return item;
    }

    /**
     * @{inheritDoc}.
     *
     * @param fillFactor @{inheritDoc}
     * @return an array of arrays containing [ itemType, fillFactor ]
     **/
    public Object[] getItems(final Double fillFactor) {
        final Object[] items;
        synchronized (tuples) {
            items = tuples
                .stream()
                .filter(tuple -> tuple.getFillFactor() <= fillFactor)
                .filter(tuple -> tuple.getFillFactor() > 0.0)
                .map(tuple -> getItem(tuple))
                .toArray();
        }
        return items;
    }

    /**
     * @{inheritDoc}.
     *
     * @param itemType @{inheritDoc}
     * @return @{inheritDoc}
     **/
    public Double getFillFactor(final long itemType) {
        final Double fillFactor;
        synchronized (tuples) {
            fillFactor = tuples
                .stream()
                .filter(tuple -> tuple.getItemType() == itemType)
                .filter(tuple -> tuple.getFillFactor() > 0)
                .mapToDouble(tuple -> tuple.getFillFactor())
                .average()
                .orElse(0.0);
        }
        return fillFactor;
    }

    /**
     * @{inheritDoc}.
     *
     * @param itemType @{inheritDoc}
     */
    public void forgetItem(final long itemType) {
        synchronized (tuples) {
            tuples.removeIf(tuple -> tuple.getItemType() == itemType);
        }
    }
}
