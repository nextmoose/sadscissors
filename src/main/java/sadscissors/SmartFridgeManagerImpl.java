package sadscissors;
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
}
