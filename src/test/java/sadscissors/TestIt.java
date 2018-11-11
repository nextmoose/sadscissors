package sadscissors;

import org.junit.Before;
import org.junit.Test;
import static java.lang.Long.parseLong;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
    private static final long ITEM_TYPE = parseLong("-574284650089157818");

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
     *           <LI> Adding the item at HALF fill.
     *           <LI> Verifying that the item is in the fridge.
     *           <LI> Removing the item.
     *           <LI> Verifying that the item is not in the fridge.
     * </OL>
     **/
    @Test
    public void testHandleItemRemoved() {
        fridge.handleItemAdded(ITEM_TYPE, ITEM_UUID, ITEM_NAME, HALF);
        assertEquals(1, fridge.getItems(HALF).length);
        assertEquals(HALF, fridge.getFillFactor(ITEM_TYPE), EPSILON);
        fridge.handleItemRemoved(ITEM_UUID);
        assertEquals(0, fridge.getItems(ZERO).length);
        assertEquals(ZERO, fridge.getFillFactor(ITEM_TYPE), EPSILON);
    }

    /**
     * Test that getItems will not return items just above the
     * threshold.
     *
     * <OL>
     *           <LI> Adding the item at HALF_PLUS fill.
     *           <LI> Verifying that getItems(HALF) does not return the item.
     *           <LI> Verify that the item is in the fridge.
     * </OL>
     **/
    @Test
    public void testGetItemsPlus() {
        fridge.handleItemAdded(ITEM_TYPE, ITEM_UUID, ITEM_NAME, HALF_PLUS);
        assertEquals(0, fridge.getItems(HALF).length);
        assertEquals(HALF_PLUS, fridge.getFillFactor(ITEM_TYPE), EPSILON);
    }

    /**
     * Test that getItems and getFillFactor will ignore empty containers.
     *
     * <OL>
     *           <LI> Adding and empty container (the item at ZERO fill).
     *           <LI> Adding the item at HALF fill.
     *           <LI> Verifying that getItems(HALF) does not consider the empty
     *                    container.
     *           <LI> Verify that getFillFactor() does not consider the empty
     *                    container.
     * </OL>
     **/
    @Test
    public void testEmptyContainer() {
        fridge.handleItemAdded(ITEM_TYPE, ITEM_UUID, ITEM_NAME, ZERO);
        fridge.handleItemAdded(ITEM_TYPE, ITEM_UUID, ITEM_NAME, HALF);
        assertEquals(1, fridge.getItems(HALF).length);
        assertEquals(HALF, fridge.getFillFactor(ITEM_TYPE), EPSILON);
    }

    /**
     * Test that getItems is returning in the array of arrays format.
     *
     * <OL>
     *           <LI> Adding the item at HALF fill.
     *           <LI> Verifying getItems
     * </OL>
     **/
    @Test
    public void testGetItems() {
        fridge.handleItemAdded(ITEM_TYPE, ITEM_UUID, ITEM_NAME, HALF);
        assertEquals(1, fridge.getItems(HALF).length);
        assertTrue(fridge.getItems(HALF)[0].getClass().isArray());
        Object[] item = (Object[]) fridge.getItems(HALF)[0];
        assertEquals(2, item.length);
        assertEquals(ITEM_TYPE, item[0]);
        assertEquals(Double.class, item[1].getClass());
        double fillFactor = (double) item[1];
        assertEquals(HALF, fillFactor, EPSILON);
    }

    /**
     * Test that forgetItems is forgetting.
     *
     * <OL>
     *           <LI> Adding the item.
     *           <LI> Verifying that the items got added.
     *           <LI> Forgetting the item type.
     *           <LI> Verifying that the item is forgotten.
     * </OL>
     **/
    @Test
    public void testForgetItem() {
        fridge.handleItemAdded(ITEM_TYPE, ITEM_UUID, ITEM_NAME, HALF);
        assertEquals(1, fridge.getItems(HALF).length);
        assertEquals(HALF, fridge.getFillFactor(ITEM_TYPE), EPSILON);
        fridge.forgetItem(ITEM_TYPE);
        assertEquals(0, fridge.getItems(HALF).length);
        assertEquals(ZERO, fridge.getFillFactor(ITEM_TYPE), EPSILON);
    }
}
