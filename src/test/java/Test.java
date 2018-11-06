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
    void justAnExample() {
	assertThrows(RuntimeException.class, () -> fridge.handleItemRemoved("100"));
    }
}
