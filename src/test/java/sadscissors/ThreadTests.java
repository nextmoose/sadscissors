package sadscissors;

import org.junit.Before;

/**
 * Test multi-threaded.
 **/
public final class ThreadTests {
    /**
     * Unit under test.
     **/
    private SmartFridgeManager fridge;

    /**
     * Construct a test instance.
     **/
    public ThreadTests() {
    };

    /**
     * Set up the test instance.
     **/
    @Before
    public void setup() {
        fridge = new SmartFridgeManagerImpl();
    }
}
