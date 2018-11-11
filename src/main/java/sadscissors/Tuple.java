package sadscissors;

/**
 * Basic data.
 **/
interface Tuple {
    /**
     * The Item Type.
     *
     * @return the item type
     **/
    long getItemType();

    /**
     * The Item UUID.
     *
     * @return the item uuid
     **/
    String getItemUUID();

    /**
     * The fill factory.
     *
     * @return the fill factory
     **/
    Double getFillFactor();
}
