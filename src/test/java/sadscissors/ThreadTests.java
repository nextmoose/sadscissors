package sadscissors;

import org.junit.Before;
import org.junit.Test;
import static java.lang.Long.parseLong;
import static java.lang.Long.MAX_VALUE;
import static java.util.stream.LongStream.range;

/**
 * Test multi-threaded.
 **/
public final class ThreadTests {
    /**
     * Unit under test.
     **/
    private SmartFridgeManager fridge;

    /**
     * Stereotypical Item Type.
     **/
    private static final long ITEM_TYPE = parseLong("-8653181405493457174");

    /**
     * Stereotypical item uuid.
     **/
    private static final String ITEM_UUID
        = "8c280abd-4265-42a7-8401-7781fd1739e4";

    /**
     * Stereotypical item name.
     **/
    private static final String ITEM_NAME
        = "2be2271c-c614-4137-9d3c-419c070f61c7";

    /**
     * Half.
     **/
    private static final double HALF = 0.50;

    /**
     * Construct a test instance.
     **/
    public ThreadTests() {
    };

    /**
     * Keep adding items until we run out of memory.
     *
     * If and when we run out of memory, delete the last
     * added item.
     * So we all but use up the memory.
     *
     * @param index the item type of the next item
     * @return true iff we used all the memory
     **/
    private boolean addItem(final long index) {
        try {
            fridge.handleItemAdded(index, ITEM_UUID, ITEM_NAME, HALF);
            return false;
        } catch (final OutOfMemoryError cause) {
            fridge.forgetItem(index);
            return true;
        }
    }

    /**
     * Set up the test instance.
     **/
    @Before
    public void setup() {
        fridge = new SmartFridgeManagerImpl();
        range(0, MAX_VALUE)
            .mapToObj(i -> addItem(i))
            .filter(b -> b)
            .findFirst();
    }

    /**
     * DELETE ME.
     **/
    @Test
    public void test() {
        fridge.handleItemRemoved(ITEM_UUID);
    }
}
