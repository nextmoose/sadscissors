/**
 * @{inheritDoc}.
 **/
final class SmartFridgeManagerImpl implements SmartFridgeManager {
    /**
     * @{inheritDoc}.
     *
     * @param itemUUID {@inheritDoc}
     **/
    public void handleItemRemoved( String itemUUID ) {
	
    }

    /**
     * @{inheritDoc}.
     *
     * @param itemType @{inheritDoc}
     * @param itemUUID @{inheritDoc}
     * @param name @{inheritDoc}
     * @param fillFactor @{inheritDoc}
     **/
    public void handleItemAdded( long itemType, String itemUUID, String name, Double fillFactor ) {
    }

    /**
     * @{inheritDoc}.
     *
     * @param fillFactor @{inheritDoc}
     * @return an array of arrays containing [ itemType, fillFactor ]
     **/
    Object[] getItems( Double fillFactor ) {
    }

    /**
     * @{inheritDoc}.
     *
     * @param itemType @{inheritDoc}
     * @return @{inheritDoc}
     **/
    Double getFillFactor( long itemType ) {
    }

    /**
     * @{inheritDoc}.
     *
     * @param itemType @{inheritDoc}
     */
    void forgetItem( long itemType ) {
    }    
}
