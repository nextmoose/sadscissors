package sadscissors;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * WTF.
 **/
public final class TestIt {
    /**
     * WTF.
     **/
    private static final double HALF = 0.50;

    /**
     * WTF.
     **/
    private SmartFridgeManager fridge;

    /**
     * WTF.
     **/
    public TestIt() {
    };

    /**
     * WTF.
     **/
    @Before
    public void setup() {
        fridge = new SmartFridgeManagerImpl();
    }

    /**
     * WTF.
     **/
    @Test
    public void justAnExample1() {
        fridge.handleItemRemoved("100");
        fridge.handleItemRemoved("101");
        assertEquals("101", fridge.getItems(HALF)[0]);
        fridge.handleItemRemoved("102");
        fridge.handleItemRemoved("103");
        assertEquals("103", fridge.getItems(HALF)[0]);
        assertTrue(true);
    }
}
