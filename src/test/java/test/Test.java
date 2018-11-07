package sadscissors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
 
class JUnit5ExampleTest {
    private SmartFridgeManager fridge;

    @BeforeEach
    void setup() {
	fridge = new SmartFridgeManagerImpl();
    }
    
    @Test
    void justAnExample1() {
	assertThrows(RuntimeException.class, () -> fridge.handleItemRemoved("100"));
    }
    
    @Test
    void justAnExample2() {
	assertThrows(RuntimeException.class, () -> fridge.handleItemAdded(100, "100", "stuff", 0.50));
    }
    
    @Test
    void justAnExample3() {
	assertThrows(RuntimeException.class, () -> fridge.getItems(0.50));
    }
    
    @Test
    void justAnExample4() {
	assertThrows(RuntimeException.class, () -> fridge.getFillFactor(100));
    }
    
    @Test
    void justAnExample5() {
	assertThrows(RuntimeException.class, () -> fridge.forgetItem(100));
    }
}
