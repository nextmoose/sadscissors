package sadscissors;

import org.junit.Before;
import org.junit.Test;
import static java.lang.Long.parseLong;
import static org.junit.Assert.assertEquals;

/**
 * WTF.
 **/
public final class TestIt {
    /**
     * Close enough.
     **/
    private static final double EPSILON = 0.01;

    /**
     * Zero.
     **/
    private static final double ZERO = 0.0;

    /**
     * Half.
     **/
    private static final double HALF = 0.50;

    /**
     * Just a little bit more than HALF.
     **/
    private static final double HALF_PLUS = HALF + EPSILON;

    /**
     * Just a little bit less than HALF.
     **/
    private static final double HALF_MINUS = HALF - EPSILON;

    /**
     * Unit under test.
     **/
    private SmartFridgeManager fridge;

    /**
     * Stereotypical Item Type.
     **/
    private static final long TYPE = parseLong("-574284650089157818");

    /**
     * Stereotypical item uuid.
     **/
    private static final String ITEM_UUID
        = "65774e23-d99b-4624-83d2-921e3251bbc2";

    /**
     * Stereotypical item name.
     **/
    private static final String ITEM_NAME
        = "ea67e92b-d92c-4628-8f5c-2103b6e48469";

    /**
     * Construct a test instance.
     **/
    public TestIt() {
    };

    /**
     * Set up the test instance.
     **/
    @Before
    public void setup() {
        fridge = new SmartFridgeManagerImpl();
    }

    /**
     * Test the handleItemRemoved method by
     *
     * <OL>
     *           <LI> Verifying that the item is not in the fridge.
     *           <LI> Adding the item.
     *           <LI> Verifying that the item is in the fridge.
     *           <LI> Removing the item.
     *           <LI> Verifying that the item is not in the fridge.
     * </OL>
     **/
    @Test
    public void testHandleItemRemovedPlus() {
        assertEquals(0, fridge.getItems(ZERO).length);
        assertEquals(ZERO, fridge.getFillFactor(TYPE), EPSILON);
        fridge.handleItemAdded(TYPE, ITEM_UUID, ITEM_NAME, HALF);
        assertEquals(1, fridge.getItems(HALF).length);
        assertEquals(HALF, fridge.getFillFactor(TYPE), EPSILON);
        fridge.handleItemRemoved(ITEM_UUID);
        assertEquals(0, fridge.getItems(ZERO).length);
        assertEquals(ZERO, fridge.getFillFactor(TYPE), EPSILON);
    }

    /**
     * Test the handleItemRemoved method by
     *
     * <OL>
     *           <LI> Verifying that the item is not in the fridge.
     *           <LI> Adding the item.
     *           <LI> Verifying that the item is in the fridge.
     *           <LI> Removing the item.
     *           <LI> Verifying that the item is not in the fridge.
     * </OL>
     **/
    @Test
    public void testHandleItemRemoved() {
        assertEquals(0, fridge.getItems(ZERO).length);
        assertEquals(ZERO, fridge.getFillFactor(TYPE), EPSILON);
        fridge.handleItemAdded(TYPE, ITEM_UUID, ITEM_NAME, HALF);
        assertEquals(1, fridge.getItems(HALF).length);
        assertEquals(HALF, fridge.getFillFactor(TYPE), EPSILON);
        fridge.handleItemRemoved(ITEM_UUID);
        assertEquals(0, fridge.getItems(ZERO).length);
        assertEquals(ZERO, fridge.getFillFactor(TYPE), EPSILON);
    }

    /**
     * Test that getItems will not return items just above the
     * threshold.
     *
     * <OL>
     *           <LI> Verifying that the item is not in the fridge.
     *           <LI> Adding the item at HALF_PLUS fill.
     *           <LI> Verifying that getItems(HALF) does not return the item.
     *           <LI> Verify that the item is in the fridge.
     * </OL>
     **/
    @Test
    public void testGetItemsPlus() {
        assertEquals(0, fridge.getItems(ZERO).length);
        assertEquals(ZERO, fridge.getFillFactor(TYPE), EPSILON);
        fridge.handleItemAdded(TYPE, ITEM_UUID, ITEM_NAME, HALF_PLUS);
        assertEquals(0, fridge.getItems(HALF).length);
        assertEquals(HALF_PLUS, fridge.getFillFactor(TYPE), EPSILON);
    }

    /**
     * Test that getItems and getFillFactor will ignore empty containers.
     *
     * <OL>
     *           <LI> Verifying that the item is not in the fridge.
     *           <LI> Adding and empty container (the item at ZERO fill).
     *           <LI> Adding the item at HALF fill.
     *           <LI> Verifying that getItems(HALF) does not consider the empty
     *                    container.
     *           <LI> Verify that getFillFactor() does not consider the empty
     *                    container.
     * </OL>
     **/
    @Test
    public void testGetItemsPlus() {
        assertEquals(0, fridge.getItems(ZERO).length);
        assertEquals(ZERO, fridge.getFillFactor(TYPE), EPSILON);
        fridge.handleItemAdded(TYPE, ITEM_UUID, ITEM_NAME, HALF_PLUS);
        assertEquals(0, fridge.getItems(HALF).length);
        assertEquals(HALF_PLUS, fridge.getFillFactor(TYPE), EPSILON);
    }
}
