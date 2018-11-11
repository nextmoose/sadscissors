package sadscissors;

import org.junit.Before;
import org.junit.Test;
import static java.lang.Long.parseLong;
import static org.junit.Assert.assertEquals;

/**
 * Multi Tests involving multiple item types.
 **/
public final class MultiTests {
    /**
     * Close enough.
     **/
    private static final Double EPSILON = 0.0000001;

    /**
     * Zero.
     **/
    private static final double ZERO_MINUS = 0.0 - EPSILON;

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
     * One.
     **/
    private static final double ONE = 1.00;

    /**
     * Just a little bit more than ONE.
     **/
    private static final double ONE_PLUS = ONE + EPSILON;

    /**
     * Unit under test.
     **/
    private SmartFridgeManager fridge;

    /**
     * Stereotypical Item Type.
     **/
    private static final long ALPHA_ITEM_TYPE = parseLong("34506798");

    /**
     * Stereotypical Item Type.
     **/
    private static final long BETA_ITEM_TYPE = parseLong("144307464");

    /**
     * Stereotypical item uuid.
     **/
    private static final String ALPHA_ITEM_UUID
        = "b2721024-b532-4472-aa67-cce9a45aad63";

    /**
     * Stereotypical item uuid.
     **/
    private static final String BETA_ITEM_UUID
        = "fe18f434-4fd7-4668-b515-d20a97ddc54d";

    /**
     * Stereotypical item name.
     **/
    private static final String ALPHA_ITEM_NAME
        = "c530c7ef-3234-4864-b478-b15abe43f319";

    /**
     * Stereotypical item name.
     **/
    private static final String BETA_ITEM_NAME
        = "21f0b506-e0c0-4b27-a2bc-d8203213be0c";

    /**
     * Construct a test instance.
     **/
    public MultiTests() {
    };

    /**
     * Set up the test instance.
     **/
    @Before
    public void setup() {
        fridge = new SmartFridgeManagerImpl();
    }

    /**
     * Test the handleItemRemoved method does not over remove
     *
     * <OL>
     *           <LI> Adding an ALPHA item at HALF fill.
     *           <LI> Adding a BETA item at HALF fill.
     *           <LI> Verifying that the ALPHA item is in the fridge.
     *           <LI> Removing the ALPHA item.
     *           <LI> Verifying that the ALPHA item is not in the fridge.
     *           <LI> Verifying that the BETA item is in the fridge.
     * </OL>
     **/
    @Test
    public void testHandleItemRemoved() {
        fridge
            .handleItemAdded(
                             ALPHA_ITEM_TYPE,
                             ALPHA_ITEM_UUID,
                             ALPHA_ITEM_NAME,
                             HALF);
        fridge.
            handleItemAdded(
                            BETA_ITEM_TYPE,
                            BETA_ITEM_UUID,
                            BETA_ITEM_NAME,
                            HALF);
        assertEquals(2, fridge.getItems(HALF).length);
        assertEquals(HALF, fridge.getFillFactor(ALPHA_ITEM_TYPE), EPSILON);
        assertEquals(HALF, fridge.getFillFactor(BETA_ITEM_TYPE), EPSILON);
        fridge.handleItemRemoved(ALPHA_ITEM_UUID);
        assertEquals(1, fridge.getItems(HALF).length);
        assertEquals(ZERO, fridge.getFillFactor(ALPHA_ITEM_TYPE), EPSILON);
        assertEquals(HALF, fridge.getFillFactor(BETA_ITEM_TYPE), EPSILON);
    }

    /**
     * Test that getFillFactor will ignore other types.
     *
     * <OL>
     *           <LI> Add an ALPHA item at HALF.
     *           <LI> Add a BETA item at ONE.
     *           <LI> Verify there are 2 items.
     *           <LI> Verify the fill factor calculation for the ALPHA item
     *                    is not contaminated by the BETAA item.
     *           <LI> Verify the fill factor calculation for the BETA item
     *                    is not contaminated by the ALPHA item.
     * </OL>
     **/
    @Test
    public void testEmptyContainer() {
        fridge
            .handleItemAdded(
                             ALPHA_ITEM_TYPE,
                             ALPHA_ITEM_UUID,
                             ALPHA_ITEM_NAME,
                             HALF);
        fridge
            .handleItemAdded(
                             BETA_ITEM_TYPE,
                             BETA_ITEM_UUID,
                             BETA_ITEM_NAME,
                             ONE);
        assertEquals(2, fridge.getItems(ONE).length);
        assertEquals(HALF, fridge.getFillFactor(ALPHA_ITEM_TYPE), EPSILON);
        assertEquals(ONE, fridge.getFillFactor(BETA_ITEM_TYPE), EPSILON);
    }

    /**
     * Test that what happens if no ALPHAs but some BETAs have been added.
     *
     * <OL>
     *           <LI> Add a BETA item.
     *           <LI> Verifying that getItems(HALF) returns 1.
     *           <LI> Verify that getFillFactor() does not return anything.
     * </OL>
     **/
    @Test
    public void testVoid() {
        fridge
            .handleItemAdded(
                             BETA_ITEM_TYPE,
                             BETA_ITEM_UUID,
                             BETA_ITEM_NAME,
                             HALF);
        assertEquals(1, fridge.getItems(HALF).length);
        assertEquals(ZERO, fridge.getFillFactor(ALPHA_ITEM_TYPE), EPSILON);
    }

    /**
     * Test that forgetItems is forgetting but not overforgetting.
     *
     * <OL>
     *           <LI> Adding an ALPHA item.
     *           <LI> Adding a BETA item.
     *           <LI> Verifying that the items got added.
     *           <LI> Forgetting the ALPHA item type.
     *           <LI> Verifying that the ALPHA item is forgotten.
     *           <LI> Verifying that the BETA item is not forgotten.
     * </OL>
     **/
    @Test
    public void testForgetItem() {
        fridge
            .handleItemAdded(
                             ALPHA_ITEM_TYPE,
                             ALPHA_ITEM_UUID,
                             ALPHA_ITEM_NAME,
                             HALF);
        fridge
            .handleItemAdded(
                             BETA_ITEM_TYPE,
                             BETA_ITEM_UUID,
                             BETA_ITEM_NAME,
                             HALF);
        assertEquals(2, fridge.getItems(HALF).length);
        assertEquals(HALF, fridge.getFillFactor(ALPHA_ITEM_TYPE), EPSILON);
        assertEquals(HALF, fridge.getFillFactor(BETA_ITEM_TYPE), EPSILON);
        fridge.forgetItem(ALPHA_ITEM_TYPE);
        assertEquals(1, fridge.getItems(HALF).length);
        assertEquals(ZERO, fridge.getFillFactor(ALPHA_ITEM_TYPE), EPSILON);
        assertEquals(HALF, fridge.getFillFactor(BETA_ITEM_TYPE), EPSILON);
    }
}
