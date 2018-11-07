package sadscissors;

import org.junit.Before;
import org.junit.Test;
 
public class TestIt {
    private SmartFridgeManager fridge;

    public TestIt() {
    };

    @Before
    public void setup() {
	fridge = new SmartFridgeManagerImpl();
    }
    
    @Test
    public void justAnExample1() {
    }
}
