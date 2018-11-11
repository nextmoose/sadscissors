package sadscissors;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * WTF.
 **/
public final class TestIt {
    /**
     * Stereotypical TYPE.
     **/
    private static final long TYPE = 100;

    /**
     * Close enough.
     **/
    private static final double EPSILON = 0.01;

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
        assertEquals(0.0, fridge.getFillFactor(TYPE), EPSILON);
    }
}
