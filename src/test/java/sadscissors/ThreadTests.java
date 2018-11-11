package sadscissors;

import org.junit.Before;
import org.junit.Test;
import static java.lang.Long.parseLong;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test multi-threaded.
 **/
public final class ThreadTests {
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
